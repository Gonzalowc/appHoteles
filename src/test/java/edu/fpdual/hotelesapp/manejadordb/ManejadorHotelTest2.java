package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.fpdual.hotelesapp.objetos.Hotel;

class ManejadorHotelTest2 {

	@Test
	public void testAgruparSinDuplicado() {
		ManejadorHotel h2 = new ManejadorHotel();
		ArrayList<Hotel> hoteles1 = new ArrayList<Hotel>();
		Hotel h =  new Hotel("Los pajaritos","Sevilla", 4, "Elegante");;
		hoteles1.add(h);
		ArrayList<Hotel> hoteles2 = new ArrayList<Hotel>();
		hoteles2.add(h);
		
		ArrayList<Hotel> hoteles3 = h2.AgruparSinDuplicado(hoteles1, hoteles2);
		
		assertNotNull(hoteles3);
		assertEquals(1, hoteles3.size());
	}

}
