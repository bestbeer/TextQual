package pdfToText;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.apache.pdfbox.pdfparser.*;
import org.apache.pdfbox.lucene.LucenePDFDocument.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.searchengine.*;
import org.apache.pdfbox.util.PDFTextStripper;

import pdfToText.dao.*;
import pdfToText.vo.Paper;

public class pdfToText {
	String txtFilesDir = "C:\\TEMP\\text.pdf";
	String pdfFilesDir = "C:\\TEMP\\text.pdf";
	
	public static int createTxtFile(String text, String fileName) throws IOException
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter( fileName));
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
	
	public static String pdfFileToText(String path) throws IOException
	{
//		String path = new String();
//		path = "C:\\TEMP\\text.pdf";
		PDDocument pdfDocument = PDDocument.load(path);
		PDFTextStripper stripper = new PDFTextStripper();
		String text =  stripper.getText(pdfDocument);
		pdfDocument.close();
		return text;
	}
	
	
	public static int performConvertToText()
	{
		//will return 1 if succeed else 0
		
		//get the list of files to convert
		String paperHashedName;
		int result;
		String text;
		List<Paper> papersList = new ArrayList<Paper>();
		papersList = getFilesListToRead();
		
		for (Paper p:papersList) //for each Paper in the list
		{
			paperHashedName = p.getHashedName();
			try {
				text = pdfFileToText(paperHashedName);
			
				result = createTxtFile(text,p.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			
		}
		return 1;

	}
	
	public static void main(String[] args) throws IOException {
		
		List <Paper> PapersList = new ArrayList<Paper>();
		Paper paper = new Paper();
		paper.setName("HMK");
         
        JDBCPaperDAO jdbcPaperDAO = new JDBCPaperDAO();
        jdbcPaperDAO.getConnection();
        PapersList = jdbcPaperDAO.select();
         
        jdbcPaperDAO.closeConnection();
	}

}
