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
	/**
	 * Campo de texto con el nombre del hotel
	 */
	@FXML
	private TextField txtHotelName;
	/**
	 * Campo de texto con la localizacion del hotel
	 */
	@FXML
	private TextField txtHotelLocation;
	/**
	 * Campo de texto con las estrellas del hotel
	 */
	@FXML
	private TextField txtHotelStars;
	/**
	 * Campo de texto con la descripcion del hotel
	 */
	@FXML
	private TextField txtHotelDescription;
	/**
	 * Subir imagen
	 */
	@FXML
	private ImageView upImage;
	/**
	 * Borrar imagen
	 */
	@FXML
	private Label xDeteleImage;
	/**
	 * Imagen cargada
	 */
	private File imagenCargada;
	/**
	 * Imagen por defecto
	 */
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
	
	/**
	 * Metodo para cargar la imagen al crear un hotel
	 * @throws IOException
	 */
	@FXML
	public void chargeImage() throws IOException{
		imagenCargada = buscarImagen();
		if(imagenCargada!=null) {
			Image image = new Image(imagenCargada.toURI().toString());
			upImage.setImage(image);
			xDeteleImage.setVisible(true);
		}
		
	}
	
	/**
	 * Metodo para buscar la imagen y seleccionarla para subir
	 * @return
	 */
	public File buscarImagen() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona Imagen de Hotel");
		System.out.println("entra GenerarFileCHooser");
		
		FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("IMAGES files ", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);

    	return fileChooser.showOpenDialog(App.getStage());
	}
	
	/**
	 * Metodo para borrar la imagen seleccionada
	 * @throws IOException
	 */
	@FXML
	public void deleteImage() throws IOException{
		upImage.setImage(imagenDefecto);
		imagenCargada = null;
		xDeteleImage.setVisible(false);
	}
	
	
	
}
