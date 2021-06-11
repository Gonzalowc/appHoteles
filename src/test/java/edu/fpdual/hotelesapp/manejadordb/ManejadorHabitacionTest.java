package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import edu.fpdual.hotelesapp.objetos.Hotel;

@ExtendWith(MockitoExtension.class)
class ManejadorHabitacionTest {
	
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
			assertNotNull(c);
			when(c1.prepareStatement(Mockito.anyString())).thenReturn(stmt);
			when(c.getMySQLConnection()).thenReturn(c1);	
			
	}
	
	@Test
	public void testCrearHabitacion() {
		Hotel h2 = new Hotel("Los pajaritos","Sevilla", 4, "Elegante");
		h2.setId(43);
		Habitacion h = new Habitacion(4, h2, 3, null, null, false, 33.2);
		
		assertTrue(mh.crearHabitacion(c, h));
	}
	
	@Test
	public void testCrearHabitacionFail() throws SQLException {
		Hotel h2 = new Hotel("Los pajaritos","Sevilla", 4, "Elegante");
		h2.setId(43);
		Habitacion h = new Habitacion(4, h2, 3, null, null, false, 33.2);
		when(stmt.execute()).thenThrow(new SQLException());
		
		assertFalse(mh.crearHabitacion(c, h));
	}
	
	@Test
	public void testBuscarHabitacionLocalizacion() throws SQLException {
		
		when(rs.getInt(Mockito.anyString())).thenReturn(43);
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
		
		
		ArrayList<Habitacion> Habitaciones = mh.buscarHabitacionLocalizacion(c, "Mundo");
		
		assertNotNull(Habitaciones);
	}
	
	@Test
	public void testBuscarHabitacionLocalizacionFail() throws SQLException {
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(mh.buscarHabitacionLocalizacion(c, "Mundo"));
	}
	
	@Test
	public void testBuscarHabitacionNumPersonas() throws SQLException {
		
		when(rs.getInt(Mockito.anyString())).thenReturn(43);
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
		
		
		ArrayList<Habitacion> Habitaciones = mh.buscarHabitacionNumPersonas(c, 43);
		
		assertNotNull(Habitaciones);
	}
	
	@Test
	public void testBuscarHabitacionNumPersonasFail() throws SQLException {
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(mh.buscarHabitacionNumPersonas(c, 43));
	}
	
	@Test
	public void testBuscarHabitacionPrecio() throws SQLException {
		
		when(rs.getDouble(Mockito.anyString())).thenReturn(43.3);
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
		
		
		ArrayList<Habitacion> Habitaciones = mh.buscarHabitacionPrecio(c, 43.3);
		
		assertNotNull(Habitaciones);
	}
	
	@Test
	public void testBuscarHabitacionPrecioFail() throws SQLException {
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(mh.buscarHabitacionPrecio(c, 43.3));
	}
	
	@Test
	public void testListaHabitacionesHotel() throws SQLException {
		
		when(rs.getInt(Mockito.anyString())).thenReturn(43);
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
		
		
		ArrayList<Habitacion> Habitaciones = mh.listaHabitacionesHotel(c, 43);
		
		assertNotNull(Habitaciones);
	}
	
	@Test
	public void testListaHabitacionesHotelFail() throws SQLException {
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(mh.listaHabitacionesHotel(c, 43));
	}

}
