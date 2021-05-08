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

public class ManejadorHotelServicio {
	public static void main(String[] args) {
		ManejadorHotelServicio mhs = new ManejadorHotelServicio();
		Conector con = new Conector();
		Hotel h = new Hotel("", "", 0, "");
		h.setId(1);
		Servicio s = new Servicio(1, "", 0, TipoServicio.HOTEL);
		System.out.println(mhs.perteneceServicioHotel(con, h, s));
	}

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

	public boolean addServicioHotel(Conector con, Hotel hotel, Servicio servicio) {

		return false;
	}

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
