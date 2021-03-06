package edu.fpdual.hotelesapp.mail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import edu.fpdual.hotelesapp.manejadordb.ManejadorRelHabitacionServicio;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRelHotelServicio;

/**
 * Clase PDFCreator para generar un pdf con los datos de la reserva y el usuario
 * 
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 *
 */
public class PdfCreator {

	/**
	 * Metodo para generar pdf y añadirle las tablas necesarias para mostrar los
	 * datos de la reserva al cliente
	 * 
	 * @throws URISyntaxException Error
	 * @throws IOException Error
	 * @throws MalformedURLException Error
	 */
	public String generarPDF(Conector con, int id_registro)
			throws URISyntaxException, MalformedURLException, IOException {
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

			img.scalePercent(15);
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
			PdfPTable tablaHotel = new PdfPTable(8);
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
			tablaHotel.setWidthPercentage(105);

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

			manejadorRegistro.setDataTableUser(con, tablaUsuario, id_registro);
			documento.add(tablaUsuario);

			/**
			 * Agregamos la tabla de servicios
			 */
			PdfPTable tablaServicios = new PdfPTable(3);
			tablaServicios.addCell("Servicio(s) Contratatado(s)");
			tablaServicios.addCell("Precio");
			tablaServicios.addCell("Tipo");

			tablaServicios.setSpacingAfter(20);
			tablaServicios.setWidthPercentage(85);
			
			new ManejadorRelHotelServicio().setDataTableServices(con, tablaServicios, id_registro);
			new ManejadorRelHabitacionServicio().setDataTableServices(con, tablaServicios, id_registro);
			
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

			footer.scalePercent(20);
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
			pdf.generarPDF(con, 3);

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
