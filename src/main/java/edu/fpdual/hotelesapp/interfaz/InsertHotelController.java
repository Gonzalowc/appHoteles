package edu.fpdual.hotelesapp.interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;

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
		
		mh.crearHotel(con, h);
	}
	
	/**
	 * Metodo para volver atras
	 * @throws IOException
	 */
	public void goBack() throws IOException {
		App.cambiarVentana("logIn",StageStyle.UNDECORATED,false);
	}
}
