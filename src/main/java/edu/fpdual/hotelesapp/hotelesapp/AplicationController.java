package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;
import edu.fpdual.hotelesapp.objetos.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AplicationController {

	@FXML
	private BorderPane myPanel;

	@FXML
	public void toHotelesVista(ActionEvent event) throws IOException {
		myPanel.setCenter(listaHoteles());
		
	}

	
	@FXML
	public void toNewRoomVista(ActionEvent event) throws IOException{
		myPanel.setCenter(rellenarMenu());
		
	}
	
	@FXML
	public void toNewHotelVista(ActionEvent event) throws IOException{
		myPanel.setCenter(App.loadFXML("insertHotel"));	
	}
	
	


	public ScrollPane listaHoteles() {

		ManejadorHotel mh = new ManejadorHotel();
		List<Hotel> hoteles = mh.listaHoteles(new Conector());
		int ANCHO = 3;
		int alto = (hoteles.size() % 3 != 0) ? (int) (hoteles.size() / 3) + 1 : (int) (hoteles.size() / 3);
		ScrollPane scrollPane = new ScrollPane();
		
		GridPane grid = new GridPane();
		
		grid.setStyle("-fx-background-color: cornsilk;");
		grid.getStylesheets().add("grid.css");
		grid.getStyleClass().add("gridpane");
		
		//grid.setHgap(100);
		//grid.setVgap(150);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(20, 20, 10, 10));
		//grid.setId("gridpane");
		//grid.getStylesheets().add("gridpane");
		int count = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ANCHO; j++) {
				if (count < hoteles.size()) {
					try {
						Hotel h = hoteles.get(count);
						
						//crear la clase que controla el archivo FXML
						FXMLLoader loader = new FXMLLoader(getClass().getResource("cardHotel.fxml"));
						//creamos el panel a partir del loader
						Pane aux = (Pane) loader.load();
						//creamos el objeto controlador que queremos usar
						CardHotelController chc = loader.<CardHotelController>getController();
						//usamos sus metodos
						chc.setDataHotel(h.getId(), h.getNombre(), h.getLocalizacion(), h.getEstrellas());
						
						grid.add(aux, j, i, 1, 1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				count++;
			}
		}
		scrollPane.setContent(grid);
		System.out.println(scrollPane);
		return scrollPane;
	}
	
	public AnchorPane rellenarMenu()  {
		
		Conector con = new Conector();
		ManejadorHotel mho = new ManejadorHotel();
		ArrayList<Hotel> hoteles = mho.listaHoteles(con);
		
		try {
			//crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("insertRoom.fxml"));
			//creamos el panel a partir del loader
			AnchorPane aux = (AnchorPane) loader.load();
			
		
			for(Hotel h1 : hoteles) {
				
					//creamos el objeto controlador que queremos usar
					InsertRoomController irc = loader.<InsertRoomController>getController();
					//usamos sus metodos
					irc.setListHotel(h1);
				
				
			}
			
			return aux;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	

	
}
