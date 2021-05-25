package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Hotel;
import edu.fpdual.hotelesapp.objetos.Servicio;
import edu.fpdual.hotelesapp.objetos.TipoServicio;
/**
 * Clase Manejador Hotel Servicio para realizar todas las consultas en cuanto a la relacion de Hotel y Servicio
 * @author angela.bonilla.gomez
 *
 */
public class ManejadorHotelServicio {
	/**
	 * Clase main para ejecutar los métodos
	 * @param args
	 */
	public static void main(String[] args) {
		//ManejadorHotelServicio mhs = new ManejadorHotelServicio();
		//Conector con = new Conector();
		//Hotel h = new Hotel("", "", 0, "");
		//h.setId(1);
		Servicio s = new Servicio(1, "", 0, TipoServicio.HOTEL);
		System.out.println(s.getTipo().name());
		//System.out.println(mhs.perteneceServicioHotel(con, h, s));
	}

	/**
	 * Metodo Servicios de hotel para consultar los servicios que nos ofrece un hotel
	 * @param con para conseguir la conexion
	 * @param hotel objeto del que queremos sacar los servicios
	 * @return ArrayList<Servicio> lista de servicios segun el hotel
	 */
	public ArrayList<Servicio> ServiciosDeHotel(Conector con, Hotel hotel) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Servicio JOIN Rel_hotel_servicio ON Rel_hotel_servicio.id_servicio = Servicio.id JOIN Hotel ON Hotel.id = Rel_hotel_servicio.id_hotel WHERE Hotel.id=?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setInt(1, hotel.getId());

			ResultSet result = stmt.executeQuery();
			result.beforeFirst();

			ArrayList<Servicio> services = new ArrayList<Servicio>();
			while (result.next()) {
				Servicio service = new Servicio(result);
				if (service.getTipo().equals(TipoServicio.HOTEL)) {
					services.add(service);
				}
			}
			return services;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para añadir un Servicio a un Hotel
	 * @param con para conseguir la conexion
	 * @param servicio objeto que queremos añadir al hotel
	 * @return true si se añade correctamente, false en caso contrario
	 */
	public boolean addServicioHotel(Conector con, Servicio servicio) {
		if(servicio.getTipo().equals(TipoServicio.HOTEL)) {
			Connection con2 = con.getMySQLConnection();
		String sql ="INSERT INTO Servicio (`nombre_servicio`, `precio`, `tipo`) VALUES(?, ?, ?)";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setString(1,servicio.getNombre_servicio());
			stmt.setDouble(2, servicio.getPrecio());
			stmt.setString(3, servicio.getTipo().name());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		}else {
			return false;
		}
		
	}

	/**
	 * Metodo para consultar si un Servicio pertenece a un Hotel / si el Hotel contiene Servicios
	 * @param con para conseguir la conexion
	 * @param hotel objeto que usamos para comprobar y relacionar con servicio
	 * @param servicio objeto objeto que usamos para comprobar y relacionar con hotel
	 * @return true si el hotel contiene servicios, false en caso contrario
	 */
	public boolean perteneceServicioHotel(Conector con, Hotel hotel, Servicio servicio) {
		Connection con2 = con.getMySQLConnection();
		if (servicio.getTipo().equals(TipoServicio.HOTEL)) {
			String sql = "SELECT * FROM Rel_hotel_servicio WHERE id_hotel=? AND id_servicio=?";
			try (PreparedStatement stmt = con2.prepareStatement(sql)) {
				stmt.setInt(1, hotel.getId());
				stmt.setInt(2, servicio.getId());
				ResultSet result = stmt.executeQuery();
				result.beforeFirst();
				int count_rows = 0;
				while (result.next()) {
					count_rows++;
				}
				if (count_rows > 0) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
