package edu.fpdual.hotelesapp.objetos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelTest {
	
	@Mock ResultSet result;
	
	 @BeforeEach
	 private void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }

	@Test
	public void testResultSetConstructor() throws SQLException {	 
	  when(result.getInt(Mockito.anyString())).thenReturn(43);
	  when(result.getString(Mockito.anyString())).thenReturn("a");
	  
	 Hotel hotel = new Hotel(result);

	 assertNotNull(hotel);
	}
	
	@Test
	public void testresultSetConstructor2() throws SQLException{
		 when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

		 assertThrows(SQLException.class,() -> new Hotel(result));
	}
	
	@Test
	public void toStringTest() {
		Hotel hotel = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		
		String string = hotel.toString();
		
		System.out.println(hotel);
		
		assertTrue(string.equals("Hotel [id=0, nombre=Pajaritos, localizacion=Sevilla, estrellas=3, descripcion=Nuevo, habitaciones=[], serviciosHotel=[]]"));
	}
	
	  @Test
	  public void testHashCode() {
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  
		  assertEquals(h.getId()+1*31,h.hashCode());
	  }
	  
	  @Test
		public void testEqualsTrue() {
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  Hotel h1 = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
			
			assertTrue(h.equals(h1));
		}

	  @Test
		public void testEqualsTrue2() {
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
			
			assertTrue(h.equals(h));
		}
	  
	  @Test
	  public void testEqualsFalse() { 
		  
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  Hotel h1 = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  
		  h1.setId(3);
	  
		  assertFalse(h.equals(h1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  String string = "Hola mundo";
	  
		  assertFalse(h.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  Hotel h1 = null;
	  
		 assertFalse(h.equals(h1)); 
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Hotel h = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  Hotel h1 = new Hotel("Pajaritos","Sevilla",3,"Nuevo");
		  h1.setId(1);
		  
		  assertEquals(-1,h.compareTo(h1),0.01);
	  }
	  
	  @Test
	  public void testGettersSetters(){
		  Hotel h = new Hotel(null, null, 0, null);
		  Habitacion h1 = new Habitacion(51, null, 1, null, null, false, 52.3);
		  ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		  habitaciones.add(h1);
		  
		  Servicio s = new Servicio(13, "Premium", 43.3, null);
		  ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		  servicios.add(s);
		  
		  h.setId(3);
		  assertEquals(3, h.getId());
		  
		  h.setNombre("Pajaritos");
		  assertNotNull(h.getNombre());
		  assertTrue(h.getNombre().equals("Pajaritos"));
		  
		  h.setLocalizacion("Sevilla");
		  assertNotNull(h.getLocalizacion());
		  assertTrue(h.getLocalizacion().equals("Sevilla"));
		  
		  h.setEstrellas(5);
		  assertEquals(5, h.getEstrellas());
		  
		  h.setDescripcion("Nuevo");
		  assertNotNull(h.getDescripcion());
		  assertTrue(h.getDescripcion().equals("Nuevo"));
		  
		  h.setHabitaciones(habitaciones);
		  assertNotNull(h.getHabitaciones());
		  assertEquals(habitaciones, h.getHabitaciones());
		  
		  h.setServiciosHotel(servicios);
		  assertNotNull(h.getServiciosHotel());
		  assertEquals(servicios, h.getServiciosHotel());
	  }
}
