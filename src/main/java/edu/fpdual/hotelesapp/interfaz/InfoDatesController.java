package edu.fpdual.hotelesapp.interfaz;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class InfoDatesController {
	@FXML
	private ListView<LocalDate> listaFechas;
	
	public void llenarListaFechas(ArrayList<LocalDate> fechas) {
		for (int i = 0; i < fechas.size(); i++) {
			listaFechas.getItems().add(fechas.get(i));
		}
	}

}
