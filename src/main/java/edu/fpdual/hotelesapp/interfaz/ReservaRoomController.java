package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRegistro;
import edu.fpdual.hotelesapp.manejadordb.ManejadorServicio;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Servicio;
import edu.fpdual.hotelesapp.objetos.TipoServicio;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReservaRoomController {
	@FXML
	private ListView<Servicio> listDispoHotel;
	
	@FXML
	private ListView<Servicio> listSelectHotel;
	
	@FXML
	private ListView<Servicio> listDispoHab;
	
	@FXML
	private ListView<Servicio> listSelectHab;
	
	@FXML
	private Label msgExito;
	
	@FXML
	private Label info;
	
	@FXML
	private Label lblIdHotel;
	
	@FXML
	private TextField txtNumPers;
	
	@FXML
	private Label lblEntry;
	
	@FXML
	private DatePicker dateEntry;
	
	@FXML
	private Label lblLeft;
	
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
	
	@FXML
	private Label infoHotel;
	
	private Habitacion habitacion;
	private Usuario user;
	private Stage padre = new Stage();
	
	
	
	@FXML
	public void disabledateBeforeEntry(ActionEvent event) throws IOException {
		dateLeft.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(dateEntry.getValue(), empty);
				setDisable(empty || date.compareTo(dateEntry.getValue()) < 0 );
			}
		});
	}
	
	public void setData(Habitacion hab,int idHotel, int NumPersonas, double price, Usuario user) {
		this.habitacion = hab;
		lblIdHotel.setText(Integer.toString(idHotel));
		System.out.println(idHotel);
		txtNumPers.setText(Integer.toString(NumPersonas));
		txtPrice.setText(Double.toString(price));
		this.user = user;
		txtUser.setText(user.getNombre());
		System.out.println(habitacion);
		dateEntry.setShowWeekNumbers(false);
		dateLeft.setShowWeekNumbers(false);
		disablePastDate(dateEntry);
		setHotel();
		serviciosSeleccionadosHotel();
		serviciosSeleccionadosHabitacion();
	}
	
	
	public Date convertToDate(LocalDate localDate) {
		
		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		return date;
	}
	
	@FXML
	public void reserva() throws IOException{
		lblEntry.setStyle(null);
		lblLeft.setStyle(null);
		Conector con = new Conector();
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		LocalDate entrada = dateEntry.getValue();
		LocalDate salida = dateLeft.getValue();
		while(entrada.compareTo(salida)<=0) {
			entrada = entrada.plusDays(1);
			fechas.add(entrada);
		}
		
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<LocalDate> registros = manejadorRegistro.getDaysWhenIsOcupped(con, habitacion.getId());
		if(!fechas.removeAll(registros)) {
			ManejadorHabitacion manejadorHabitacion = new ManejadorHabitacion();
		manejadorHabitacion.alquilarHabitacion(con,dateEntry.getValue(),dateLeft.getValue(),habitacion,user);
		msgExito.setVisible(true);
		dateLeft.setValue(null);
		dateEntry.setValue(null);
		}else {
			dateLeft.setValue(null);
			dateEntry.setValue(null);
			lblEntry.setStyle("-fx-text-fill: #D00037;");
			lblLeft.setStyle("-fx-text-fill: #D00037;");
		}
	}
	
	public void disablePastDate(DatePicker dp) {
		
		dp.setDayCellFactory(picker -> new DateCell() {
			
		public void updateItem(LocalDate date, boolean empty) {
			super.updateItem(date, empty);
			LocalDate today = LocalDate.now();
			setDisable(empty || date.compareTo(today) < 0);
			
		}
		});
	}
	
	public void setHotel() {
		Conector con = new Conector();
		ManejadorHotel manejadorHotel = new ManejadorHotel();
		System.out.println("Codigo hotel: "+Integer.parseInt(lblIdHotel.getText()));
		Hotel hotel = manejadorHotel.getHotelId(con, Integer.parseInt(lblIdHotel.getText()));
		System.out.println(hotel);
		infoHotel.setText(hotel.getNombre()+" - "+hotel.getLocalizacion()+"\n"+hotel.getEstrellas()+" Estrellas");
	}
	
	public void setAccordionDateHabitacionService() {
		//
	}
	
	@FXML
	public void setInfoDates() throws IOException {
		
		Conector con = new Conector();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<LocalDate> registros = manejadorRegistro.getDaysWhenIsOcupped(con, habitacion.getId());
		// crear la clase que controla el archivo FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("infoDates.fxml"));
		// creamos el panel a partir del loader
		AnchorPane aux = (AnchorPane) loader.load();
		// creamos el objeto controlador que queremos usar
		InfoDatesController infoDatesController = loader.<InfoDatesController>getController();
		// usamos sus metodos
		infoDatesController.llenarListaFechas(registros);
		System.out.println("Set infoDates");
		Scene escena = new Scene(aux);
		ButtonType btn_ok = new ButtonType("OK");
		padre.setScene(escena);
		padre.setAlwaysOnTop(true);
		padre.showAndWait();
	}
	
	public void serviciosSeleccionadosHotel() {
		Conector con = new Conector();
		ManejadorServicio servicioManejador = new ManejadorServicio();
		ArrayList<Servicio> servicioHotel = servicioManejador.ServiciosPorTipo(con, TipoServicio.HOTEL);
		for (int i = 0; i < servicioHotel.size(); i++) {
			Servicio serv = servicioHotel.get(i);
			listDispoHotel.getItems().add(serv);
		}
	}
	
	public void serviciosSeleccionadosHabitacion() {
		Conector con = new Conector();
		ManejadorServicio servicioManejador = new ManejadorServicio();
		ArrayList<Servicio> servicioHotel = servicioManejador.ServiciosPorTipo(con, TipoServicio.HABITACION);
		for (int i = 0; i < servicioHotel.size(); i++) {
			Servicio serv = servicioHotel.get(i);
			listDispoHab.getItems().add(serv);
		}
	}
	
	@FXML
	public void changeServiceToSelectHotel(MouseEvent event) throws IOException {
		Servicio servicio = listDispoHotel.getSelectionModel().getSelectedItem();
		System.out.println("toSelectHotel");
		if(servicio!=null) {
			listDispoHotel.getSelectionModel().clearSelection();
			listDispoHotel.getItems().remove(getSelectedID(listDispoHotel));
			listSelectHotel.getItems().add(servicio);
		}
	}
	
	
	public void changeServiceToDispoHotel(MouseEvent  event) throws IOException{
		Servicio servicio = listSelectHotel.getSelectionModel().getSelectedItem();
		System.out.println("toDispoHotel");
		if(servicio!=null) {
			listSelectHotel.getSelectionModel().clearSelection();
			listSelectHotel.getItems().remove(getSelectedID(listSelectHotel));
			listDispoHotel.getItems().add(servicio);
		}
	}
	
	@FXML
	public void changeServicetoSelectHabitacion(MouseEvent event) throws IOException{
		Servicio servicio = listDispoHab.getSelectionModel().getSelectedItem();
		if(servicio!=null) {
			listDispoHab.getSelectionModel().clearSelection();
			listDispoHab.getItems().remove(getSelectedID(listDispoHab));
			listSelectHab.getItems().add(servicio);
		}
	}
	
	@FXML
	public void changeServiceToDispoHab(MouseEvent  event) throws IOException{
		Servicio servicio = listSelectHab.getSelectionModel().getSelectedItem();
		if(servicio!=null) {
			listSelectHab.getSelectionModel().clearSelection();
			listSelectHab.getItems().remove(getSelectedID(listSelectHab));
			listDispoHab.getItems().add(servicio);
		}
	}
	
	private int getSelectedID(ListView lista) {
		int selectedID = lista.getItems().size();
		if(selectedID!=-1) {
			return selectedID-1;
		}
		return 0;
	}
	
	//TODO: cuando este activo,que asigne ese objeto a la lista de la habitación.
	//cuando se haga click en reservar sumar todos los precios de los servicios y añadirlo al precio final de la habitacion.
	//TODO: tambie hacer los servicios que tiene un determinado hotel, pero a modo de informacion, no se necesita hacer check.

}
