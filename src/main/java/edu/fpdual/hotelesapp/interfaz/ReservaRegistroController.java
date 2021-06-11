package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRegistro;
import edu.fpdual.hotelesapp.objetos.Registro;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clase para el registro de reservas
 * 
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */

public class ReservaRegistroController {
	/**
	 * Etiqueta info
	 */
	@FXML
	private Label info;
	/**
	 * Tabla de registro
	 */
	@FXML
	private TableView<Registro> tabla;
	/**
	 * Columna de tabla de registro con el campo ID de registro
	 */
	@FXML
	private TableColumn<Registro, String> id_Registro;
	/**
	 * Columna de tabla de registro con el campo nombre hotel
	 */
	@FXML
	private TableColumn<Registro, String> nombreHotel;
	/**
	 * Columna de tabla de registro con el campo localizacion
	 */
	@FXML
	private TableColumn<Registro, String> localizacion;
	/**
	 * Columna de tabla de registro con el campo estrellas
	 */
	@FXML
	private TableColumn<Registro, String> estrellas;
	/**
	 * Columna de tabla de registro con el campo id de habitacion
	 */
	@FXML
	private TableColumn<Registro, String> id_Habitacion;
	/**
	 * Columna de tabla de registro con el campo numero de personas
	 */
	@FXML
	private TableColumn<Registro, String> numPersonas;
	/**
	 * Columna de tabla de registro con el campo fecha de entrada
	 */
	@FXML
	private TableColumn<Registro, String> fechaEntrada;
	/**
	 * Columna de tabla de registro con el campo fecha de salida
	 */
	@FXML
	private TableColumn<Registro, String> fechaSalida;
	/**
	 * Columna de tabla de registro con el campo precio
	 */
	@FXML
	private TableColumn<Registro, String> precio;
	/**
	 * Columna de tabla de registro con el campo id de servicios
	 */
	@FXML
	private TableColumn<Registro, String> id_Services;
	/**
	 * Campo de texto de Hotel
	 */
	@FXML
	private TextField txtnHotel;
	/**
	 * Campo de texto de localizacion
	 */
	@FXML
	private TextField txtLocalizacion;
	/**
	 * Campo de texto de estrellas
	 */
	@FXML
	private TextField txtEstrellas;
	/**
	 * Campo de texto de personas
	 */
	@FXML
	private TextField txtPersonas;
	/**
	 * Campo de texto de fecha entrada
	 */
	@FXML
	private TextField txtFechEntry;
	/**
	 * Campo de texto de fecha salida
	 */
	@FXML
	private TextField txtFechExit;
	/**
	 * Campo de texto de precio
	 */
	@FXML
	private TextField txtPrecio;
	/**
	 * Boton para activar campos
	 */
	@FXML
	private Button btnActivarCampos;
	/**
	 * Boton para guardar cambios
	 */
	@FXML
	private Button btnGuardarCambios;
	/**
	 * Boton para borrar registro
	 */
	@FXML
	private Button btnBorrarRegistro;
	/**
	 * Lista de registros
	 */
	private ObservableList<Registro> registros = FXCollections.observableArrayList();
	/**
	 * Usuario
	 */
	private Usuario user;
	/**
	 * Escenario padre
	 */
	private Stage padre = new Stage();

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUser() {
		return user;
	}

	/**
	 * Metodo para rellenar la tabla de registro
	 */
	public void rellenarTabla() {
		// crear observableList de Registro

		Conector con = new Conector();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<Registro> registrosDB = manejadorRegistro.getRegistrosPorUsuario(con, user.getNombre());
		for (int i = 0; i < registrosDB.size(); i++) {
			registros.add(registrosDB.get(i));
		}
		id_Registro.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreHotel.setCellValueFactory(new PropertyValueFactory<>("nombreHotel"));
		localizacion.setCellValueFactory(new PropertyValueFactory<>("localizacion"));
		estrellas.setCellValueFactory(new PropertyValueFactory<>("estrellas"));
		id_Habitacion.setCellValueFactory(new PropertyValueFactory<>("id_Habitacion"));
		numPersonas.setCellValueFactory(new PropertyValueFactory<>("num_personas"));
		fechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fecha_Entrada"));
		fechaSalida.setCellValueFactory(new PropertyValueFactory<>("fecha_salida"));
		precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		id_Services.setCellValueFactory(new PropertyValueFactory<>("id_Services"));
		// Rellenar tabla
		tabla.setItems(registros);
	}

	/**
	 * Metodo para comprobar la fecha
	 * 
	 * @param fecha que se introduce
	 * @return La comparacion de la fecha
	 */
	public int comprobarFecha(String fecha) {
		ArrayList<String> fechaSeparada = (ArrayList<String>) Arrays.asList(fecha.split("-"));
		LocalDate fechaComprobar = LocalDate.of(Integer.parseInt(fechaSeparada.get(0)),
				Integer.parseInt(fechaSeparada.get(1)), Integer.parseInt(fechaSeparada.get(2)));
		LocalDate ahora = LocalDate.now();

		return fechaComprobar.compareTo(ahora);
	}

	/**
	 * Genera un panel con la lista de fechas No disponibles
	 * 
	 * @throws IOException Error
	 */
	@FXML
	public void setInfoDates() throws IOException {
		Registro registroSelect = tabla.getSelectionModel().getSelectedItem();
		Conector con = new Conector();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<LocalDate> registros = manejadorRegistro.getDaysWhenIsOcupped(con, registroSelect.getId_habitacion());
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
		padre.setScene(escena);
		padre.setAlwaysOnTop(true);
		padre.showAndWait();
	}

