package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Hotel;

public class ManejadorHotel {

	public Hotel buscarHotel(Conector con, String nombre) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Hotel WHERE nombre LIKE ?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, "%" + nombre + "%");
			ResultSet result = stmt.executeQuery();

			result.beforeFirst();
			result.next();
			Hotel hotel = new Hotel(result);
			return hotel;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean crearHotel(Conector con, Hotel hotel) {
		Connection con2 = con.getMySQLConnection();
		String sql = "INSERT INTO Hotel(`nombre`,`localizacion`,`estrellas`,`descripcion`) VALUES(?,?,?,?)";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, hotel.getNombre());
			stmt.setString(2, hotel.getLocalizacion());
			stmt.setInt(3, hotel.getEstrellas());
			stmt.setString(4, hotel.getDescripcion());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Hotel> listaHoteles(Conector con) {
		Connection con2 = con.getMySQLConnection();
		try (Statement stmt = con2.createStatement()) {
			String sql = "SELECT * FROM Hotel";
			ResultSet result = stmt.executeQuery(sql);
			result.beforeFirst();
			ArrayList<Hotel> hoteles = new ArrayList<>();
			while (result.next()) {
				Hotel hotel = new Hotel(result);
				hoteles.add(hotel);
			}
			return hoteles;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Hotel> hotelesPor(Conector con, ArrayList<Integer> ids) {
		Connection con2 = con.getMySQLConnection();
		String sql = String.format("SELECT * FROM Hotel WHERE id IN (%s)",
				ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

		try (PreparedStatement stmt = con2.prepareStatement(sql)) {

			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			ArrayList<Hotel> hoteles = new ArrayList<>();
			while (result.next()) {
				Hotel hotel = new Hotel(result);
				hoteles.add(hotel);
			}
			return hoteles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Hotel> hotelesCiudad(Conector con, String ciudad) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Hotel WHERE localizacion LIKE ?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, "%" + ciudad + "%");
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();

			ArrayList<Hotel> hoteles = new ArrayList<Hotel>();
			while (result.next()) {
				Hotel h = new Hotel(result);
				hoteles.add(h);
			}
			return hoteles;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Hotel> hotelesEstrellas(Conector con, int estrellas) {
		if (estrellas > 0 && estrellas <= 5) {
			Connection con2 = con.getMySQLConnection();
			String sql = "SELECT * FROM Hotel WHERE estrellas<=?";
			try (PreparedStatement stmt = con2.prepareStatement(sql)) {
				stmt.setInt(1, estrellas);
				ResultSet result = stmt.executeQuery();

				result.beforeFirst();

				ArrayList<Hotel> hoteles = new ArrayList<Hotel>();
				while (result.next()) {
					Hotel h = new Hotel(result);
					hoteles.add(h);
				}
				return hoteles;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<Hotel> hotelesNombre(Conector con, String nombre) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Hotel WHERE nombre LIKE";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, "%"+nombre+"%");
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();

			ArrayList<Hotel> hoteles = new ArrayList<Hotel>();
			while (result.next()) {
				Hotel h = new Hotel(result);
				hoteles.add(h);
			}
			return hoteles;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public ArrayList<Hotel> AgruparSinDuplicado(ArrayList<Hotel> actual, ArrayList<Hotel> nuevo){
		
		TreeSet<Hotel> hoteles = new TreeSet<>();
		hoteles.addAll(actual);
		hoteles.addAll(nuevo);
		ArrayList<Hotel> hotelesAgrup = new ArrayList<>(hoteles);
		return hotelesAgrup;
		
	}

	private String formatInt(ArrayList<Integer> ids) {
		String cadena = ids.toString();
		String idsFormat = cadena.replace("[", "(");
		idsFormat = idsFormat.replace("]", ")");
		return idsFormat;
	}

}
