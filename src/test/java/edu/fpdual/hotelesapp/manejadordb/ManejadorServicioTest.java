package edu.fpdual.hotelesapp.manejadordb;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import edu.fpdual.hotelesapp.objetos.Servicio;
import edu.fpdual.hotelesapp.objetos.TipoServicio;

@ExtendWith(MockitoExtension.class)
class ManejadorServicioTest {

	@InjectMocks
	ManejadorServicio ms;

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
	public void testNuevoServicio() {
		ms.nuevoServicio(c, "premium", 42.2, TipoServicio.HABITACION);
	}
	
	@Test
	public void testNuevoServicioFail() throws SQLException {
		when(c1.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
		ms.nuevoServicio(c, "premium", 42.2, TipoServicio.HABITACION);
	}
	
	@Test
	public void testServiciosPorTipo() throws SQLException {
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
		
		ArrayList<Servicio> servicios = ms.ServiciosPorTipo(c, TipoServicio.HOTEL);
		
		assertNotNull(servicios);
	}
	
	@Test
	public void testServiciosPorTipoFail() throws SQLException {
		
		when(stmt.executeQuery()).thenThrow(new SQLException());
		assertNull(ms.ServiciosPorTipo(c, TipoServicio.HABITACION));
	}
}
