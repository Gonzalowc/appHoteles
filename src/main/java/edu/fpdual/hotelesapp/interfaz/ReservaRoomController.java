package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ReservaRoomController {
	
	
	@FXML
	private Label lblIdHotel;
	
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
	
	private Habitacion habitacion;
	private Usuario user;
	
	public void setData(Habitacion hab,int idHotel, int NumPersonas, double price, Usuario user) {
		this.habitacion = hab;
		lblIdHotel.setText(Integer.toString(idHotel));
		System.out.println(idHotel);
		txtNumPers.setText(Integer.toString(NumPersonas));
		txtPrice.setText(Double.toString(price));
		this.user = user;
		txtUser.setText(user.getNombre());
		System.out.println(habitacion);
	}
	
	
	public Date convertToDate(LocalDate localDate) {
		
		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		return date;
	}
	public void toReservaRoomVista(ActionEvent event) throws IOException{
	
		
	}
	
	public void reserva() {
		Conector con = new Conector();
		//Manejador
	}

}
