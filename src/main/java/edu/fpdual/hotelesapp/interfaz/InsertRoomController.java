package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class InsertRoomController {
	/**
	 * Campo de texto con el id del hotel
	 */
	@FXML
	private TextField txtIdHotel;
	/**
	 * Campo de texto con el numero de personas
	 */
	@FXML
	private TextField txtNumPers;
	/**
	 * Seleccionador de fecha de entrada
	 */
	@FXML
	private DatePicker dateEntry;
	/**
	 * Seleccionador de fecha de salida
	 */
	@FXML
	private DatePicker dateLeft;
	/**
	 * Campo de texto con el precio
	 */
	@FXML
	private TextField txtPrice;
	/**
	 * Campo de texto con el usuario
	 */
	@FXML
	private TextField txtUser;
	/**
	 * Checkbox
	 */
	@FXML
	private CheckBox chkBusy;
	/**
	 * Boton para seleccionar hotel
	 */
	@FXML
	private MenuButton selectBtnHotel;
	/**
	 * Etiqueta de insercion correcta
	 */
	@FXML
	private Label lblCorrectInsert;
	/**
	 * Etiqueta de insercion incorrecta
	 */
	@FXML
	private Label lblErrorInsert;
	/**
	 * Hotel
	 */
	private Hotel hotel = null;
	/**
	 * Metodo para salir
	 */
	public void exit() {
		System.exit(0);
	}
	/**
	 * Metodo para insertar habitacion
	 * @throws IOException
	 */
	public void insertRoom() throws IOException{
		Conector con = new Conector();
		ManejadorHabitacion mh = new ManejadorHabitacion();

		Habitacion h = new Habitacion(hotel, Integer.parseInt(txtNumPers.getText()),null,null,false,Double.parseDouble(txtPrice.getText()), null);
		
		if(mh.crearHabitacion(con, h)) {
			lblCorrectInsert.setVisible(true);
			lblErrorInsert.setVisible(false);
		}else {
			lblErrorInsert.setVisible(true);
			lblCorrectInsert.setVisible(false);
		}
	}
	
	/**
	 * Metodo para introducir la lista de hoteles y coger sus datos
	 * @param h2 hotel que introducimos
	 */
	public void setListHotel(Hotel h2){
		MenuItem m = new MenuItem(h2.getNombre()+" - " + h2.getLocalizacion());
		m.setOnAction(event -> {
		    selectBtnHotel.setText(h2.getNombre()+" - " + h2.getLocalizacion());
		    hotel = h2;
		});
		
		selectBtnHotel.getItems().add(m);
		
	}
	

}
