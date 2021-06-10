package edu.fpdual.hotelesapp.mail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Anchor;
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
import edu.fpdual.hotelesapp.manejadordb.ManejadorRegistro;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase PDFCreator
 * 
 * @author angela.bonilla.gomez
 *
 */
public class PdfCreator {

	/**
	 * Metodo para generar pdf y añadirle las tablas necesarias para mostrar los
	 * datos de la reserva al cliente
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public String generarPDF(Conector con, int id_registro)
			throws URISyntaxException, MalformedURLException, IOException {
		// java.awt.event.ActionEvent event deberia ir entre los parentesis para que se
		// ejecute al pulsar el boton
		Document documento = new Document();
		try {
			Connection con2 = con.getMySQLConnection();

			/**
			 * Ruta donde se va a guardar el pdf
			 */
			String ruta = System.getProperty("user.home");
			String rutaCompleta = ruta + "/Documents/reserva" + id_registro + ".pdf";
			PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
			documento.open();

			/**
			 * Imagen que vamos a usar de header
			 */
			Path pathHeader = Paths.get(getClass().getResource("/img/header.jpg").toURI());
			Image header = Image.getInstance(pathHeader.toAbsolutePath().toString());

			header.scaleAbsoluteWidth(600);
			header.scaleAbsoluteHeight(55);

			documento.add(header);

			/**
			 * Agregamos espacios en blanco
			 */
			Paragraph espacio = new Paragraph(" ");

			/**
			 * Agregamos el texto que queremos que aparezca
			 */
			Paragraph parrafo = new Paragraph();

			parrafo.add(espacio);
			parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			parrafo.add(
					"¡GRACIAS por confiar en nosotros! Su reserva se ha realizado con ÉXITO, a continuación tiene todos los datos.");
			parrafo.setFont(FontFactory.getFont("Arial", 18, Font.BOLD, BaseColor.DARK_GRAY));
			parrafo.add(espacio);

			documento.add(parrafo);

