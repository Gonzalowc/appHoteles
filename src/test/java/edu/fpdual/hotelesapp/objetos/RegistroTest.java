package edu.fpdual.hotelesapp.objetos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistroTest {

	@Mock ResultSet result;
	
	 @BeforeEach
	 private void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }

	@Test
	public void testResultSetConstructor() throws SQLException {	
		
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		
	  when(result.getInt(Mockito.anyString())).thenReturn(43);
	  when(result.getDouble(Mockito.anyString())).thenReturn(43.5);
	  when(result.getDate(Mockito.anyString())).thenReturn(date);
	  
	 Registro r = new Registro(result);

	 assertNotNull(r);
	}
	
	@Test
	public void testresultSetConstructor2() throws SQLException{
		 when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

		 assertThrows(SQLException.class,() -> new Registro(result));
	}
	
	@Test
	public void testConstructor() {
		Registro r = new Registro(13, null, null, 43.2, null);
		assertNotNull(r);
	}
	
	@Test
	public void testToString() {
		Registro r = new Registro(13, null, null, 43.2, null);
		String string = r.toString();
		
		assertTrue(string.equals("Registro [id=13, habitacion=null, usuario=null, precio=43.2, fecha=null]"));
		
	}
	
	 @Test
		public void testEqualsTrue() {
		 Registro r = new Registro(13, null, null, 43.2, null);
		 Registro r1 = new Registro(13, null, null, 43.2, null);
			
			assertTrue(r.equals(r1));
		}

	  @Test
		public void testEqualsTrue2() {
		  Registro r = new Registro(13, null, null, 43.2, null);
			
			assertTrue(r.equals(r));
		}
	  
	  @Test
	  public void testEqualsFalse() { 
		  
		  Registro r = new Registro(13, null, null, 43.2, null);
		  Registro r1 = new Registro(12, null, null, 43.2, null);
		  
	
		  assertFalse(r.equals(r1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Registro r = new Registro(13, null, null, 43.2, null);
		  String string = "Hola mundo";
	  
		  assertFalse(r.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Registro r = new Registro(13, null, null, 43.2, null);
		  Registro r1 = null;
	  
		 assertFalse(r.equals(r1)); 
	  }
	
	  @Test
	  public void testHashCode() {
		  Registro r = new Registro(13, null, null, 43.2, null);
		  
		  assertEquals(r.getId()+1*31,r.hashCode());
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Registro r = new Registro(13, null, null, 43.2, null);
		  Registro r1 = new Registro(14, null, null, 43.2, null);
		  
		  assertEquals(-1,r.compareTo(r1),0.01);
	  }

	  @Test
	  public void testGettersSetters() {
		  Registro r = new Registro(0, null, null, 0, null);
		  long millis=System.currentTimeMillis();  
		  java.sql.Date date=new java.sql.Date(millis);  
		  Habitacion habitacion = new Habitacion(null, 2, null, null, false, 30, null);
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  
		  r.setId(12);
		  assertNotNull(r.getId());
		  assertEquals(12, r.getId());
		  
		  r.setPrecio(32.2);
		  assertNotNull(r.getPrecio());
		  assertEquals(32.2, r.getPrecio());
		  
		  
		  r.setFecha(date);
		  assertNotNull(r.getFecha());
		  assertEquals(date,r.getFecha());
		  
		  
		  r.setHabitacion(habitacion);
		  assertNotNull(r.getHabitacion());
		  assertEquals(habitacion, r.getHabitacion());
		  
		  r.setUsuario(u);
		  assertNotNull(r.getUsuario());
		  assertEquals(u,r.getUsuario());
		  
		  r.setId_services("1,3");
		  assertNotNull(r.getId_services());
		  assertTrue(r.getId_services().equals("1,3"));
	  }
}
