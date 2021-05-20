package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;
import java.util.List;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AplicationController {

	@FXML
	private BorderPane myPanel;
	
	@FXML
	public void toHotelesVista(ActionEvent event) throws IOException{
		myPanel.setCenter(listaHoteles());
	}
	
	@FXML
	public void toNewRoomVista(ActionEvent event) throws IOException{
		myPanel.setCenter(App.loadFXML("insertRoom"));
	}
	
	@FXML
	public void toNewHotelVista(ActionEvent event) throws IOException{
		myPanel.setCenter(App.loadFXML("insertHotel"));
	}
	
	
	public GridPane listaHoteles() {
		ManejadorHotel mh = new ManejadorHotel();
		List<Hotel> hoteles = mh.listaHoteles(new Conector());
		int ANCHO = 3;
		int alto = (hoteles.size()%3 != 0 ) ?(int)(hoteles.size()/3)+1 : (int)(hoteles.size()/3);	
		GridPane grid = new GridPane();
		grid.setHgap(100);
		grid.setVgap(150);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(10,20,10,10));
		grid.borderProperty();
		int count=0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ANCHO; j++) {
				if(count<hoteles.size()) {
					Label l = new Label(hoteles.get(count).getNombre()+" ");
				grid.add(l, j, i, 1, 1);
				}
				count++;
			}
		}
		System.out.println(grid);
		return grid;
	}
	

	
}
