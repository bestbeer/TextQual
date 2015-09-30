package pdfToText;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Document;

import org.apache.pdfbox.pdfparser.*;
import org.apache.pdfbox.lucene.LucenePDFDocument.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.searchengine.*;
import org.apache.pdfbox.util.PDFTextStripper;

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
	
	public static Map<String,String>  getFilesListToRead()
	{
		Map filesListMap = new HashMap(); 
		//m1.put("Zara", "8");
		return filesListMap;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = new String();
		s = "C:\\TEMP\\text.pdf";
		PDDocument pdfDocument = PDDocument.load(s);
		PDFTextStripper stripper = new PDFTextStripper();
		String s1 =  stripper.getText(pdfDocument);
		pdfDocument.close();
	}

}
