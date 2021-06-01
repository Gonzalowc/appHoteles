package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
	
	private int codeHotel;
	private AnchorPane reservaPane;
	
	private BorderPane parent;
	
	private Usuario user;
	private Habitacion habitacion;
	
	public void setUsuario(Usuario usuario) {
		this.user = usuario;
	}
	
	
	public void setReservaPane(AnchorPane reservaPane) {
		this.reservaPane = reservaPane;
	}
	
	public void setParentAplication(BorderPane parent) {
		this.parent = parent;
	}
	
	/**
	 * Metodo para rellenar las cartas de habitacion con los datos
	 * @param hab Habitacion que se agrega
	 */
	public void rellenarCard(Habitacion hab,int idHotel) {
		this.codeHotel = idHotel;
		habitacion = hab;
		lblNumPersonas.setText(Integer.toString(hab.getNum_personas()));
		lblPrecio.setText(Double.toString(hab.getPrecio()));
		lblDescripcion.setText("Descripción no disponible");
		if(hab.isOcupada()) {
			lblOcupada.setVisible(true);
		}else {
			lblDisponible.setVisible(true);
		}
		lblCodeHabitacion.setText(Integer.toString(hab.getId()));
	}
	
	public void rellenarReserva() throws IOException {
		
		// crear la clase que controla el archivo FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("reservaRoom.fxml"));
		// creamos el panel a partir del loader
		AnchorPane aux = (AnchorPane) loader.load();
		// creamos el objeto controlador que queremos usar
		ReservaRoomController reservaRoomController = loader.<ReservaRoomController>getController();
		// usamos sus metodos
		reservaRoomController.setData(habitacion,codeHotel, Integer.parseInt(lblNumPersonas.getText()), Double.parseDouble(lblPrecio.getText()),user);;
		parent.setRight(aux);
	}
	
	

}
