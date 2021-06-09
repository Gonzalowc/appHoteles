package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 * Clase Aplication Controller
 * 
 * @author angela.bonilla.gomez
 *
 */
public class AplicationController {
	/**
	 * Panel principal
	 */
	@FXML
	private BorderPane myPanel;
	private ManejadorHotel mh = new ManejadorHotel();
	private ArrayList<Hotel> hoteles = mh.listaHoteles(new Conector());
	private ArrayList<Hotel> hotelesMostrar = hoteles;
	private Usuario user;
	
	public void setHotelesMostrar(ArrayList<Hotel> hotelesFiltro) {
		this.hotelesMostrar = hotelesFiltro;
	}

	public ArrayList<Hotel> getHotelesMostrar() {
		return hotelesMostrar;
	}

	public void setUsuario(Usuario usuario) {
		this.user = usuario;
	}

	public BorderPane getMyPanel() {
		return myPanel;
	}
	public void setMyPanel(BorderPane myPanel) {
		this.myPanel = myPanel;
	}
	public Usuario getUsuario() {
		return user;
	}

	/**
	 * Metodo para cambiar a la vista de Hoteles
	 * 
	 * @param event Evento de click
	 * @throws IOException
	 */
	@FXML
	public void toHotelesVista(ActionEvent event) throws IOException {
		hoteles = mh.listaHoteles(new Conector());
		hotelesMostrar = hoteles;
		myPanel.setCenter(listaHoteles(hotelesMostrar));
		myPanel.setBottom(generarBuscador());
		myPanel.setRight(null);
	}
	@FXML
	public void toPrincipalVista(ActionEvent event) throws IOException{
		System.out.println("toPrinciaplVista: AplicationController"+myPanel);
		primeraVentana();
	}

	

	@FXML
	public void toReservaRoomVista(ActionEvent event) throws IOException {
		myPanel.setCenter(rellenarMenuReservaRoom());
		myPanel.setRight(null);
		myPanel.setBottom(null);
	}

	@FXML
	public void toNewRoomVista(ActionEvent event) throws IOException {
		myPanel.setCenter(rellenarMenuNewRoom());
		myPanel.setRight(null);
		myPanel.setBottom(null);
	}

	@FXML
	public void toNewHotelVista(ActionEvent event) throws IOException {
		myPanel.setCenter(App.loadFXML("insertHotel"));
		myPanel.setRight(null);
		myPanel.setBottom(null);
	}
	@FXML
	public void toReservaRegistro(ActionEvent event) throws IOException{
		myPanel.setCenter(tablaReservas());
		myPanel.setBottom(null);
		myPanel.setRight(null);
	}

	/**
	 * Metodo para cerrarSesion
	 * 
	 * @throws IOException
	 */
	@FXML
	public void goBack() throws IOException {
		user = null;
		App.cambiarVentana("logIn", StageStyle.UNDECORATED, false);
	}

	/**
	 * Metodo para general la vista de lista de hoteles
	 * 
	 * @return scrollPane El panel completo
	 */
	public ScrollPane listaHoteles(ArrayList<Hotel> hotelesMostrar) {

		final int ANCHO = 3;
		int alto = (hotelesMostrar.size() % 3 != 0) ? (int) (hotelesMostrar.size() / 3) + 1
				: (int) (hotelesMostrar.size() / 3);
		ScrollPane scrollPane = new ScrollPane();

		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));

		int count = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ANCHO; j++) {
				if (count < hotelesMostrar.size()) {
					try {
						Hotel h = hotelesMostrar.get(count);

						// crear la clase que controla el archivo FXML
						FXMLLoader loader = new FXMLLoader(getClass().getResource("cardHotel.fxml"));
						// creamos el panel a partir del loader
						Pane aux = (Pane) loader.load();
						// creamos el objeto controlador que queremos usar
						CardHotelController chc = loader.<CardHotelController>getController();
						// usamos sus metodos
						chc.setDataHotel(h.getId(), h.getNombre(), h.getLocalizacion(), h.getEstrellas());
						chc.setImage(h.getImagen());
						chc.setUser(user);
						chc.setParentPane(myPanel);
						grid.add(aux, j, i, 1, 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				count++;
			}
		}
		scrollPane.setContent(grid);
		return scrollPane;
	}

	public AnchorPane rellenarMenuReservaRoom() {
		Conector con = new Conector();
		ManejadorHotel mho = new ManejadorHotel();
		ArrayList<Hotel> hoteles = mho.listaHoteles(con);

		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("reservaRoom.fxml"));
			// creamos el panel a partir del loader
			AnchorPane aux = (AnchorPane) loader.load();

			for (Hotel h1 : hotelesMostrar) {

				// creamos el objeto controlador que queremos usar
				InsertRoomController irc = loader.<InsertRoomController>getController();
				// usamos sus metodos
				irc.setListHotel(h1);

			}

			return aux;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AnchorPane rellenarMenuNewRoom() {

		Conector con = new Conector();
		ManejadorHotel mho = new ManejadorHotel();
		ArrayList<Hotel> hoteles = mho.listaHoteles(con);
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("insertRoom.fxml"));
			// creamos el panel a partir del loader
			AnchorPane aux = (AnchorPane) loader.load();
			for (Hotel h1 : hoteles) {
				// creamos el objeto controlador que queremos usar
				InsertRoomController irc = loader.<InsertRoomController>getController();
				// usamos sus metodos
				irc.setListHotel(h1);
			}
			return aux;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public ToolBar generarBuscador() {
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("buscadorHotel.fxml"));
			// creamos el panel a partir del loader
			ToolBar aux = (ToolBar) loader.load();

			// creamos el objeto controlador que queremos usar
			BuscadorHotelController buscadorHotelController = loader.<BuscadorHotelController>getController();
			// usamos sus metodos
			buscadorHotelController.setParent(myPanel);
			buscadorHotelController.setHoteles(hotelesMostrar);
			buscadorHotelController.setUser(user);
			return aux;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@FXML
	public void primeraVentana() throws IOException{
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
			// creamos el panel a partir del loader
			AnchorPane inicio = (AnchorPane) loader.load();

			// creamos el objeto controlador que queremos usar
			InicioController inicioController = loader.<InicioController>getController();
			// usamos sus metodos
			inicioController.setParent(myPanel);
			inicioController.setPanelCiudades(inicioController.listaCiudades());
			myPanel.setCenter(inicio);
			myPanel.setBottom(null);
			myPanel.setRight(null);
			System.out.println("primeraVentana: AplicationController"+ myPanel);
			System.out.println("Primera ventana");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public AnchorPane tablaReservas() throws IOException{
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("reservaRegistro.fxml"));
			// creamos el panel a partir del loader
			AnchorPane registros = (AnchorPane) loader.load();

			// creamos el objeto controlador que queremos usar
			ReservaRegistroController reservaRegistroController = loader.<ReservaRegistroController>getController();
			// usamos sus metodos
			reservaRegistroController.setUser(user);
			reservaRegistroController.rellenarTabla();
			return registros;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
