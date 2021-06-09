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

public class ReservaRegistroController {
	@FXML
	private TableView<Registro> tabla;
	@FXML
	private TableColumn<Registro, String> id_Registro;
	@FXML
	private TableColumn<Registro, String> nombreHotel;
	@FXML
	private TableColumn<Registro, String> localizacion;
	@FXML
	private TableColumn<Registro, String> estrellas;
	@FXML
	private TableColumn<Registro, String> id_Habitacion;
	@FXML
	private TableColumn<Registro, String> numPersonas;
	@FXML
	private TableColumn<Registro, String> fechaEntrada;
	@FXML
	private TableColumn<Registro, String> fechaSalida;
	@FXML
	private TableColumn<Registro, String> precio;
	@FXML
	private TableColumn<Registro, String> id_Services;
	@FXML
	private TableColumn<Registro, String> opciones;

	private Usuario user;

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUser() {
		return user;
	}

	public void rellenarTabla() {
		// crear observableList de Registro
		ObservableList<Registro> registros = FXCollections.observableArrayList();
		Conector con = new Conector();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		ArrayList<Registro> registrosDB = manejadorRegistro.getRegistrosPorUsuario(con, user.getNombre());
		for (int i = 0; i < registrosDB.size(); i++) {
			registros.add(registrosDB.get(i));
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
		}
		
		tabla.setItems(registros);
		fechaEntrada.setCellFactory(new Callback<TableColumn<Registro,String>, TableCell<Registro,String>>(){
	        @Override
	        public TableCell<Registro, String> call(TableColumn<Registro, Integer> fechaEntrada) {
	            return new TableCell<Registro,String>(){
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);

	                    if (item != null){
	                        if (comprobarFecha(item)<0){
	                            setStyle("-fx-background-color: red;");
	                        }
	                        else if (comprobarFecha(item)>0){
	                            setStyle("-fx-background-color: green;");
	                        }
	                    }

	                }
	            };
	        }
	    });
	}

	public int comprobarFecha(String fecha) {
		ArrayList<String> fechaSeparada = (ArrayList<String>) Arrays.asList(fecha.split("-"));
		LocalDate fechaComprobar =  LocalDate.of(Integer.parseInt(fechaSeparada.get(0)),Integer.parseInt(fechaSeparada.get(1)),Integer.parseInt(fechaSeparada.get(2)));
		LocalDate ahora = LocalDate.now();
				
		return fechaComprobar.compareTo(ahora);
	}

}
