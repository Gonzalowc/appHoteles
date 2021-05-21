package edu.fpdual.hotelesapp.hotelesapp;

import edu.fpdual.hotelesapp.objetos.Habitacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
/**
 * Clase Card Room Controller
 * @author angela.bonilla.gomez
 *
 */
public class CardRoomController {
	
	@FXML
	private Label lblBtnReservar;
	@FXML
	private Label lblNumPersonas;
	@FXML
	private Label lblPrecio;
	@FXML
	private Label lblDisponible;
	@FXML
	private Label lblOcupada;
	@FXML
	private Label lblDescripcion;
	@FXML
	private Label lblCodeHabitacion;
	
	/**
	 * Metodo para rellenar las cartas de habitacion con los datos
	 * @param hab Habitacion que se agrega
	 */
	public void rellenarCard(Habitacion hab) {
		lblNumPersonas.setText(Integer.toString(hab.getNum_personas()));
		lblPrecio.setText(Double.toString(hab.getPrecio()));
		lblDescripcion.setText("Descripción no disponible");
		if(hab.isOcupada()) {
			lblDisponible.setVisible(true);
		}else {
			lblOcupada.setVisible(true);
		}
		lblCodeHabitacion.setText(Integer.toString(hab.getId()));
	}
	
	

}
