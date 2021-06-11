package edu.fpdual.hotelesapp.interfaz;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
/**
 * Clase Info Dates Controller
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */
public class InfoDatesController {
	/**
	 * Lista de fechas
	 */
	@FXML
	private ListView<LocalDate> listaFechas;
	/**
	 * Metodo para llenar la lista de fechas
	 * @param fechas que se facilitan para elegir
	 */
	public void llenarListaFechas(ArrayList<LocalDate> fechas) {
		for (int i = 0; i < fechas.size(); i++) {
			listaFechas.getItems().add(fechas.get(i));
		}
	}

}
