package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BuscadorHotelController {

	/**
	 * Buscador de hoteles
	 */
	@FXML
	private TextField buscador;
	/**
	 * Titulo del panel buscar
	 */
	@FXML
	private TitledPane buscar;
	/**
	 * Panel padre
	 */
	private BorderPane parent;
	/**
	 * Lista de hoteles
	 */
	private ArrayList<Hotel> hoteles;
	/**
	 * Lista de usuarios
	 */
	private Usuario user;

	public void setParent(BorderPane aplication) {
		parent = aplication;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void setHoteles(ArrayList<Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	/**
	 * Metodo para filtrar hoteles segun el buscador
	 * @return lista de hoteles filtrados
	 */
	public ArrayList<Hotel> filtrarHoteles() {
		ArrayList<Hotel> hotelesFiltro = new ArrayList<Hotel>();
		String filtro = buscador.getText().toLowerCase();
		if (hoteles != null) {
			for (int i = 0; i < hoteles.size(); i++) {
				Hotel aux = hoteles.get(i);
				if (aux.getNombre().toLowerCase().contains(filtro)
						|| aux.getLocalizacion().toLowerCase().contains(filtro)
						|| Integer.toString(aux.getEstrellas()).toLowerCase().contains(filtro)) {
					hotelesFiltro.add(aux);
				}
			}
		}
		return hotelesFiltro;
	}

	/**
	 * Metodo para hacer la busqueda de hoteles
	 * @throws IOException
	 */
	@FXML
	public void hacerBusqueda() throws IOException {
//		// crear la clase que controla el archivo FXML
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("aplication.fxml"));
//		// creamos el panel a partir del loader
//		BorderPane aux = (BorderPane) loader.load();
//		// creamos el objeto controlador que queremos usar
//		AplicationController aplicationController = loader.<AplicationController>getController();
//		// usamos sus metodos

		ArrayList<Hotel> hotelesFilt = filtrarHoteles();
		// aplicationController.setHotelesMostrar(hotelesFilt);
		/*----------------------------------*/
		final int ANCHO = 3;
		int alto = (hotelesFilt.size() % 3 != 0) ? (int) (hotelesFilt.size() / 3) + 1 : (int) (hotelesFilt.size() / 3);
		ScrollPane scrollPane = new ScrollPane();

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		int count = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ANCHO; j++) {
				if (count < hotelesFilt.size()) {
					try {
						Hotel h = hotelesFilt.get(count);

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
						chc.setParentPane(parent);
						grid.add(aux, j, i, 1, 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				count++;
			}
		}
		scrollPane.setContent(grid);
		/*-----------------------------------------------------------------------*/

		if (parent != null) {
			parent.setCenter(scrollPane);
		}

	}

}
