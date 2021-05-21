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
	
	@FXML
	private Label lblCorrectInsert;
	@FXML
	private Label lblErrorInsert;
	
	private Hotel hotel = null;
	
	public void exit() {
		System.exit(0);
	}
	
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
	
	public void setListHotel(Hotel h2){
		MenuItem m = new MenuItem(h2.getNombre()+" - " + h2.getLocalizacion());
		m.setOnAction(event -> {
		    selectBtnHotel.setText(h2.getNombre()+" - " + h2.getLocalizacion());
		    hotel = h2;
		});
		
		selectBtnHotel.getItems().add(m);
		
	}
	

}
