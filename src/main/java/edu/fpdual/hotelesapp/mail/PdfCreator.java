package edu.fpdual.hotelesapp.mail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
	private void generarPDF() throws URISyntaxException, MalformedURLException, IOException {
		//java.awt.event.ActionEvent event deberia ir entre los parentesis para que se ejecute al pulsar el boton
		Document documento = new Document();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://79.146.64.10/Hoteles","proyecto","proyecto");
			
			/**
			 * Ruta donde se va a guardar el pdf
			 */
			String ruta = System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+" /Desktop/reserva.pdf"));
			documento.open();
			
			/**
			 * Agregamos el texto que queremos que aparezca
			 */
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, BaseColor.BLACK);
			Chunk chunk = new Chunk("¡GRACIAS POR RESERVAR CON NOSOTROS! SU RESERVA SE HA REALIZADO CON ÉXITO.", font);
			
			documento.add(chunk);
			
			/**
			 * Añadimos la imagen que queremos que aparezca
			 */
			Path path = Paths.get(getClass().getResource("/img/hotel.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.setWidthPercentage((float) 0.2);
			img.setScaleToFitHeight(true);
			documento.add(img);
			
			/**
			 * Creamos la tabla que veremos en el pdf de usuario
			 */
			PdfPTable tablaUsuario = new PdfPTable(4);
			tablaUsuario.addCell("NOMBRE");
			tablaUsuario.addCell("DNI");
			tablaUsuario.addCell("EMAIl");
			tablaUsuario.addCell("TELEFONO");
			
			/**
			 * Creamos la tabla que veremos en el pdf de hotel
			 */
			PdfPTable tablaHotel = new PdfPTable(3);
			tablaHotel.addCell("NOMBRE DEL HOTEL");
			tablaHotel.addCell("LOCALIZACION");
			tablaHotel.addCell("ESTRELLAS");
			
			/**
			 * Creamos la tabla que veremos en el pdf de habitacion
			 */
			PdfPTable tablaHabitacion = new PdfPTable(5);
			tablaHabitacion.addCell("NOMBRE DE LA HABITACION");
			tablaHabitacion.addCell("NUMERO DE PERSONAS");
			tablaHabitacion.addCell("FECHA DE ENTRADA");
			tablaHabitacion.addCell("FECHA DE SALIDA");
			tablaHabitacion.addCell("PRECIO");
			
			/**
			 * Creamos la tabla que veremos en el pdf de servicio
			 */
			PdfPTable tablaServicio = new PdfPTable(3);
			tablaServicio.addCell("NOMBRE DEL SERVICIO ADICIONAL CONTRATADO");
			tablaServicio.addCell("PRECIO");
			tablaServicio.addCell("TIPO");

			/**
			 * Realizamos las consultas de las cuatro tablas
			 */
			PreparedStatement statementUsuario = con.prepareStatement("SELECT nombre_usuario,dni,telefono,email FROM Usuario");
			PreparedStatement statementHotel = con.prepareStatement("SELECT nombre,localizacion,estrellas FROM Hotel");
			PreparedStatement statementHabitacion = con.prepareStatement("SELECT num_personas,fecha_entrada,fecha_salida,precio FROM Habitacion");
			PreparedStatement statementServicio = con.prepareStatement("SELECT nombre_servicio,precio,tipo FROM Servicio");
			
			/**
			 * Ejecutamos las consultas
			 */
			ResultSet rsUsuario = statementUsuario.executeQuery();
			ResultSet rsHotel = statementHotel.executeQuery();
			ResultSet rsHabitacion = statementHabitacion.executeQuery();
			ResultSet rsServicio = statementServicio.executeQuery();
			
			/**
			 * Indicamos las filas que corresponden a cada columna de usuario y añadimos al documento
			 */
			if (rsUsuario.next()) {
				do {
					tablaUsuario.addCell(rsUsuario.getString(1));
					tablaUsuario.addCell(rsUsuario.getString(2));
					tablaUsuario.addCell(rsUsuario.getString(3));
					tablaUsuario.addCell(rsUsuario.getString(4));
				} while(rsUsuario.next());
				documento.add(tablaUsuario);
			}
			
			/**
			 * Indicamos las filas que corresponden a cada columna de hotel y añadimos al documento
			 */
			if (rsHotel.next()) {
				do {
					tablaHotel.addCell(rsHotel.getString(1));
					tablaHotel.addCell(rsHotel.getString(2));
					tablaHotel.addCell(rsHotel.getString(3));
				} while(rsHotel.next());
				documento.add(tablaHotel);
			}
			
			/**
			 * Indicamos las filas que corresponden a cada columna de habitacion y añadimos al documento
			 */
			if (rsHabitacion.next()) {
				do {
					tablaHabitacion.addCell(rsHabitacion.getString(1));
					tablaHabitacion.addCell(rsHabitacion.getString(2));
					tablaHabitacion.addCell(rsHabitacion.getString(3));
					tablaHabitacion.addCell(rsHabitacion.getString(4));
					tablaHabitacion.addCell(rsHabitacion.getString(5));
				} while(rsHabitacion.next());
				documento.add(tablaHabitacion);
			}
			
			/**
			 * Indicamos las filas que corresponden a cada columna de servicio y añadimos al documento
			 */
			if (rsServicio.next()) {
				do {
					tablaServicio.addCell(rsServicio.getString(1));
					tablaServicio.addCell(rsServicio.getString(2));
					tablaServicio.addCell(rsServicio.getString(3));
				} while(rsServicio.next());
				documento.add(tablaServicio);
			}
			
			/**
			 * Cerramos el documento
			 */
			documento.close();
			//JOptionPane.showMessageDialog(null,"PDF creado");
		} catch (DocumentException | SQLException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}
