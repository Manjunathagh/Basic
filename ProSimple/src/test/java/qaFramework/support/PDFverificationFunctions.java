package qaFramework.support;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.testautomationguru.utility.PDFUtil;

public class PDFverificationFunctions {

	PDFUtil pdfutil = new PDFUtil();

	public void getPDFPageCount(String path, int count) throws Exception {
		int actual = pdfutil.getPageCount(path);
		assertEquals(actual, count,"Error info: The actual page count is not equal to the expected page count");
	}

	public void verifyFileContent(String path, String text) throws Exception {
		String actual = pdfutil.getText(path);
		assertTrue(actual.trim().contains(text),"Error info: The particular text is not displayed");
	}

	public void extractImages(String path, int imageCount) throws Exception {
		List<String> actualExtractedImages = pdfutil.extractImages(path);
		assertEquals(actualExtractedImages.size(), imageCount);

	}
	public void saveAsImages(String PDFpath, String ImagePath) throws Exception {
		pdfutil.setImageDestinationPath(ImagePath);
		pdfutil.savePdfAsImage(PDFpath);
	}
	
	public void extractImages(String PDFpath, String ImagePath) throws Exception {
		pdfutil.setImageDestinationPath(ImagePath);
		pdfutil.extractImages(PDFpath);
	}
	public void compareTwoPDFfiles(String PDF1, String PDF2, String path) throws Exception {
		pdfutil.highlightPdfDifference(true);
		pdfutil.setImageDestinationPath(path);
		pdfutil.savePdfAsImage(PDF1);
		pdfutil.compare(PDF1,PDF2);
		/*assertTrue(pdfutil.compare(PDF1,PDF2));*/
       }

	/***********************************************
	 * Date       :   21/01/2019
	 * Author     :   Amogh
	 * Peer reviewer: 
	 * Modifier   :
	 ************************************************/
	public ArrayList<String>  getTextFromThePdf(String filepath) throws Exception {
		File file2 = new File(filepath);
		PDFParser parser = new PDFParser(new RandomAccessFile(file2,"r"));
		parser.parse();
		COSDocument cosDocument = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDocument);
		PDFTextStripper stripper = new PDFTextStripper();
		String pdfText = stripper.getText(pdDoc);
		
		String lines[] = pdfText.split("\\r?\\n");
		ArrayList<String> arrlist = new ArrayList<String>(); 
		
		for (int i= 0; i<=lines.length-1;i++ ){
			
			arrlist.add(lines[i]);

			for (String number : arrlist) { 
				System.out.println(number); 
			} 
			
			
		}
		
		cosDocument.close();
		pdDoc.close();
		return arrlist;
		
	}

	public  ArrayList<String> getLineByLineTextFromThePdf(String filepath) throws Exception {

		PDDocument doc = PDDocument.load(new File(filepath));
		PDFTextStripper stripper = new PDFTextStripper();
		String txt = stripper.getText(doc);
		String lines[] = txt.split("\\r?\\n");
		ArrayList<String> arrlist = new ArrayList<String>(); 
		
		for (int i= 0; i<=lines.length-1;i++ ){
			
			arrlist.add(lines[i]);

			for (String number : arrlist) { 
				System.out.println(number); 
			} 
			
			
		}

		doc.close();
		return arrlist;
	}

}

	
	 
