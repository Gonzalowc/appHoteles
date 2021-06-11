package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPTable;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Registro;
import edu.fpdual.hotelesapp.objetos.Servicio;
import javafx.collections.ObservableList;
/**
 * Clase manejador para la relacion de habitacion con servicio
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */
public class ManejadorRelHabitacionServicio {
	/**
	 * Metodo para añadir un conjunto de servicios a una habitacion
	 * @param con2 Conexion con la base de datos
	 * @param habitacion Habitacion
	 * @param serviciosHab Servicios de la habitacion
	 * @param serviciosID ID del servicio
	 */
	public void addConjuntoServiciosHabitacion(Connection con2, Habitacion habitacion,
			ObservableList<Servicio> serviciosHab, String serviciosID) {

		String values = "";
		if (serviciosHab != null) {
			for (int i = 0; i < serviciosHab.size(); i++) {
				if (i == serviciosHab.size() - 1) {
					values += "(" + habitacion.getId() + ", " + serviciosHab.get(i).getId() + ",'" + serviciosID + "')";
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

	/**
	 * Metodo para añadir los datos de los servicios de habitacion a la tabla que aparecera en el pdf
	 * @param con Conexion con la base de datos
	 * @param tablaServicios Tabla de servicios
	 * @param idRegistro ID del registro
	 */
	public void setDataTableServices(Conector con, PdfPTable tablaServicios, int idRegistro) {
		Connection con2 = con.getMySQLConnection();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		String sql = "SELECT Servicio.nombre_servicio, Servicio.precio, Servicio.tipo "
				+ "FROM `Rel_habitacion_servicio` JOIN Servicio "
				+ "ON Rel_habitacion_servicio.id_servicio = Servicio.id  "
				+ "WHERE Rel_habitacion_servicio.servicio_registro_id=?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			Registro registro = manejadorRegistro.getRegistroPorID(con, idRegistro);
			String code = registro.getId_services();
			stmt.setString(1, code);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				tablaServicios.addCell(result.getString(1));
				tablaServicios.addCell(result.getString(2));
				tablaServicios.addCell(result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
