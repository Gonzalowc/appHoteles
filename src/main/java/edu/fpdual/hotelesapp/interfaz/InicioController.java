package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.LinkedHashMap;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorHotel;

public class InicioController {
	
	public void rellenarLocalizaciones() throws IOException{
		ManejadorHotel manejadorHotel = new ManejadorHotel();
		Conector con = new Conector();
		LinkedHashMap<String,Integer> ciudades = manejadorHotel.listaHotelesOrdenCantidadCiudad(con);
		
		
		
	}
	
	
}
