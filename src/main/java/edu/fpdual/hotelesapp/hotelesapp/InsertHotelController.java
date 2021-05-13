package edu.fpdual.hotelesapp.hotelesapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;


public class InsertHotelController {

	@FXML
	private TextField txtHotelName;
	
	@FXML
	private TextField txtHotelLocation;
	
	@FXML
	private TextField txtHotelStars;
	
	@FXML
	private TextField txtHotelDescription;
	
	public void exit() {
		System.exit(0);
	}
	
	public void insertHotel() throws IOException {
		Conector con = new Conector();
		ManejadorHotel mh = new ManejadorHotel();
		
		Hotel h = new Hotel(txtHotelName.getText(), txtHotelLocation.getText(), Integer.parseInt(txtHotelStars.getText()), txtHotelDescription.getText());
		
		mh.crearHotel(con, h);
	}
}
