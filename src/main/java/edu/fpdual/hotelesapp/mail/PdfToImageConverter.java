package edu.fpdual.hotelesapp.mail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

public class PdfToImageConverter {
	public static void main(String[] args) {
		String pdfFileName ="C:\\Users\\angela.bonilla.gomez\\Documents\\reserva1.pdf";
		try {
			PDDocument document = PDDocument.load(new File(pdfFileName));
			
			List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
			
			int page = 0;
			
			for (PDPage pdPage : pdPages) {
				++page;
				BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_INT_RGB,300);
				ImageIOUtil.writeImage(bim,pdfFileName+"Imagen"+".png",300);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
