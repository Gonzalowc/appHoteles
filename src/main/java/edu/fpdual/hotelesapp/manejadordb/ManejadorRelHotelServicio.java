package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Servicio;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class ManejadorRelHotelServicio {

	public void addConjuntoServiciosHotel(Connection con2, int IdHotel, ObservableList<Servicio> serviciosHotel,
			String serviciosID) {
		String values = "";
		for (int i = 0; i < serviciosHotel.size(); i++) {
			if(i==serviciosHotel.size()-1) {
				values+= "("+IdHotel+", "+serviciosHotel.get(i).getId()+",'"+serviciosID+"');";
			}else {
				values+= "("+IdHotel+", "+serviciosHotel.get(i).getId()+",'"+serviciosID+"'), ";
			}
			
		}
		String sql = "INSERT INTO Rel_hotel_servicio(`id_hotel`, `id_servicio`,`servicio_registro_id`) VALUES"+values;
		try(PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.execute();
			System.out.println("Relacion Habitacion servicios Cargados Correctamente");
		} catch (SQLException e) {
			System.out.println("ERROR: Relacion Habitacion servicios(addConjuntoServiciosHabitacion)");
			e.printStackTrace();
		}
		
	}

}
