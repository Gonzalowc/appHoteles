package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.DatosTarjetaCiudades;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Clase de Controlador de inicio
 * 
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 */
public class InicioController {
	/**
	 * Panel de ciudades
	 */
	@FXML
	private ScrollPane panelCiudades;
	/**
	 * Panel para cambiar
	 */
	@FXML
	private AnchorPane panelCambiar;
	/**
	 * Panel padre
	 */
	private BorderPane parent;
	/**
	 * Usuario
	 */
	private Usuario user;
	/**
	 * Lista de ciudades
	 */
	private ArrayList<DatosTarjetaCiudades> ciudades;

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUser() {
		return user;
	}

	public ArrayList<DatosTarjetaCiudades> getCiudades() {
		return ciudades;
	}

	public void setPanelCiudades(GridPane panel) {
		this.panelCiudades.setContent(panel);
	}

	public void setParent(BorderPane parent) {
		this.parent = parent;
	}

	/**
	 * Metodo para rellenar localizaciones
	 */
	public void rellenarLocalizaciones() {
		ManejadorHotel manejadorHotel = new ManejadorHotel();
		Conector con = new Conector();
		ciudades = manejadorHotel.listaHotelesOrdenCantidadCiudad(con);
	}

	/**
	 * Metodo para generar un panel con la lista de ciudades y rellenar los datos
	 * 
	 * @return GridPane panel relleno con cardCiudad
	 */
	public GridPane listaCiudades() {
		rellenarLocalizaciones();
		final int ANCHO = 3;
		int alto = (ciudades.size() % 3 != 0) ? (int) (ciudades.size() / 3) + 1 : (int) (ciudades.size() / 3);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		int count = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ANCHO; j++) {
				if (count < ciudades.size()) {
					try {
						DatosTarjetaCiudades ciudad = ciudades.get(count);

						// crear la clase que controla el archivo FXML
						FXMLLoader loader = new FXMLLoader(getClass().getResource("cardCiudad.fxml"));
						// creamos el panel a partir del loader
						Pane aux = (Pane) loader.load();
						// creamos el objeto controlador que queremos usar
						CardCiudadController cardCiudadController = loader.<CardCiudadController>getController();
						// usamos sus metodos
						cardCiudadController.setParent(parent);
						cardCiudadController.setDataCard(ciudad);
						cardCiudadController.setUser(user);
						grid.add(aux, j, i, 1, 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				count++;
			}
		}
		return grid;
	}

}
