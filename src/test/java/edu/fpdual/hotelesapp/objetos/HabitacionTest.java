package edu.fpdual.hotelesapp.objetos;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

//import java.sql.ResultSet;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;


//@RunWith(MockitoJUnitRunner.class)
class HabitacionTest {
	
	/*
	 * @InjectMocks private Habitacion h1;
	 * 
	 * @Mock private ResultSet result;
	 */
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
	  public void testCompaerTo() {
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
	  
	  
	
	
	/*
	 * @Test public void testConstructorResultSet() {
	 * when(result.getString("")).thenReturn(new int[] { 1, 2, 3, 4, 5 });
	 * when(result.getInt("id")).thenReturn(0); }
	 */

	
}
