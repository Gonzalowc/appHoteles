package edu.fpdual.hotelesapp.mail;

import java.io.FileOutputStream;
import java.sql.Connection;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import edu.fpdual.hotelesapp.conector.Conector;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.Rectangle;

class CodigoQR {

	public void crearCodigo(Conector con) throws Exception {
		Document documento = new Document(new Rectangle(360, 852));
		try {
			//Connection con2 = con.getMySQLConnection();
		
//			PdfCreator pdf = new PdfCreator();
//			String ruta = pdf.generarPDF(con, 1);
			
			String ruta = System.getProperty("user.home");
			String rutaCompleta = ruta+"/Documents/codigoqr.pdf";
			PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
			documento.open();
			
			BarcodeQRCode my_code = new BarcodeQRCode("Datos de la reserva", 1, 1, null);
			
			Image qr_image = my_code.getImage();
			
			documento.add(qr_image);
			
			documento.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	public static void main(String[] args) {
		Conector con = new Conector();
		CodigoQR qr = new CodigoQR();
		
		try {
			qr.crearCodigo(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
