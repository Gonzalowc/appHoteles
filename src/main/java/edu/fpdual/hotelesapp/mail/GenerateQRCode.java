package edu.fpdual.hotelesapp.mail;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
 
import javax.imageio.ImageIO;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import edu.fpdual.hotelesapp.conector.Conector;
 
/**
 * Generador de Codigo QR en Java usando las librerias de ZXing
 * @author angela.bonilla.gomez
 *
 */
public class GenerateQRCode {
 
   /**
    * Propiedades de la imagen
    */
    private static final int qr_image_width = 400;
    private static final int qr_image_height = 400;
    private static final String IMAGE_FORMAT = "png";
    private static final String IMG_PATH = "C:\\Users\\angela.bonilla.gomez\\eclipse-workspace\\appHoteles\\src\\main\\resources\\img\\qrcode.png";
 
    /**
     * Metodo para crear el codigo QR y añadirle el pdf
     * @param idRegistro id del registro que guarda los datos
     * @throws Exception
     */
    public void generateCodePDF(int idRegistro) throws Exception {
 
        /**
         * URL que va a ser codificada
         */
    	PdfCreator pdf = new PdfCreator();
    	Conector con = new Conector();
        String data = pdf.generarPDF(con, idRegistro);
        
        //String data = "";
 
        /**
         * Codificamos la URL en formatoQR
         */
        BitMatrix matrix;
        Writer writer = new QRCodeWriter();
        try {
 
            matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
 
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
 
        /**
         * Creamos la imagen
         */
        BufferedImage image = new BufferedImage(qr_image_width,
                   qr_image_height, BufferedImage.TYPE_INT_RGB);
 
        /**
         * Iteramos a través de la matriz y dibujamos los píxeles en la imagen
         */
        for (int y = 0; y < qr_image_height; y++) {
            for (int x = 0; x < qr_image_width; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }
 
        /**
         * Esccribimos la imagen en un archivo
         */
        FileOutputStream qrCode = new FileOutputStream(IMG_PATH);
        ImageIO.write(image, IMAGE_FORMAT, qrCode);
 
        qrCode.close();
 
    }
    
    public static void main(String[] args) {
		GenerateQRCode qr = new GenerateQRCode();
		try {
			qr.generateCodePDF(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}
