package edu.fpdual.hotelesapp.manejadordb;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Hotel;

@ExtendWith(MockitoExtension.class)
class ManejadorHotelTest3 {
	@InjectMocks
	ManejadorHotel h2;
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
	private void setUp(TestInfo info) throws Exception {
		 	MockitoAnnotations.initMocks(this);
			when(c1.createStatement()).thenReturn(stmt);
			when(c.getMySQLConnection()).thenReturn(c1);
			
			
			
	}

	@Test
	public void testListaHoteles() throws SQLException {
		Mockito.when(c1.createStatement()).thenReturn(stmt);
		Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs);
		Mockito.when(rs.getInt("id")).thenReturn(1);
		Mockito.when(rs.getString(Mockito.anyString())).thenReturn("String Prueba");
		
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
		
		
		ArrayList<Hotel> hoteles = h2.listaHoteles(c);
		
		assertNotNull(hoteles);
	}
	
	@Test
	public void testListaHotelesFail() throws SQLException {
		when(stmt.executeQuery(Mockito.anyString())).thenThrow(new SQLException());
		assertNull(h2.listaHoteles(c));
	}
	
	

}
