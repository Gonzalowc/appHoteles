package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Servicio;
import javafx.collections.ObservableList;

public class ManejadorRelHabitacionServicio {
	public void addConjuntoServiciosHabitacion(Connection con2, Habitacion habitacion,
			ObservableList<Servicio> serviciosHab, String serviciosID) {

		String values = "";
		if (serviciosHab != null) {
			for (int i = 0; i < serviciosHab.size(); i++) {
				if (i == serviciosHab.size() - 1) {
					values += "(" + habitacion.getId() + ", " + serviciosHab.get(i).getId() + ",'" + serviciosID
							+ "')";
				} else {
					values += "(" + habitacion.getId() + ", " + serviciosHab.get(i).getId() + ",'" + serviciosID
							+ "'), ";
				}
				
			}
			String sql = "INSERT INTO Rel_habitacion_servicio(`id_habitacion`, `id_servicio`,`servicio_registro_id`) VALUES "
					+ values;
			try (PreparedStatement stmt = con2.prepareStatement(sql)) {
				stmt.execute();
				System.out.println("Relacion Habitacion servicios Cargados Correctamente");
			} catch (SQLException e) {
				System.out.println("ERROR: Relacion Habitacion servicios(addConjuntoServiciosHabitacion)");
				e.printStackTrace();
			}
		} else {
			System.out.println("No hay Servicios de Habitacion seleccionados");
		}

	}
}
