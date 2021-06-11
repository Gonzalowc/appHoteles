package edu.fpdual.hotelesapp.conector;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConectorTest {

	@Test
	public void testConector() {
		Conector c = new Conector();
		assertNotNull(c);
	}
	
	@Test
	public void testGetMySQLConeection() {
		Conector c = new Conector();
		Connection c1 = c.getMySQLConnection();
		
		assertNotNull(c1);
	}

}
