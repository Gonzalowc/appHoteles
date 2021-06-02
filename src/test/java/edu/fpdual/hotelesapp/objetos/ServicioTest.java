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
class ServicioTest {

	@Mock ResultSet result;
	
	 @BeforeEach
	 private void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }

	@Test
	public void testResultSetConstructor() throws SQLException {	 
	  when(result.getInt(Mockito.anyString())).thenReturn(3);
	  when(result.getString(Mockito.anyString())).thenReturn("hotel");
	  when(result.getDouble(Mockito.anyString())).thenReturn(43.5);
	  
	  Servicio s = new Servicio(result);

	 assertNotNull(s);
	}
	@Test
	public void testResultSetConstructor2() throws SQLException {	 
	  when(result.getInt(Mockito.anyString())).thenReturn(3);
	  when(result.getString(Mockito.anyString())).thenReturn("habitacion");
	  when(result.getDouble(Mockito.anyString())).thenReturn(43.5);
	  
	  Servicio s = new Servicio(result);

	 assertNotNull(s);
	}
	
	@Test
	public void testresultSetConstructor3() throws SQLException{
		 when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

		 assertThrows(SQLException.class,() -> new Servicio(result));
	}
	
	@Test
	public void testToString(){
		Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		
		String string = s.toString();
		
		assertTrue(string.equals("premium - 42.3€"));
	}
	
	 @Test
		public void testEqualsTrue() {
		 Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		 Servicio s1 = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
			
			assertTrue(s.equals(s1));
		}

	  @Test
		public void testEqualsTrue2() {
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
			
			assertTrue(s.equals(s));
		}
	  
	  @Test
	  public void testEqualsFalse() { 
		  
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		  Servicio s1 = new Servicio(5, "premium", 42.3, TipoServicio.HABITACION);
	  
		  assertFalse(s.equals(s1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		  String string = "Hola mundo";
	  
		  assertFalse(s.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		  Servicio s1 = null;
	  
		 assertFalse(s.equals(s1)); 
	  }

	  @Test
	  public void testHashCode() {
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		  
		  assertEquals(s.getId()+1*31,s.hashCode());
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Servicio s = new Servicio(2, "premium", 42.3, TipoServicio.HABITACION);
		  Servicio s1 = new Servicio(3, "premium", 42.3, TipoServicio.HABITACION);
		  
		  assertEquals(-1,s.compareTo(s1),0.01);
	  }
	  
	  @Test
	  public void testGettersSetters() {
		  Servicio s = new Servicio(0, null, 0, null);
		  
		  s.setId(1);
		  assertNotNull(s.getId());
		  assertEquals(1, s.getId());
		  
		  s.setNombre_servicio("premium");
		  assertNotNull(s.getNombre_servicio());
		  assertTrue(s.getNombre_servicio().equals("premium"));
		  
		  s.setPrecio(34.2);
		  assertNotNull(s.getPrecio());
		  assertEquals(34.2, s.getPrecio());
		  
		  s.setTipo(TipoServicio.HABITACION);
		  assertNotNull(s.getTipo());
		  assertEquals(TipoServicio.HABITACION,s.getTipo());

	  }
}
