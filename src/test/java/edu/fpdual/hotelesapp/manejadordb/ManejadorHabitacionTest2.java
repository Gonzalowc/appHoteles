package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Habitacion;

@ExtendWith(MockitoExtension.class)
class ManejadorHabitacionTest2 {
	
	@InjectMocks
	ManejadorHabitacion mh;

	@Mock
	private Conector c;
	
	@Mock
	private Connection c1;
	
    
	@Mock
    private PreparedStatement stmt;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	private void setUp() throws Exception {
		 	MockitoAnnotations.initMocks(this);
			when(c1.createStatement()).thenReturn(stmt);
			when(c.getMySQLConnection()).thenReturn(c1);
			
	}

	@Test
	public void testListaHabitaciones() throws SQLException {
		
		Mockito.when(c1.createStatement()).thenReturn(stmt);
		Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs);
		Mockito.when(rs.getInt("id")).thenReturn(1);
		
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
		
		
		ArrayList<Habitacion> Habitaciones = mh.listaHabitaciones(c);
		
		assertNotNull(Habitaciones);
	}
	
	@Test
	public void testListaHabitacionesFail() throws SQLException {		
		when(stmt.executeQuery(Mockito.anyString())).thenThrow(new SQLException());
		assertNull(mh.listaHabitaciones(c));
	}

}
