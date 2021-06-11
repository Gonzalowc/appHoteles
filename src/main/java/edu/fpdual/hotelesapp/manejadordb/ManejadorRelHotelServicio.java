package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPTable;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Registro;
import edu.fpdual.hotelesapp.objetos.Servicio;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
/**
 * Clase manejador para la relacion de servicios con hoteles
 * @author angela.bonilla.gomez
 *
 */
public class ManejadorRelHotelServicio {
	/**
	 * Metodo para añadir un conjunto de servicios a un hotel
	 * @param con2 Conexion con la base de datos
	 * @param IdHotel ID del hotel
	 * @param serviciosHotel Servicios del hotel
	 * @param serviciosID ID del servicio
	 */
	public void addConjuntoServiciosHotel(Connection con2, int IdHotel, ObservableList<Servicio> serviciosHotel,
			String serviciosID) {
		if (serviciosHotel != null) {
			String values = "";
			for (int i = 0; i < serviciosHotel.size(); i++) {
				if (i == serviciosHotel.size() - 1) {
					values += "(" + IdHotel + ", " + serviciosHotel.get(i).getId() + ",'" + serviciosID + "')";
				} else {
					values += "(" + IdHotel + ", " + serviciosHotel.get(i).getId() + ",'" + serviciosID + "'), ";
				}

			}
			String sql = "INSERT INTO Rel_hotel_servicio(`id_hotel`, `id_servicio`,`servicio_registro_id`) VALUES"
					+ values;
			try (PreparedStatement stmt = con2.prepareStatement(sql)) {
				stmt.execute();
				System.out.println("Relacion Habitacion servicios Cargados Correctamente");
			} catch (SQLException e) {
				System.out.println("ERROR: Relacion Habitacion servicios(addConjuntoServiciosHabitacion)");
				e.printStackTrace();
			}
		} else {
			System.out.println("No hay servicios de Hotel seleccionados");
		}

	}
	
	/**
	 * Metodo para añadir los datos de los servicios de hotel a la tabla que aparecera en el pdf
	 * @param con Conexion con la base de datos
	 * @param tablaServicios Tabla de servicios
	 * @param idRegistro ID del registro
	 */
	public void setDataTableServices(Conector con, PdfPTable tablaServicios, int idRegistro) {
		Connection con2 = con.getMySQLConnection();
		ManejadorRegistro manejadorRegistro = new ManejadorRegistro();
		String sql = "SELECT Servicio.nombre_servicio, Servicio.precio, Servicio.tipo "
				+ "FROM `Rel_hotel_servicio` JOIN Servicio "
				+ "ON Rel_hotel_servicio.id_servicio = Servicio.id  "
				+ "WHERE Rel_hotel_servicio.servicio_registro_id=?";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
