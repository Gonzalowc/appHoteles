package edu.fpdual.hotelesapp.hotelesapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CardHotelController {
	@FXML
	private Label lblCodeHotel;
	@FXML
	private Label lblNombreHotel;
	@FXML
	private Label lblLocalizacion;
	@FXML
	private Label lblNumEstrella;
	@FXML
	private AnchorPane paneCardHotel;
	
	
	public void setDataHotel(int codeHotel,String nombreHotel,String localizacion, int numEstrellas) {
		lblCodeHotel.setText(Integer.toString(codeHotel));
		lblNombreHotel.setText(nombreHotel);
		lblLocalizacion.setText(localizacion);
		lblNumEstrella.setText(Integer.toString(numEstrellas));
	}
}
