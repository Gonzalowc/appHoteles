package edu.fpdual.hotelesapp.interfaz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorRegistro;
import edu.fpdual.hotelesapp.objetos.Registro;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
/**
 * Clase para el registro de reservas
 * @author angela.bonilla.gomez
 *
 */
public class ReservaRegistroController {
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
	 * Columna de tabla de registro con el campo opciones
	 */
	@FXML
	private TableColumn opciones;
	/**
	 * Columna de tabla de registro con el campo estado
	 */
	@FXML
	private TableColumn estado;
	/**
	 * Usuario
	 */
	private Usuario user;

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
		ObservableList<Registro> registros = FXCollections.observableArrayList();
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
		//Rellenar tabla
		tabla.setItems(registros);
	}

	/**
	 * Metodo para comprobar la fecha
	 * @param fecha que se introduce
	 * @return La comparacion de la fecha
	 */
	public int comprobarFecha(String fecha) {
		ArrayList<String> fechaSeparada = (ArrayList<String>) Arrays.asList(fecha.split("-"));
		LocalDate fechaComprobar =  LocalDate.of(Integer.parseInt(fechaSeparada.get(0)),Integer.parseInt(fechaSeparada.get(1)),Integer.parseInt(fechaSeparada.get(2)));
		LocalDate ahora = LocalDate.now();
				
		return fechaComprobar.compareTo(ahora);
	}

}
