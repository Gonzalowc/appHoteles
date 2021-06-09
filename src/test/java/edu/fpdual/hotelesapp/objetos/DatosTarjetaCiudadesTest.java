package edu.fpdual.hotelesapp.objetos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DatosTarjetaCiudadesTest {

	@Test
	public void testConstructor() {
		DatosTarjetaCiudades dtc = new DatosTarjetaCiudades("Sevilla", 3, 4.2);
		
		assertNotNull(dtc);
		
	}
	
	@Test
	public void testToString() {
		DatosTarjetaCiudades dtc = new DatosTarjetaCiudades("Sevilla", 3, 6);
		String string = dtc.toString();
		
		assertTrue(string.equals("DatosTarjetaCiudades [localizacion=Sevilla, cantidad=3, mediaEstrellas=2.0]"));
	}
	
	@Test
	public void testGettersSetters() {
		DatosTarjetaCiudades dtc = new DatosTarjetaCiudades(null, 0, 0);
		
		dtc.setLocalizacion("Sevilla");
		assertNotNull(dtc.getLocalizacion());
		assertTrue(dtc.getLocalizacion().equals("Sevilla"));
		
		dtc.setCantidad(2);
		assertNotNull(dtc.getCantidad());
		assertEquals(2, dtc.getCantidad());
		
		dtc.setMediaEstrellas(4.2);
		assertNotNull(dtc.getMediaEstrellas());
		assertEquals(4.2, dtc.getMediaEstrellas());
	}

}