	/**
	 * Rellena el formulario con los datos del registro que se haya seleccionado en
	 * la tabla
	 * 
	 * @throws IOException Error
	 */
	@FXML
	public void rellenarFormulario() throws IOException {
		Registro registroSelect = tabla.getSelectionModel().getSelectedItem();
		System.out.println(registroSelect);
		info.setVisible(false);
		txtnHotel.setText(registroSelect.getNombreHotel());
		txtLocalizacion.setText(registroSelect.getLocalizacion());
		txtEstrellas.setText(Integer.toString(registroSelect.getEstrellas()));
		txtPersonas.setText(Integer.toString(registroSelect.getNum_personas()));
		txtFechEntry.setText(registroSelect.getFecha_Entrada());
		txtFechExit.setText(registroSelect.getFecha_salida());
		txtPrecio.setText(Double.toString(registroSelect.getPrecio()));
		btnActivarCampos.setVisible(true);
		btnBorrarRegistro.setVisible(true);
		btnGuardarCambios.setVisible(false);
	}

	/**
	 * Activa los campos del formulario
	 * 
	 * @throws IOException Error
	 */
	@FXML
	public void activarCamposFormulario() throws IOException {
		txtnHotel.setDisable(false);
		txtLocalizacion.setDisable(false);
		txtEstrellas.setDisable(false);
		txtPersonas.setDisable(false);
		txtFechEntry.setDisable(false);
		txtFechExit.setDisable(false);
		txtPrecio.setDisable(false);
		btnActivarCampos.setVisible(false);
		btnGuardarCambios.setVisible(true);
		info.setVisible(true);
	}

	/**
	 * Desactiva los campos del formulario
	 */
	public void desactivarCamposFormulario() {
		txtnHotel.setDisable(true);
		txtLocalizacion.setDisable(true);
		txtEstrellas.setDisable(true);
		txtPersonas.setDisable(true);
		txtFechEntry.setDisable(true);
		txtFechExit.setDisable(true);
		txtPrecio.setDisable(true);
		btnActivarCampos.setVisible(false);
		btnGuardarCambios.setVisible(false);
		btnBorrarRegistro.setVisible(false);
	}

	/**
	 * Actualiza los datos del Registro y lo actualiza en la base de datos usando
	 * los servicios del servlet
	 * 
	 * @throws IOException Error
	 */
	@FXML
	public void modificarDatos() throws IOException {
		Registro registroSelect = tabla.getSelectionModel().getSelectedItem();
		registroSelect.setNombreHotel(txtnHotel.getText());
		registroSelect.setLocalizacion(txtLocalizacion.getText());
		registroSelect.setEstrellas(Integer.parseInt(txtEstrellas.getText()));
		registroSelect.setNum_personas(Integer.parseInt(txtPersonas.getText()));
		registroSelect.setFecha_Entrada(txtFechEntry.getText());
		registroSelect.setFecha_salida(txtFechExit.getText());
		registroSelect.setPrecio(Double.parseDouble(txtPrecio.getText()));
		Gson json = new Gson();
		String registroJSON = json.toJson(registroSelect);
		System.out.println(registroJSON);
		mandarURL("http://localhost:8080/appHotelesServices/api/actions/registro/modificar", registroJSON);
		desactivarCamposFormulario();
		actualizarDatoTabla(tabla.getSelectionModel().getSelectedIndex(), registroSelect);
		tabla.getSelectionModel().clearSelection();

	}

	/**
	 * Actualiza los datos de la tabla por los nuevos
	 * 
	 * @param index posicion a cambiar
	 * @param nuevo objeto con los nuevos datos
	 */
	private void actualizarDatoTabla(int index, Registro nuevo) {
		registros.set(index, nuevo);
		tabla.setItems(registros);
	}
	/**
	 * Manda un JSON por url con metodo post para la modificacion del registro
	 * @param url url del servidor
	 * @param json json para el body
	 */
	private void mandarURL(String url, String json) {
		try {
			URL server = new URL(url);
			HttpURLConnection conexionHTTP = (HttpURLConnection) server.openConnection();
			conexionHTTP.setRequestMethod("POST");
			conexionHTTP.setRequestProperty("Content-Type", "application/json; utf-8");
			conexionHTTP.setRequestProperty("Accept", "application/json");
			conexionHTTP.setDoOutput(true);

			try (OutputStream os = conexionHTTP.getOutputStream()) {
				byte[] input = json.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			conexionHTTP.connect();
			System.out.println("Mensaje Modificación: " + conexionHTTP.getResponseCode() + ": "
					+ conexionHTTP.getResponseMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Borrar un Registro usando el id del registro que está seleccionado en la tabla.
	 * @throws IOException Error
	 */
	@FXML
	public void borrarRegistro() throws IOException {
		Registro registroSelect = tabla.getSelectionModel().getSelectedItem();
		try {
			URL server = new URL(
					"http://localhost:8080/appHotelesServices/api/actions/registro/delete/" + registroSelect.getId());
			System.out.println("URL: " + server.toString());
			HttpURLConnection conexionHTTP = (HttpURLConnection) server.openConnection();
			conexionHTTP.setRequestMethod("GET");
			conexionHTTP.connect();

			System.out.println("Mensaje de borrado: " + conexionHTTP.getResponseMessage());
			conexionHTTP.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
