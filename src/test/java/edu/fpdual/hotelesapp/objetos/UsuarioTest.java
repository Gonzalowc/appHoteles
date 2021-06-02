package edu.fpdual.hotelesapp.objetos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
class UsuarioTest {

	@Mock ResultSet result;
	
	 @BeforeEach
	 private void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }

	@Test
	public void testResultSetConstructor() throws SQLException {	 
	  when(result.getInt(Mockito.anyString())).thenReturn(43);
	  when(result.getString(Mockito.anyString())).thenReturn("a");
	  
	 Usuario user = new Usuario(result);

	 assertNotNull(user);
	}
	
	@Test
	public void testresultSetConstructor2() throws SQLException{
		 when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

		 assertThrows(SQLException.class,() -> new Usuario(result));
	}
	
	@Test
	public void testToString() {
		Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		String s = u.toString();
		
		assertTrue(s.equals("Usuario [id=32, nombre=Pedro, pass=1234, dni=63344244R, telefono=xxxxxxxxx, email=pedro34@gmail.com]"));
	}
	
	@Test
	  public void testHashCode() {
		Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  
		  assertEquals(u.getId()+1*31,u.hashCode());
	  }
	
	 @Test
		public void testEqualsTrue() {
		 Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		 Usuario u1 = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
			
			assertTrue(u.equals(u1));
		}

	  @Test
		public void testEqualsTrue2() {
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
			
			assertTrue(u.equals(u));
		}
	  
	  @Test
	  public void testEqualsFalse() { 
		  
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  Usuario u1 = new Usuario(30, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  
	  
		  assertFalse(u.equals(u1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  String string = "Hola mundo";
	  
		  assertFalse(u.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  Usuario u1 = null;
	  
		 assertFalse(u.equals(u1)); 
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Usuario u = new Usuario(32, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  Usuario u1 = new Usuario(33, "Pedro", "1234", "63344244R", "xxxxxxxxx", "pedro34@gmail.com");
		  
		  assertEquals(-1,u.compareTo(u1),0.01);
	  }
	  
	  @Test
	  public void testGettersSetters() {
		  Usuario u = new Usuario(0, null, null, null, null, null);
		  
		  u.setId(5);
		  assertEquals(5,u.getId());
		  
		  u.setNombre("Juan");
		  assertNotNull(u.getNombre());
		  assertTrue(u.getNombre().equals("Juan"));
		  
		  u.setPass("1234");
		  assertNotNull(u.getPass());
		  assertTrue(u.getPass().equals("1234"));
		  
		  u.setDni("63344244R");
		  assertNotNull(u.getDni());
		  assertTrue(u.getDni().equals("63344244R"));
		  
		  u.setTelefono("xxxxxxxxx");
		  assertNotNull(u.getTelefono());
		  assertTrue(u.getTelefono().equals("xxxxxxxxx"));
		  
		  u.setEmail("pedro34@gmail.com");
		  assertNotNull(u.getEmail());
		  assertTrue(u.getEmail().equals("pedro34@gmail.com"));
	  }
}
