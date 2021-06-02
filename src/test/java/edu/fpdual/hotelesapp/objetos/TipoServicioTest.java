package edu.fpdual.hotelesapp.objetos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TipoServicioTest {

	@Test
	public void enumTest() {
		assertEquals("HOTEL",TipoServicio.HOTEL.name());
	}
	
	@Test
	public void enumTest2() {
		assertEquals("HABITACION",TipoServicio.HABITACION.name());
	}

}
