package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.mail.PdfCreator;
import edu.fpdual.hotelesapp.mail.Sender;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRegistro;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRelHabitacionServicio;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRelHotelServicio;
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
import javafx.stage.Stage;
/**
 * Clase para reserva de habitaciones
 * @author angela.bonilla.gomez
 *
 */
public class ReservaRoomController {
	/**
	 * Lista de hoteles disponibles
	 */
	@FXML
	private ListView<Servicio> listDispoHotel;
	/**
	 * Lista de hoteles seleccionables
	 */
	@FXML
	private ListView<Servicio> listSelectHotel;
	/**
	 * Lista de habitaciones disponibles
	 */
	@FXML
	private ListView<Servicio> listDispoHab;
	/**
	 * Lista de habitaciones seleccionables
	 */
	@FXML
	private ListView<Servicio> listSelectHab;
	/**
	 * Etiqueta de mensaje de exito
	 */
	@FXML
	private Label msgExito;
	/**
	 * Etiqueta de info
	 */
	@FXML
	private Label info;
	/**
	 * Etiqueta de id de hotel
	 */
	@FXML
	private Label lblIdHotel;
	/**
	 * Campo de texto de numero de personas
	 */
	@FXML
	private TextField txtNumPers;
	/**
	 * Etiqueta de fecha entrada
	 */
	@FXML
	private Label lblEntry;
	/**
	 * Seleccionador de fecha de entrada
	 */
	@FXML
	private DatePicker dateEntry;
	/**
	 * Etiqueta de fecha salida
	 */
	@FXML
	private Label lblLeft;
	/**
	 * Seleccionador de fecha de salida
	 */
	@FXML
	private DatePicker dateLeft;
	/**
	 * Campo de texto de precio
	 */
	@FXML
	private TextField txtPrice;
	/**
	 * Campo de texto de usuario
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
	 * Etiqueta de info de hotel
	 */
	@FXML
	private Label infoHotel;
	/**
	 * Habitacion
	 */
	private Habitacion habitacion;
	/**
	 * Usuario
	 */
	private Usuario user;
	/**
	 * Escenario padre
	 */
	private Stage padre = new Stage();
	/**
	 * Metodo para desactivar la fecha antes de la entrada en el calendario a elegir
	 * @param event Evento de click
	 * @throws IOException
	 */
	@FXML
	public void disabledateBeforeEntry(ActionEvent event) throws IOException {
		dateLeft.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(dateEntry.getValue(), empty);
				setDisable(empty || date.compareTo(dateEntry.getValue()) < 0);
			}
		});
	}
	
	/**
	 * Metodo para introducir los datos de la reserva
	 * @param hab Habitacion
	 * @param idHotel ID del hotel
	 * @param NumPersonas Numero de personas
	 * @param price Precio
	 * @param user Usuario
	 */
	public void setData(Habitacion hab, int idHotel, int NumPersonas, double price, Usuario user) {
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

	/**
	 * Metodo para la fecha
	 * @param localDate fecha local
	 * @return
	 */
	public Date convertToDate(LocalDate localDate) {

		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		return date;
	}
	
	/**
	 * Metodo para realizar la reserva con todos los datos introducidos
	 * @throws IOException
	 */
	@FXML
	public void reserva() throws IOException {
		lblEntry.setStyle(null);
		lblLeft.setStyle(null);
		ArrayList<LocalDate> fechas = listaFechasSelected();
		Conector con = new Conector();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<LocalDate> registros = manejadorRegistro.getDaysWhenIsOcupped(con, habitacion.getId());
		boolean hayCambios = fechas.removeAll(registros);
		if (!hayCambios) {
			Connection con2 = con.getMySQLConnection();
			try {
				con2.setAutoCommit(false);
				ManejadorHabitacion manejadorHabitacion = new ManejadorHabitacion();
				saveServiciosHabitacion(con2);
				saveServiciosHotel(con2);
				manejadorHabitacion.alquilarHabitacion(con2, dateEntry.getValue(), dateLeft.getValue(), habitacion,
						user);
				con2.commit();
				msgExito.setVisible(true);

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					System.out.println("rollback");
					con2.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			LocalDate diaEntrada = dateEntry.getValue();
			String serviciosID = Integer.toString(habitacion.getId()) + user.getNombre() + diaEntrada.toString();
			crearPDFyEnviar(con,serviciosID);
			dateLeft.setValue(null);
			dateEntry.setValue(null);
		} else {
			dateLeft.setValue(null);
			dateEntry.setValue(null);
			lblEntry.setStyle("-fx-text-fill: #D00037;");
			lblLeft.setStyle("-fx-text-fill: #D00037;");
		}
	}

	/**
	 * Metodo para listar las fechas seleccionadas
	 * @return
	 */
	private ArrayList<LocalDate> listaFechasSelected() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		LocalDate entrada = dateEntry.getValue();
		LocalDate salida = dateLeft.getValue();
		while (entrada.compareTo(salida) <= 0) {
			entrada = entrada.plusDays(1);
			fechas.add(entrada);
		}
		return fechas;
	}

	/**
	 * Metodo para crear el pdf y que se envie por correo
	 * @param con Conexion con la base de datos
	 * @param serviciosID ID del servicio
	 * @return
	 */
	public int crearPDFyEnviar(Conector con, String serviciosID) {
		Sender email = new Sender();
		PdfCreator pdf = new PdfCreator();
		String ruta;
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		int id_registro = manejadorRegistro.getIdRegistro(con, serviciosID);
		try {
			ruta = pdf.generarPDF(con, id_registro);
			new Sender().send("hotelesapp@gmail.com", user.getEmail(), "Reserva realizada en AG2",
					"¡Su reserva se ha realizado con éxito!", ruta);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Metodo para desactivar las fechas anteriores a la actual
	 * @param dp Seleccionador de fecha/calendario
	 */
	public void disablePastDate(DatePicker dp) {

		dp.setDayCellFactory(picker -> new DateCell() {

			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) < 0);

			}
		});
	}
	/**
	 * Metodo para los datos del hotel
	 */
	public void setHotel() {
		Conector con = new Conector();
		ManejadorHotel manejadorHotel = new ManejadorHotel();
		System.out.println("Codigo hotel: " + Integer.parseInt(lblIdHotel.getText()));
		Hotel hotel = manejadorHotel.getHotelId(con, Integer.parseInt(lblIdHotel.getText()));
		System.out.println(hotel);
		infoHotel.setText(
				hotel.getNombre() + " - " + hotel.getLocalizacion() + "\n" + hotel.getEstrellas() + " Estrellas");
	}
	
	/**
	 * Metodo para la info de las fechas
	 * @throws IOException
	 */
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

	/**
	 * Metodo para añadir los servicios de hotel seleccionados
	 */
	public void serviciosSeleccionadosHotel() {
		Conector con = new Conector();
		ManejadorServicio servicioManejador = new ManejadorServicio();
		ArrayList<Servicio> servicioHotel = servicioManejador.ServiciosPorTipo(con, TipoServicio.HOTEL);
		for (int i = 0; i < servicioHotel.size(); i++) {
			Servicio serv = servicioHotel.get(i);
			listDispoHotel.getItems().add(serv);
		}
	}

	/**
	 * Metodo para añadir los servicios de habitacion seleccionados
	 */
	public void serviciosSeleccionadosHabitacion() {
		Conector con = new Conector();
		ManejadorServicio servicioManejador = new ManejadorServicio();
		ArrayList<Servicio> servicioHotel = servicioManejador.ServiciosPorTipo(con, TipoServicio.HABITACION);
		for (int i = 0; i < servicioHotel.size(); i++) {
			Servicio serv = servicioHotel.get(i);
			listDispoHab.getItems().add(serv);
		}
	}

	/**
	 * Metodo para guardar los servicios de habitacion
	 * @param con2 Conexion con la base de datos
	 */
	public void saveServiciosHabitacion(Connection con2) {
		ManejadorRelHabitacionServicio manejadorHabServ = new ManejadorRelHabitacionServicio();
		String serviciosID = habitacion.getId() + user.getNombre() + dateEntry.getValue().toString();
		manejadorHabServ.addConjuntoServiciosHabitacion(con2, habitacion, listSelectHab.getItems(), serviciosID);
	}

	/**
	 * Metodo para guardar los servicios del hotel
	 * @param con2 Conexion con la base de datos
	 */
	public void saveServiciosHotel(Connection con2) {
		String serviciosID = habitacion.getId() + user.getNombre() + dateEntry.getValue().toString();
		ManejadorRelHotelServicio manejadorHotelServ = new ManejadorRelHotelServicio();
		manejadorHotelServ.addConjuntoServiciosHotel(con2, Integer.parseInt(lblIdHotel.getText()),
				listSelectHotel.getItems(), serviciosID);
	}

	/**
	 * Metodo para cambiar servicios de hotel de disponible a seleccionados
	 * @param event Evento de raton
	 * @throws IOException
	 */
	@FXML
	public void changeServiceToSelectHotel(MouseEvent event) throws IOException {
		Servicio servicio = listDispoHotel.getSelectionModel().getSelectedItem();
		System.out.println("toSelectHotel");
		if (servicio != null) {
			listDispoHotel.getSelectionModel().clearSelection();
			listDispoHotel.getItems().remove(servicio);
			listSelectHotel.getItems().add(servicio);
			txtPrice.setText(Double.toString((Double.parseDouble(txtPrice.getText()) + servicio.getPrecio())));
		}
	}

	/**
	 * Metodo para cambiar servicios de hotel de seleccionados a disponibles
	 * @param event Evento de raton
	 * @throws IOException
	 */
	public void changeServiceToDispoHotel(MouseEvent event) throws IOException {
		Servicio servicio = listSelectHotel.getSelectionModel().getSelectedItem();
		System.out.println("toDispoHotel");
		if (servicio != null) {
			listSelectHotel.getSelectionModel().clearSelection();
			listSelectHotel.getItems().remove(servicio);
			listDispoHotel.getItems().add(servicio);
			txtPrice.setText(Double.toString((Double.parseDouble(txtPrice.getText()) - servicio.getPrecio())));
		}
	}
	/**
	 * Metodo para cambiar servicio de habitacion de disponibles a seleccionados
	 * @param event Evento de raton
	 * @throws IOException
	 */
	@FXML
	public void changeServicetoSelectHabitacion(MouseEvent event) throws IOException {
		Servicio servicio = listDispoHab.getSelectionModel().getSelectedItem();
		if (servicio != null) {
			listDispoHab.getSelectionModel().clearSelection();
			listDispoHab.getItems().remove(servicio);
			listSelectHab.getItems().add(servicio);
			txtPrice.setText(Double.toString((Double.parseDouble(txtPrice.getText()) + servicio.getPrecio())));
		}
	}
	/**
	 * Metodo para cambiar servicio de habitacion de seleccionados a disponibles
	 * @param event Evento de raton
	 * @throws IOException
	 */
	@FXML
	public void changeServiceToDispoHab(MouseEvent event) throws IOException {
		Servicio servicio = listSelectHab.getSelectionModel().getSelectedItem();
		if (servicio != null) {
			listSelectHab.getSelectionModel().clearSelection();
			listSelectHab.getItems().remove(servicio);
			listDispoHab.getItems().add(servicio);
			txtPrice.setText(Double.toString((Double.parseDouble(txtPrice.getText()) - servicio.getPrecio())));
		}
	}
}
