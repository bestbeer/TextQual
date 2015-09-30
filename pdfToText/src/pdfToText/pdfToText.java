package pdfToText;

import java.io.IOException;

import javax.swing.text.Document;

import org.apache.pdfbox.pdfparser.*;
import org.apache.pdfbox.lucene.LucenePDFDocument.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.searchengine.*;
import org.apache.pdfbox.util.PDFTextStripper;

public class pdfToText {

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
