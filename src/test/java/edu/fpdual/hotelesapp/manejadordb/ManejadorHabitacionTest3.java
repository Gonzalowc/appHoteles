package edu.fpdual.hotelesapp.manejadordb;

import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Usuario;

@ExtendWith(MockitoExtension.class)
class ManejadorHabitacionTest3 {

	@InjectMocks
	ManejadorHabitacion mh;
	
	@Mock
	private Connection c1;
	
	@Mock
    private PreparedStatement stmt;
	
	@BeforeEach
	private void setUp() throws Exception {
		 	MockitoAnnotations.initMocks(this);
			when(c1.prepareStatement(Mockito.anyString())).thenReturn(stmt);	
			
	}
	
	@Test
	public void testAlquilarHabitacion() {
		Usuario u = new Usuario(4, "pepe", "1234", "XXXXXXXXXX", "XXXXXXXX", "XXXXXX");
		Hotel h2 = new Hotel("Los pajaritos","Sevilla", 4, "Elegante");
		h2.setId(43);
		Habitacion h = new Habitacion(4, h2, 3, null, null, false, 33.2);
		LocalDate d = java.time.LocalDate.now();
		
		mh.alquilarHabitacion(c1, d, d, h, u);
	}
	
	@Test
	public void testAlquilarHabitacionFail() throws SQLException {
		Usuario u = new Usuario(4, "pepe", "1234", "XXXXXXXXXX", "XXXXXXXX", "XXXXXX");
		Hotel h2 = new Hotel("Los pajaritos","Sevilla", 4, "Elegante");
		h2.setId(43);
		Habitacion h = new Habitacion(4, h2, 3, null, null, false, 33.2);
		LocalDate d = java.time.LocalDate.now();
		
		when(stmt.executeLargeUpdate()).thenThrow(new SQLException());
		mh.alquilarHabitacion(c1, d, d, h, u);
	}

}
