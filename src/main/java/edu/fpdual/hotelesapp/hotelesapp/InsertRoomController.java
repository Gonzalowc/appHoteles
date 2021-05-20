package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InsertRoomController {
	@FXML
	private TextField txtIdHotel;
	
	@FXML
	private TextField txtNumPers;
	
	@FXML
	private DatePicker dateEntry;
	
	@FXML
	private DatePicker dateLeft;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private TextField txtUser;
	
	@FXML
	private CheckBox chkBusy;
	
	@FXML
	private MenuButton selectBtnHotel;
	
	
	private Hotel hotel;
	
	
	public void exit() {
		System.exit(0);
	}
	
	public void insertRoom() throws IOException{
		Conector con = new Conector();
		ManejadorHabitacion mh = new ManejadorHabitacion();

		
		
		Habitacion h = new Habitacion(null, 0, null, null, false, 0, null);
		
		mh.crearHabitacion(con, h);
	}
	
	@FXML
	public void rellenarMenu() {
		Conector con = new Conector();
		ManejadorHotel mho = new ManejadorHotel();
		ArrayList<Hotel> hoteles = mho.listaHoteles(con);
		
		
		for(Hotel h1 : hoteles) {
			MenuItem m = new MenuItem(h1.getNombre()+" - " + h1.getLocalizacion());
			selectBtnHotel.getItems().add(m);
		}
		
	}
	

}
