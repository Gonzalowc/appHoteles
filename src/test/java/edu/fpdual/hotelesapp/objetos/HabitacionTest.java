package edu.fpdual.hotelesapp.objetos;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Date;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class HabitacionTest {
	
	  
	  @Mock private  ResultSet result;
	  
	 @BeforeEach
	 private void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }
	 
	@Test
	public void testToString() {
		Habitacion habitacion = new Habitacion(null, 2, null, null, false, 30, null);
		
		
		String string = habitacion.toString();
		
		
		assertTrue(string.equals("Habitacion [id=0, hotel=null, num_personas=2, fecha_entrada=null, fecha_salida=null, ocupada=false, precio=30.0, usuario=null, serviciosHabitacion=[]]"));
	}
	
	@Test
	public void testConstructor() {
		Habitacion habitacion2 = new Habitacion(51, null, 1, null, null, false, 52.3);
		assertNotNull(habitacion2);
	}
	
	@Test
	public void testEqualsTrue() {
		Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		Habitacion h1 = new Habitacion(51, null, 1, null, null, false, 52.3);
		
		assertTrue(h.equals(h1));
	}
	
	@Test
	public void testEqualsTrue2() {
		Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		
		assertTrue(h.equals(h));
	}
	
	
	  @Test
	  public void testEqualsFalse() { 
		  
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		  Habitacion h1 = new Habitacion(53, null, 1, null, null, false, 52.3);
	  
		  assertFalse(h.equals(h1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		  String string = "Hola mundo";
	  
		  assertFalse(h.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		 Habitacion h1 = null;
	  
		 assertFalse(h.equals(h1)); 
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		  Habitacion h1 = new Habitacion(53, null, 1, null, null, false, 52.3);
		  
		  assertEquals(-2,h.compareTo(h1),0.01);
	  }
	 
	  @Test
	  public void testGetters() {
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		  ArrayList<Servicio> servicios = null;
		  
		  assertEquals(51,h.getId());
		  assertTrue(h.getHotel()==null);
		  assertEquals(1,h.getNum_personas());
		  assertFalse(h.isOcupada());
		  assertTrue(h.getUsuario()==null);
		  assertTrue(h.getFecha_entrada()==null);
		  assertTrue(h.getFecha_salida()==null);
		  assertEquals(52.3,h.getPrecio());
		  assertEquals(servicios,h.getServiciosHabitacion());
	  }
	  
	  @Test
	  public void testSetters() {
		  Habitacion h = new Habitacion(0, null, 0, null, null, false, 0);
		  Hotel h1 = new Hotel("Las palomas", "Sevilla", 4, "");
		  Date d =  new Date();
		  Servicio s = new Servicio(13, "Premium", 43.3, null);
		  ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		  servicios.add(s);
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  
		  h.setId(61);
		  assertNotNull(h.getId());
		  assertEquals(61, h.getId());
		  
		  h.setHotel(h1);
		  assertNotNull(h.getHotel());
		  assertEquals(h1,h.getHotel());
		  
		  h.setFecha_entrada(d);
		  assertNotNull(h.getFecha_entrada());
		  assertEquals(d,h.getFecha_entrada());
		  
		  h.setFecha_salida(d);
		  assertNotNull(h.getFecha_salida());
		  assertEquals(d,h.getFecha_salida());
		  
		  h.setNum_personas(4);
		  assertNotNull(h.getNum_personas());
		  assertEquals(4,h.getNum_personas());
		  
		  h.setOcupada(false);
		  assertNotNull(h.isOcupada());
		  assertFalse(h.isOcupada());
		  
		  h.setPrecio(42.3);
		  assertNotNull(h.getPrecio());
		  assertEquals(42.3, h.getPrecio());
		  
		  h.setServiciosHabitacion(servicios);
		  assertNotNull(h.getServiciosHabitacion());
		  assertEquals(servicios,h.getServiciosHabitacion());
		  
		  h.setUsuario(u);
		  assertNotNull(h.getUsuario());
		  assertEquals(u,h.getUsuario());
		  
		  

	  }
	  
	  @Test
	  public void testHashCode() {
		  Habitacion h = new Habitacion(51, null, 1, null, null, false, 52.3);
		  
		  assertEquals(h.getId()+1*31,h.hashCode());
	  }
	  

	
	
	  @Test public void testConstructorResultSet() throws SQLException {
		  
		  long millis=System.currentTimeMillis();  
		  java.sql.Date date=new java.sql.Date(millis);  
	 
	  when(result.getInt(Mockito.anyString())).thenReturn(43);
	  when(result.getDate(Mockito.anyString())).thenReturn(date);
	  when(result.getBoolean(Mockito.anyString())).thenReturn(false);
	  when(result.getDouble(Mockito.anyString())).thenReturn(5.6);
	  
	 Habitacion habitacion1 = new Habitacion(result);

	 assertNotNull(habitacion1);
	  }
	  
	  @Test public void testConstructorResultSet2() throws SQLException {
		  
		  long millis=System.currentTimeMillis();  
		  java.sql.Date date=new java.sql.Date(millis);  
	 
	  when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

	 assertThrows(SQLException.class,() -> new Habitacion(result));
	  }
	 

	
}
