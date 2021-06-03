package edu.fpdual.hotelesapp.mail;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GeneradorQR2 {
	public static void main(String[] args) {
		try {
			String content = "C:\\Users\\angela.bonilla.gomez\\Documents\\reserva1.pdf";
			String filePath = "C:\\Users\\angela.bonilla.gomez\\Documents\\";
			String fileType = "png";
			int size = 125;
			
			QRCodeWriter qrcode = new QRCodeWriter();
			BitMatrix matrix = qrcode.encode(content, BarcodeFormat.QR_CODE, size, size);
			File qrFile = new File(filePath + "codigoQR." + fileType);
			int matrixWidth = matrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			graphics.setColor(Color.BLACK);

			for (int b = 0; b < matrixWidth; b++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (matrix.get(b, j)) {
						graphics.fillRect(b, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, qrFile);
		} catch (WriterException ex) {
			Logger.getLogger(GeneradorQR2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GeneradorQR2.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
