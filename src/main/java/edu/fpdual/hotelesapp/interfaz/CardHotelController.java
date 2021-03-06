package edu.fpdual.hotelesapp.interfaz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Clase Card Hotel Controller
 * 
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */
public class CardHotelController {
	/**
	 * Etiqueta de codigo de hotel
	 */
	@FXML
	private Label lblCodeHotel;
	/**
	 * Etiqueta de nombre de hotel
	 */
	@FXML
	private Label lblNombreHotel;
	/**
	 * Etiqueta de boton de hotel
	 */
	@FXML
	private Label lblBtnhotel;
	/**
	 * Etiqueta de localizacion
	 */
	@FXML
	private Label lblLocalizacion;
	/**
	 * Etiqueta de numero de estrellas
	 */
	@FXML
	private Label lblNumEstrella;
	/**
	 * Panel de carta de hotel
	 */
	@FXML
	private AnchorPane paneCardHotel;
	/**
	 * Imagen del hotel
	 */
	@FXML
	private ImageView image;
	/**
	 * Panel padre
	 */
	private BorderPane parentPane;
	/**
	 * Panel de reserva
	 */
	private AnchorPane reservaPane;
	/**
	 * Usuario
	 */
	private Usuario user;

	public void setUser(Usuario usuario) {
		this.user = usuario;
	}

	/**
	 * Metodo para asignar la ventana padre
	 * 
	 * @param borderPane Ventana padre
	 */
	public void setParentPane(BorderPane borderPane) {
		this.parentPane = borderPane;
	}

	/**
	 * Metodo para asignar la ventana de reserva.
	 * 
	 * @param borderPane Ventana Reserva.
	 * @throws IOException Error
	 */
	public void setReservaPane() throws IOException {
		AnchorPane panelLateral;
		panelLateral = (AnchorPane) App.loadFXML("reservaRoom");
		this.reservaPane = panelLateral;
	}

	/**
	 * Metodo para introducir datos del hotel en el FXML
	 * 
	 * @param codeHotel    Codigo del hotel
	 * @param nombreHotel  Nombre del hotel
	 * @param localizacion Localizacion del hotel
	 * @param numEstrellas Numero de estrellas del hotel
	 */
	public void setDataHotel(int codeHotel, String nombreHotel, String localizacion, int numEstrellas) {
		lblCodeHotel.setText(Integer.toString(codeHotel));
		lblNombreHotel.setText(nombreHotel);
		lblLocalizacion.setText(localizacion);
		lblNumEstrella.setText(Integer.toString(numEstrellas));
	}

	/**
	 * Metodo que devuelve el hotel con un codigo
	 * 
	 * @param con       Para conseguir la conexion MySQ
	 * @param codeHotel ID/Codigo del hotel
	 * @return
	 */
	public Hotel getIntoHotel(Conector con, int codeHotel) {
		Connection con2 = con.getMySQLConnection();
		ManejadorHotel mHotel = new ManejadorHotel();
		return mHotel.getHotelId(con, codeHotel);
	}

	public int getLblCodeHotel() {
		return Integer.parseInt(lblCodeHotel.getText());
	}

	public void setImage(byte[] imagenByte) {
		if (imagenByte != null) {
			Image img = new Image(new ByteArrayInputStream(imagenByte));
			image.setImage(img);
		}

	}

	/**
	 * Metodo para crear la lista de habitaciones de un hotel y cambiar la vista
	 * 
	 * @throws IOException Error
	 */
	@FXML
	public void listaHabitaciones() throws IOException {
		setReservaPane();
		int idHotel = Integer.parseInt(lblCodeHotel.getText());
		Conector con = new Conector();
		Connection con2 = con.getMySQLConnection();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		List<Habitacion> habitaciones = new ManejadorHabitacion().listaHabitacionesHotel(con, idHotel);
		for (int i = 0; i < habitaciones.size(); i++) {
			Habitacion hab = habitaciones.get(i);

			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRoom.fxml"));
			// creamos el panel a partir del loader
			Pane aux = (Pane) loader.load();
			// creamos el objeto controlador que queremos usar
			CardRoomController cardRoomController = loader.<CardRoomController>getController();
			// usamos sus metodos
			cardRoomController.rellenarCard(hab, Integer.parseInt(lblCodeHotel.getText()));
			cardRoomController.setParentAplication(parentPane);
			cardRoomController.setUsuario(user);
			cardRoomController.setReservaPane(reservaPane);
			grid.add(aux, 0, i, 1, 1);
		}
		ScrollPane scroll = new ScrollPane();
		System.out.println(parentPane);
		scroll.setContent(grid);
		parentPane.setCenter(null);
		parentPane.setCenter(scroll);
		parentPane.setRight(null);
		parentPane.setRight(reservaPane);
		parentPane.setBottom(null);
	}
}
