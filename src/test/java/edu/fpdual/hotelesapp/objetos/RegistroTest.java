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
		
	  when(result.getInt(Mockito.anyString())).thenReturn(43);
	  when(result.getDouble(Mockito.anyString())).thenReturn(43.5);
	  when(result.getString(Mockito.anyString())).thenReturn("A");
	  
	 Registro r = new Registro(result,true);

	 assertNotNull(r);
	}
	
	@Test
	public void testresultSetConstructor2() throws SQLException{
		 when(result.getInt(Mockito.anyString())).thenThrow(new SQLException());

		 assertThrows(SQLException.class,() -> new Registro(result,true));
	}
	
	@Test
	public void testConstructor() {
		Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		assertNotNull(r);
	}
	
	@Test
	public void testToString() {
		Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		String string = r.toString();
		System.out.println(r.toString());
		
		assertTrue(string.equals("Registro(id=13, nombreHotel=A, localizacion=Sevilla, estrellas=4, id_habitacion=6, num_personas=2, fecha_Entrada=null, fecha_salida=null, precio=43.2, nombre_usuario=Pepe, dni=11111111X, telefono=111111111, email=pepe@gmail.com, id_services=null)"));
		
	}
	
	 @Test
		public void testEqualsTrue() {
		 Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		 Registro r1 = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
			
			assertTrue(r.equals(r1));
		}

	  @Test
		public void testEqualsTrue2() {
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
			
			assertTrue(r.equals(r));
		}
	  
	  @Test
	  public void testEqualsFalse() { 
		  
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  Registro r1 = new Registro(12, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  
	
		  assertFalse(r.equals(r1)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse2() { 
		  
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  String string = "Hola mundo";
	  
		  assertFalse(r.equals(string)); 
	  
	  }
	  
	  @Test
	  public void testEqualsFalse3() {
		  
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  Registro r1 = null;
	  
		 assertFalse(r.equals(r1)); 
	  }
	
	  @Test
	  public void testHashCode() {
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  
		  assertEquals(r.getId()+1*31,r.hashCode());
	  }
	  
	  @Test
	  public void testCompareTo() {
		  Registro r = new Registro(13, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  Registro r1 = new Registro(14, "A", "Sevilla", 4, 6, 2, null, null, 43.2, "Pepe", "11111111X", "111111111", "pepe@gmail.com", null);
		  
		  assertEquals(-1,r.compareTo(r1),0.01);
	  }

	  @Test
	  public void testGettersSetters() {
		  Registro r = new Registro(0, null, null, 0, 0, 0, null, null, 0, null, null, null, null, null);
		  
		  r.setId(13);
		  assertNotNull(r.getId());
		  assertEquals(13,r.getId());
		  
		  r.setNombreHotel("A");
		  assertNotNull(r.getNombreHotel());
		  assertTrue(r.getNombreHotel().equals("A"));
		  
		  r.setLocalizacion("Sevilla");
		  assertNotNull(r.getLocalizacion());
		  assertTrue(r.getLocalizacion().equals("Sevilla"));
		  
		  r.setEstrellas(4);
		  assertNotNull(r.getEstrellas());
		  assertEquals(4,r.getEstrellas());
		  
		  r.setId_habitacion(6);
		  assertNotNull(r.getId_habitacion());
		  assertEquals(6,r.getId_habitacion());
		  
		  r.setNum_personas(2);
		  assertNotNull(r.getNum_personas());
		  assertEquals(2,r.getNum_personas());
		  
		  r.setFecha_Entrada("14-06-2021");
		  assertNotNull(r.getFecha_Entrada());
		  assertTrue(r.getFecha_Entrada().equals("14-06-2021"));
		  
		  r.setFecha_salida("20-06-2021");
		  assertNotNull(r.getFecha_salida());
		  assertTrue(r.getFecha_salida().equals("20-06-2021"));
		  
		  r.setPrecio(43.2);
		  assertNotNull(r.getPrecio());
		  assertEquals(43.2,r.getPrecio());
		  
		  r.setNombre_usuario("Pepe");
		  assertNotNull(r.getNombre_usuario());
		  assertTrue(r.getNombre_usuario().equals("Pepe"));
		  
		  r.setDni("11111111X");
		  assertNotNull(r.getDni());
		  assertTrue(r.getDni().equals("11111111X"));
		  
		  r.setTelefono("111111111");
		  assertNotNull(r.getTelefono());
		  assertTrue(r.getTelefono().equals("111111111"));
		  
		  r.setEmail("pepe@gmail.com");
		  assertNotNull(r.getEmail());
		  assertTrue(r.getEmail().equals("pepe@gmail.com"));
		  
		  r.setId_services("a");
		  assertNotNull(r.getId_services());
		  assertTrue(r.getId_services().equals("a"));
		  
	  }
}