			/**
			 * Añadimos la imagen que queremos que aparezca
			 */
			Path path = Paths.get(getClass().getResource("/img/hotel.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());

			img.scalePercent(20);
			img.setScaleToFitHeight(true);
			img.setAlignment(Chunk.ALIGN_CENTER);
			img.setBorder(Image.BOX);
			img.setBorderWidth(7);
			img.setBorderColor(BaseColor.DARK_GRAY);

			documento.add(img);

			/**
			 * Creamos la tabla con los datos de la Reserva y le añadimos el tamaño y los
			 * espacios
			 */
			PdfPTable tablaHotel = new PdfPTable(9);
			tablaHotel.addCell("ID de registro");
			tablaHotel.addCell("Hotel");
			tablaHotel.addCell("Ubicación");
			tablaHotel.addCell("Estrellas");
			tablaHotel.addCell("Habitación");
			tablaHotel.addCell("Personas");
			tablaHotel.addCell("Entrada");
			tablaHotel.addCell("Salida");
			tablaHotel.addCell("Precio");

			tablaHotel.setSpacingBefore(20);
			tablaHotel.setSpacingAfter(20);
			tablaHotel.setWidthPercentage(110);

//			/**
//			 * Realizamos las consultas de la tabla
//			 */
//			PreparedStatement statement = con2.prepareStatement("SELECT id,nombreHotel,localizacion,Estrellas,id_habitacion,num_personas,fecha_entrada,fecha_salida,precio FROM Registro WHERE id = "+id_registro);
//			
//			/**
//			 * Ejecutamos la consulta
//			 */
//			ResultSet rs = statement.executeQuery();
//			
//			/**
//			 * Indicamos las filas que corresponden a cada columna y añadimos al documento
//			 */
//			while (rs.next()) {
//				
//					tablaHotel.addCell(rs.getString(1));
//					tablaHotel.addCell(rs.getString(2));
//					tablaHotel.addCell(rs.getString(3));
//					tablaHotel.addCell(rs.getString(4));
//					tablaHotel.addCell(rs.getString(5));
//					tablaHotel.addCell(rs.getString(6));
//					tablaHotel.addCell(rs.getString(7));
//					tablaHotel.addCell(rs.getString(8));
//					tablaHotel.addCell(rs.getString(9));
//				
//			}
			ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
			manejadorRegistro.setDataTablaHotel(con, tablaHotel, id_registro);
			documento.add(tablaHotel);
			/**
			 * Agregamos la tabla con los datos del Usuario
			 */
			PdfPTable tablaUsuario = new PdfPTable(4);
			tablaUsuario.addCell("Usuario");
			tablaUsuario.addCell("DNI");
			tablaUsuario.addCell("Telefono");
			tablaUsuario.addCell("Email");

			tablaUsuario.setSpacingAfter(20);
			tablaUsuario.setWidthPercentage(100);

//			PreparedStatement statement1 = con2.prepareStatement("SELECT nombre_usuario,dni,telefono,email FROM Registro WHERE id = "+id_registro);
//			ResultSet rs1 = statement1.executeQuery();
//			
//			if (rs1.next()) {
//				do {
//					tablaUsuario.addCell(rs1.getString(1));
//					tablaUsuario.addCell(rs1.getString(2));
//					tablaUsuario.addCell(rs1.getString(3));
//					tablaUsuario.addCell(rs1.getString(4));
//				} while(rs1.next());
//				
//			}
			manejadorRegistro.setDataTableUser(con, tablaUsuario, id_registro);
			documento.add(tablaUsuario);

			/**
			 * Agregamos la tabla de servicios
			 */
			PdfPTable tablaServicios = new PdfPTable(4);
			tablaServicios.addCell("ID de servicio contratado");
			tablaServicios.addCell("Nombre");
			tablaServicios.addCell("Precio");
			tablaServicios.addCell("Tipo");

			tablaServicios.setSpacingAfter(20);
			tablaServicios.setWidthPercentage(100);
			
			// Falta la consulta para meterle los datos a la tabla AQUI
			
			documento.add(tablaServicios);

			/**
			 * Agregamos mas texto que queremos que aparezca
			 */
			Paragraph parrafo2 = new Paragraph();

			parrafo2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			parrafo2.add(
					"Esperamos que tenga una experiencia gratificante. Para cualquier duda o problema contacte con nosotros. ¡GRACIAS Y HASTA PRONTO!");
			parrafo2.setFont(FontFactory.getFont("Arial", 18, Font.BOLDITALIC, BaseColor.DARK_GRAY));

			documento.add(parrafo2);

			/**
			 * Agregamos el correo con el que pueden contactar
			 */
			Font fuente = FontFactory.getFont("Arial", 12, Font.ITALIC, new BaseColor(0, 0, 255));
			Anchor link = new Anchor("Haga click aquí para contactar.", fuente);
			link.setReference("mailto:mihotelesapp@gmail.com");

			documento.add(link);

			/**
			 * Gracias
			 */
			Paragraph parrafo3 = new Paragraph();

			parrafo3.add(espacio);
			parrafo3.add(espacio);
			parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo3.add("AG2 Hoteles");
			parrafo3.setFont(FontFactory.getFont("Arial", 16, Font.BOLDITALIC, BaseColor.DARK_GRAY));
			parrafo3.add(espacio);

			documento.add(parrafo3);

			/**
			 * Imagen de footer
			 */
			Path path2 = Paths.get(getClass().getResource("/img/gracias.png").toURI());
			Image footer = Image.getInstance(path2.toAbsolutePath().toString());

			footer.scalePercent(26);
			footer.setScaleToFitHeight(true);
			footer.setAlignment(Chunk.ALIGN_RIGHT);
			footer.setAlignment(Chunk.ALIGN_BOTTOM);

			documento.add(footer);

			/**
			 * Logo
			 */
			Path path3 = Paths.get(getClass().getResource("/img/logo2.png").toURI());
			Image logo = Image.getInstance(path3.toAbsolutePath().toString());

			logo.scalePercent(15);
			logo.setScaleToFitHeight(true);
			logo.setAlignment(Chunk.ALIGN_LEFT);

			documento.add(logo);

			/**
			 * Cerramos el documento
			 */
			documento.close();
			// JOptionPane.showMessageDialog(null,"PDF creado");

			return rutaCompleta;
		} catch (IOException | DocumentException | URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public static void main(String[] args) {
		try {
			PdfCreator pdf = new PdfCreator();
			Conector con = new Conector();
			pdf.generarPDF(con, 2);

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
