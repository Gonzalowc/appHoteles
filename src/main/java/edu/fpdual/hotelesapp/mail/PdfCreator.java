package edu.fpdual.hotelesapp.mail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;

//import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.fpdual.hotelesapp.conector.Conector;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase PDFCreator
 * @author angela.bonilla.gomez
 *
 */
public class PdfCreator {
	
	/**
	 * Metodo para generar pdf y añadirle las tablas necesarias para mostrar los datos de la reserva al cliente
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("unused")
	private void generarPDF(Conector con, int id_registro) throws URISyntaxException, MalformedURLException, IOException {
		//java.awt.event.ActionEvent event deberia ir entre los parentesis para que se ejecute al pulsar el boton
		Document documento = new Document();
		try {
			Connection con2 = con.getMySQLConnection();
			
			/**
			 * Ruta donde se va a guardar el pdf
			 */
			String ruta = System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Documents/reserva"+id_registro+".pdf"));
			documento.open();
			
			/**
			 * Imagen que vamos a usar de header
			 */
			Path pathHeader = Paths.get(getClass().getResource("/img/header.jpg").toURI());
			Image header = Image.getInstance(pathHeader.toAbsolutePath().toString());
			header.scalePercent(10);
			header.setScaleToFitHeight(true);
			header.setAlignment(Chunk.HEADER);
			
			documento.add(header);
			
			/**
			 * Agregamos el texto que queremos que aparezca
			 */
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			parrafo.add("¡GRACIAS por confiar en nosotros! Su reserva se ha realizado con ÉXITO, a continuación tiene todos los datos.");
			parrafo.setFont(FontFactory.getFont("Arial", 18, Font.BOLD, BaseColor.DARK_GRAY));
			
			documento.add(parrafo);
			
			/**
			 * Añadimos la imagen que queremos que aparezca
			 */
			Path path = Paths.get(getClass().getResource("/img/hotel.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scalePercent(20);
			img.setScaleToFitHeight(true);
			img.setAlignment(Chunk.ALIGN_CENTER);
			
			documento.add(img);
			
			/**
			 * Creamos la tabla que veremos en el pdf
			 */
			PdfPTable tabla = new PdfPTable(13);
			tabla.addCell("ID de registro");
			tabla.addCell("Hotel");
			tabla.addCell("Localizacion");
			tabla.addCell("Estrellas");
			tabla.addCell("Habitacion");
			tabla.addCell("Personas");
			tabla.addCell("Entrada");
			tabla.addCell("Salida");
			tabla.addCell("Precio");
			tabla.addCell("Usuario");
			tabla.addCell("DNI");
			tabla.addCell("tlf");
			tabla.addCell("email");
			
			/**
			 * Realizamos las consultas de las cuatro tablas
			 */
			PreparedStatement statement = con2.prepareStatement("SELECT * FROM Registro WHERE id = "+id_registro);
			
			/**
			 * Ejecutamos las consultas
			 */
			ResultSet rs = statement.executeQuery();
			
			/**
			 * Indicamos las filas que corresponden a cada columna de usuario y añadimos al documento
			 */
			if (rs.next()) {
				do {
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
					tabla.addCell(rs.getString(3));
					tabla.addCell(rs.getString(4));
					tabla.addCell(rs.getString(5));
					tabla.addCell(rs.getString(6));
					tabla.addCell(rs.getString(7));
					tabla.addCell(rs.getString(8));
					tabla.addCell(rs.getString(9));
					tabla.addCell(rs.getString(10));
					tabla.addCell(rs.getString(11));
					tabla.addCell(rs.getString(12));
					tabla.addCell(rs.getString(13));
				} while(rs.next());
				documento.add(tabla);
			}
			
			/**
			 * Agregamos mas texto que queremos que aparezca
			 */
			Paragraph parrafo2 = new Paragraph();
			parrafo2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			parrafo2.add("Esperamos que tenga una experiencia gratificante. Para cualquier duda o problema contacte con este email.");
			parrafo2.setFont(FontFactory.getFont("Arial", 18, Font.BOLDITALIC, BaseColor.DARK_GRAY));
			
			
			documento.add(parrafo2);
			
			
			/**
			 * Cerramos el documento
			 */
			documento.close();
			//JOptionPane.showMessageDialog(null,"PDF creado"); 
		} catch (IOException| DocumentException | SQLException | URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		try {
			PdfCreator pdf = new PdfCreator();
			Conector con = new Conector();
			pdf.generarPDF(con,1);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

}
