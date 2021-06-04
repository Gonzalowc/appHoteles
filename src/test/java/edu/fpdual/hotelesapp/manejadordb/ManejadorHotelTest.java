package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Hotel;

@ExtendWith(MockitoExtension.class)
class ManejadorHotelTest {

	@Mock
	private Conector c;
	
	@Mock
	private Connection c1;
	
    
	@Mock
    private PreparedStatement stmt;
	
	@Mock
	private ResultSet rs;
	
	private Hotel h;

	
	@BeforeEach
	public void setUp() throws Exception {
		 	MockitoAnnotations.initMocks(this);
			assertNotNull(c);
			when(c1.prepareStatement(Mockito.anyString())).thenReturn(stmt);
			when(c.getMySQLConnection()).thenReturn(c1);
			
			h = new Hotel("Los pajaritos","Sevilla", 4, "Elegante");
			h.setId(43);
			
			
	}
	
	@Test
	public void testCrearHotel() {
		ManejadorHotel h2 = new ManejadorHotel();
		
		assertTrue(h2.crearHotel(c, h));
	}
	
	@Test
	public void testCrearHotelFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.execute()).thenThrow(new SQLException());
		assertFalse(h2.crearHotel(c, h));
		
		
	}
	
	@Test
	public void testGetHotelId() throws SQLException {
		
		when(rs.getInt(Mockito.anyString())).thenReturn(43);
		when(rs.getString(Mockito.anyString())).thenReturn("Mundo");
		when(stmt.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenAnswer(new Answer<Boolean>() {

			private int count = 0;

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				if (count == 0) {
					count++;
					return true;
				} else {
					return false;
				}
			}
		});
		
		ManejadorHotel h2 = new ManejadorHotel();
		
		h2.crearHotel(c, h);
		
		Hotel h3 = h2.getHotelId(c, 43);
		
		assertEquals(43,h3.getId());
	}
	
	@Test
	public void testGetHotelIdFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.getHotelId(c, 43));
		
		
	}
}


