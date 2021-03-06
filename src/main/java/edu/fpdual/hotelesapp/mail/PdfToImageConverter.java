package edu.fpdual.hotelesapp.mail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;
/**
 * Clase para convertir el pdf a imagen
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 *
 */
public class PdfToImageConverter {
	public static void main(String[] args) {
		String ruta = System.getProperty("user.home");
		String rutaCompleta = ruta+"/Documents/reserva"+1+".pdf";
		String pdfFileName =rutaCompleta;
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
