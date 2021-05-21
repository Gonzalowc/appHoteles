package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHabitacion;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class CardHotelController {
	@FXML
	private Label lblCodeHotel;
	@FXML
	private Label lblNombreHotel;
	@FXML
	private Label lblBtnhotel;
	@FXML
	private Label lblLocalizacion;
	@FXML
	private Label lblNumEstrella;
	@FXML
	private AnchorPane paneCardHotel;

	private BorderPane parentPane;

	public void setParentPane(BorderPane borderPane) {
		this.parentPane = borderPane;
	}

	public void setDataHotel(int codeHotel, String nombreHotel, String localizacion, int numEstrellas) {
		lblCodeHotel.setText(Integer.toString(codeHotel));
		lblNombreHotel.setText(nombreHotel);
		lblLocalizacion.setText(localizacion);
		lblNumEstrella.setText(Integer.toString(numEstrellas));
	}

	public Hotel getIntoHotel(Conector con, int codeHotel) {
		Connection con2 = con.getMySQLConnection();
		ManejadorHotel mHotel = new ManejadorHotel();
		return mHotel.getHotelId(con, codeHotel);
	}

	public int getLblCodeHotel() {
		return Integer.parseInt(lblCodeHotel.getText());
	}

	public void toListRoom() throws IOException {

		// App.cambiarVentana("aplication", StageStyle.DECORATED, true);
		// crear la clase que controla el archivo FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("aplication.fxml"));
		// creamos el panel a partir del loader
		AnchorPane aux = (AnchorPane) loader.load();
		// creamos el objeto controlador que queremos usar
		AplicationController ac = loader.<AplicationController>getController();

	}
	@FXML
	public void listaHabitaciones() {
		int idHotel = Integer.parseInt(lblCodeHotel.getText());
		Conector con = new Conector();
		Connection con2 = con.getMySQLConnection();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		List<Habitacion> habitaciones = new ManejadorHabitacion().listaHabitacionesHotel(con, idHotel);
		for (int i = 0; i < habitaciones.size(); i++) {
			try {
				Habitacion hab = habitaciones.get(i);

				// crear la clase que controla el archivo FXML
				FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRoom.fxml"));
				// creamos el panel a partir del loader
				Pane aux = (Pane) loader.load();
				// creamos el objeto controlador que queremos usar
				CardRoomController chc = loader.<CardRoomController>getController();
				// usamos sus metodos
				chc.rellenarCard(hab);

				grid.add(aux, 0, i, 1, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ScrollPane scroll = new ScrollPane();
			scroll.setContent(grid);
			parentPane.setCenter(scroll);
			//return scroll;
		}

		//return null;
	}

}
