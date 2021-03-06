package edu.fpdual.hotelesapp.mail;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * Clase para generar un codigoQR con logo
 * 
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */
public class QRGenerator {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		/**
		 * Definir los parámetros del código QR.
		 */
		int width = 300;
		int height = 300;
		String format = "jpg";
		String ruta = System.getProperty("user.home");
		String rutaCompleta = ruta + "/Documents/reserva" + 1 + ".pdf";
		String content = rutaCompleta + "Imagen.png";// Contenido del código QR

		/**
		 * Definir sugerencias HashMap
		 */
		@SuppressWarnings("rawtypes")
		HashMap hints = new HashMap();

		/**
		 * hints llama a la función put para establecer el conjunto de caracteres, el
		 * espaciado y la corrección de errores en M
		 */

		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);// Nivel de corrección de error [L, M, Q, H]
		hints.put(EncodeHintType.MARGIN, 2);

		/**
		 * Generar el codigo QR
		 */
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			/**
			 * Finalmente, usamos la clase de función MultiformatWriter para llamar a la
			 * función echoed y devolver un valor, luego escribimos en el archivo
			 */ /* content */
			BitMatrix bitMatrix = multiFormatWriter.encode(
					"iVBORw0KGgoAAAANSUhEUgAACa8AAA20CAIAAAB3e7Q/AAAACXBIWXMAAC4jAAAuIwF4pT92AACAAElEQVR4AQBGkbluAP",
					BarcodeFormat.QR_CODE, width, height, hints);
			String rutaCompleta2 = ruta + "/Documents/codigoQR.png";
			Path file = new File(rutaCompleta2).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
			/**
			 * ************* Agregar logo *****************
			 */

			/**
			 * Leer la imagen del código QR
			 */
			BufferedImage bufferedImage = ImageIO.read(new File(file.toString()));
			Graphics2D graphics = bufferedImage.createGraphics();

			/**
			 * Leer la imagen del logo
			 */
			BufferedImage logo = ImageIO
					.read(new File(ruta + "\\ejemplosConJavaFX\\apphoteles\\src\\main\\resources\\img\\logo2.png"));

			/**
			 * Establece el tamaño del logo, si es demasiado grande cubrirá el código QR,
			 * aquí hay un 20%
			 * 
			 */
			int logoWidth = logo.getWidth() > bufferedImage.getWidth() * 2 / 10 ? (bufferedImage.getWidth() * 2 / 10)
					: logo.getWidth();
			int logoHeight = logo.getHeight() > bufferedImage.getHeight() * 2 / 10
					? (bufferedImage.getHeight() * 2 / 10)
					: logo.getHeight();

			/**
			 * Establecer la ubicación donde se coloca la imagen del logotipo, centrar
			 * 
			 */
			int x = (bufferedImage.getWidth() - logoWidth) / 2;
			int y = (bufferedImage.getHeight() - logoHeight) / 2;

			graphics.drawImage(logo, x, y, logoWidth, logoHeight, null);
			graphics.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
			/**
			 * Tamaño y color del borde del logo
			 */
			graphics.setStroke(new BasicStroke(2));
			graphics.setColor(Color.WHITE);
			graphics.drawRect(x, y, logoWidth, logoHeight);
			graphics.dispose();

			logo.flush();
			bufferedImage.flush();

			ImageIO.write(bufferedImage, format, new File(ruta + "\\Documents\\codigoQR2.png"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Imagen QR generada");
	}
}
