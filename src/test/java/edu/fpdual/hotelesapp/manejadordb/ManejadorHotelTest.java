package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import edu.fpdual.hotelesapp.objetos.DatosTarjetaCiudades;
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
	private void setUp() throws Exception {
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
	public void testCrearHotel2() {
		
		ManejadorHotel h2 = new ManejadorHotel();
		InputStream is = this.getClass().getResourceAsStream("Hola");
		
		assertTrue(h2.crearHotel(c, h, is));
	}
	
	@Test
	public void testCrearHotelFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.execute()).thenThrow(new SQLException());
		assertFalse(h2.crearHotel(c, h));
		
		
	}
	
	@Test
	public void testCrearHotelFail2() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		InputStream is = this.getClass().getResourceAsStream("Hola");
		
		when(stmt.execute()).thenThrow(new SQLException());
		assertFalse(h2.crearHotel(c, h, is));
		
		
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
		
		assertNotNull(h3);
	}
	
	@Test
	public void testGetHotelIdFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.getHotelId(c, 43));
		
		
	}
	
	@Test
	public void testHotelesPor() throws SQLException {
		
		ArrayList<Integer> ids = new ArrayList<>();
		ids.add(4);
		ids.add(43);
		
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
		
		ArrayList<Hotel> hoteles = h2.hotelesPor(c, ids);
		
		assertNotNull(hoteles);
	}
	
	@Test
	public void testHotelesPorFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		ArrayList<Integer> ids = new ArrayList<>();
		ids.add(4);
		ids.add(43);
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.hotelesPor(c, ids));
		
	}
	
	@Test
	public void testHotelesCiudad() throws SQLException {
		
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
		
		ArrayList<Hotel> hoteles = h2.hotelesCiudad(c, "Mundo");
		
		assertNotNull(hoteles);
	}
	
	@Test
	public void testHotelesCiudadFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.hotelesCiudad(c, "Mundo"));
	}
	
	@Test
	public void testHotelesNombre() throws SQLException {
		
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
		
		ArrayList<Hotel> hoteles = h2.hotelesNombre(c, "Mundo");
		
		assertNotNull(hoteles);
	}
	
	@Test
	public void testHotelesNombreFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.hotelesNombre(c, "Mundo"));
	}
	
	@Test
	public void testHotelesEstrellas() throws SQLException {
		
		when(rs.getInt(Mockito.anyString())).thenReturn(4);
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
		
		ArrayList<Hotel> hoteles = h2.hotelesEstrellas(c, 4);
		
		assertNotNull(hoteles);
	}
	
	@Test
	public void testHotelesEstrellasFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.hotelesEstrellas(c, 4));
	}
	
	@Test
	public void testBuscarHotel() throws SQLException {
		
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
		
		Hotel hotel = h2.buscarHotel(c, "Mundo");
		
		assertNotNull(hotel);
	}
	
	@Test
	public void testBuscarHotelFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.buscarHotel(c, "Mundo"));
	}
	
	@Test
	public void testListaHotelesOrdenCantidad() throws SQLException {
		when(rs.getInt(Mockito.anyString())).thenReturn(3);
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
		
		ArrayList<DatosTarjetaCiudades> dtc = h2.listaHotelesOrdenCantidadCiudad(c);
		
		assertNotNull(dtc);
	}
	
	@Test
	public void testListaHotelesOrdenCantidadFail() throws SQLException {
		ManejadorHotel h2 = new ManejadorHotel();
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(h2.listaHotelesOrdenCantidadCiudad(c));
	}
	
	
	
}


