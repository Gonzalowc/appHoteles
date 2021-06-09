package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.DatosTarjetaCiudades;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CardCiudadController {

	@FXML
	private Label nomCiudad;
	@FXML
	private Label nHoteles;
	@FXML
	private Label nMediaEstrellas;
	private BorderPane parent;

	public void setDataCard(DatosTarjetaCiudades datoCiudad) {
		nomCiudad.setText(datoCiudad.getLocalizacion());
		nHoteles.setText(Integer.toString(datoCiudad.getCantidad()));
		nMediaEstrellas.setText(Double.toString(datoCiudad.getMediaEstrellas()));
	}

	public void setParent(BorderPane parent) {
		this.parent = parent;
	}

	/**
	 * Metodo para cambiar a la vista de Hoteles
	 * 
	 * @param event Evento de click
	 * @throws IOException
	 */
	@FXML
	public void toBuscadorCiudad() throws IOException {
		Conector con = new Conector();
		ManejadorHotel mh = new ManejadorHotel();
		
//TODO: aqui da error al cargar el archivo
		ArrayList<Hotel> hotelesMostrar = mh.hotelesCiudad(con,nomCiudad.getText());
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("aplication.fxml"));
			// creamos el panel a partir del loader
			BorderPane aplicacion = (BorderPane) loader.load();

			// creamos el objeto controlador que queremos usar
			AplicationController aplicationController = loader.<AplicationController>getController();
			// usamos sus metodos
			;
			System.out.println("toBuscadorCiudad "+parent);
			aplicationController.setMyPanel(parent);
			parent.setCenter(aplicationController.listaHoteles(hotelesMostrar));
			parent.setBottom(aplicationController.generarBuscador());
			parent.setRight(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}