package pdfToText;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.apache.pdfbox.pdfparser.*;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.lucene.LucenePDFDocument.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.searchengine.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.*;

import pdfToText.dao.*;
import pdfToText.vo.Paper;

import pdfToText.readability.endpoints.*;
import pdfToText.readability.enums.MetricType;

public class pdfToText {
	String txtFilesDir = "C:\\TEMP\\text.pdf";
	String pdfFilesDir = "C:\\TEMP\\text.pdf";
	
	public static int createTxtFile(String text, String fileName, String path) throws IOException
	{
		BufferedWriter writer = null;
		fileName = fileName.replaceAll("/", ".");
		try
		{
			writer = new BufferedWriter( new FileWriter( path + "\\" + fileName + ".txt"));
		    writer.write(text);	
		}
		catch ( IOException e)
		{
			throw e;
		}
		finally
		{  
			try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    	throw e;
		    } 
		}
		if(writer==null) 
			return -1;
		else 
			return 1;
	}
	
	public static List<Paper>  getFilesListToRead()
	{
		List <Paper> PapersList = new ArrayList<Paper>();
		JDBCPaperDAO jdbcPaperDAO = new JDBCPaperDAO();
        jdbcPaperDAO.getConnection();
        PapersList = jdbcPaperDAO.select(); //here the logic of which files to read will be in sql query
         
        jdbcPaperDAO.closeConnection();
        
		//Map filesListMap = new HashMap(); 
		//m1.put("Zara", "8");
		return PapersList;
	}
	
	public static String pdfFileToText(File file, String doi) throws IOException
	{
//		String path = new String();
//		path = "C:\\TEMP\\text.pdf";
		String text = null;
		PDDocument pdfDocument = PDDocument.load(file);
		PDFTextStripper stripper = new PDFTextStripper();
		try
		{
			text =  stripper.getText(pdfDocument);
		}
		catch(IOException e)
		{
			System.out.println("ERROR: There was a problem to convert pdf file to text check next file: " + file.getName() + "with DOI: " + doi);
			System.out.println(e.getMessage());
			
		}
		pdfDocument.close();
		return text;
	}
	
	public static String findAbsolutePath(String rootFolder, String fileName)
	{
		File root = new File(rootFolder);
        String absolutePath = null;
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fileName + ".pdf" ))
                	absolutePath = file.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath;
	}
	
	public static int performConvertToText(String pdffilesPath, String txtFilesPath)
	{
		//will return number of files that were converted if succeed else 0
		
		//get the list of files to convert
		int cnt = 0;
		String paperHashedName;
		String absoluteFilePath;
		int result;
		String text;
		List<Paper> papersList = new ArrayList<Paper>();
		papersList = getFilesListToRead(); //get files list from DB
		
		ReadabilityEndpoint readability = new ReadabilityEndpoint();
		
		Map<MetricType, BigDecimal> read_metrics = new HashMap<MetricType, BigDecimal>();
		for (Paper p:papersList) //for each Paper in the list
		{
			paperHashedName = p.getHashedName();
			absoluteFilePath = findAbsolutePath(pdffilesPath,paperHashedName);
			File file = new File(absoluteFilePath);
			if (absoluteFilePath!=null)
			{
				try {
					text = pdfFileToText(file, p.getName());
					if (text.length()>10) //check that there is any text and the pdf is readable file else we will not process this file 
					{
					read_metrics = readability.get(text);
					
					readability.writeToDbReadability(read_metrics, p.getName());
					
					result = createTxtFile(text,p.getName(),txtFilesPath);
					cnt++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("The error occured when trying process doc with name: " + p.getName() );
					return 0;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("The error occured when trying process doc with name: " + p.getName() );
				}

			}
		}
		return cnt;

	}
	
	public static void main(String[] args) throws IOException {
		String pdfFilesPath = "G:\\Papers\\ACM\\ACM";
		String txtFilesPath = "G:\\Papers\\ACM_Text2";
		int res = 0;
		res = performConvertToText(pdfFilesPath, txtFilesPath);
		if(res != 0)
		{
			System.out.print("The program convert successfully  " + res + " documents"); 
		}
		else 
			System.out.print("The program wasn't finish successfully  " + res); 
	}

}
