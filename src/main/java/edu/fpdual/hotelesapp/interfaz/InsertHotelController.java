package edu.fpdual.hotelesapp.interfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


/**
 * Clase Insert Hotel Controller
 * @author angela.bonilla.gomez
 *
 */
public class InsertHotelController {

	@FXML
	private TextField txtHotelName;
	
	@FXML
	private TextField txtHotelLocation;
	
	@FXML
	private TextField txtHotelStars;
	
	@FXML
	private TextField txtHotelDescription;
	
	@FXML
	private ImageView upImage;
	@FXML
	private Label xDeteleImage;
	
	private File imagenCargada;
	private Image imagenDefecto = new Image(getClass().getResource("img/uploadImage.png").toExternalForm());
	
	/**
	 * Metodo para salir de la app
	 */
	public void exit() {
		System.exit(0);
	}
	
	
	
	/**
	 * Metodo para insertar hotel
	 * @throws IOException
	 */
	public void insertHotel() throws IOException {
		Conector con = new Conector();
		ManejadorHotel mh = new ManejadorHotel();
				
		Hotel h = new Hotel(txtHotelName.getText(), txtHotelLocation.getText(), Integer.parseInt(txtHotelStars.getText()), txtHotelDescription.getText());
		if(imagenCargada!=null) {
			InputStream imageBlob = new FileInputStream(imagenCargada);
			mh.crearHotel(con, h, imageBlob);
		}else {
			mh.crearHotel(con, h);
		}
		txtHotelLocation.setText(null);
		txtHotelStars.setText(null);
		txtHotelDescription.setText(null);
		deleteImage();
	}
	
	
	@FXML
	public void chargeImage() throws IOException{
		imagenCargada = buscarImagen();
		if(imagenCargada!=null) {
			Image image = new Image(imagenCargada.toURI().toString());
			upImage.setImage(image);
			xDeteleImage.setVisible(true);
		}
		
	}
	
	public File buscarImagen() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona Imagen de Hotel");
		System.out.println("entra GenerarFileCHooser");
		
		FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("IMAGES files ", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);

    	return fileChooser.showOpenDialog(App.getStage());
	}
	
	@FXML
	public void deleteImage() throws IOException{
		upImage.setImage(imagenDefecto);
		imagenCargada = null;
		xDeteleImage.setVisible(false);
	}
	
	
	
}
