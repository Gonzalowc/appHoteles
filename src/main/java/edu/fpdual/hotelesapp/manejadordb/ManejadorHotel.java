package edu.fpdual.hotelesapp.manejadordb;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.DatosTarjetaCiudades;
import edu.fpdual.hotelesapp.objetos.Hotel;

/**
 * Clase Manejador Hotel para realizar todas las consultas en cuanto a hoteles
 * @author angela.bonilla.gomez
 *
 */
public class ManejadorHotel {

	/**
	 * Metodo Buscar Hotel para buscar hoteles por nombre
	 * @param con para conseguir la conexion
	 * @param nombre para buscar por ese parametro los hoteles
	 * @return Hotel hotel resultante
	 */
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
	
	/**
	 * Metodo Crear Hotel para la creacion de hoteles
	 * @param con para conseguir la conexion
	 * @param hotel objeto que se añade
	 * @return true si se añade correctamente, false en caso contrario
	 */
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
	
	
	public boolean crearHotel(Conector con, Hotel hotel, InputStream imageBlob) {
		Connection con2 = con.getMySQLConnection();
		String sql = "INSERT INTO Hotel(`nombre`,`localizacion`,`estrellas`,`descripcion`,`imagen`) VALUES(?,?,?,?,?)";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, hotel.getNombre());
			stmt.setString(2, hotel.getLocalizacion());
			stmt.setInt(3, hotel.getEstrellas());
			stmt.setString(4, hotel.getDescripcion());
			stmt.setBlob(5, imageBlob);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	

	/**
	 * Metodo Lista Hoteles para mostrar la lista de todos los hoteles disponibles
	 * @param con para conseguir la conexion
	 * @return ArrayList<Hotel> lista de hoteles
	 */
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

	/**
	 * Metodo Hoteles Por para buscar hoteles por id
	 * @param con para conseguir la conexion
	 * @param ids para buscar los hoteles por ese parametro
	 * @return ArrayList<Hotel> lista de hoteles segun id buscado
	 */
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

	/**
	 * Metodo Hoteles Ciudad para buscar hoteles segun la ciudad
	 * @param con para conseguir la conexion
	 * @param ciudad para buscar los hoteles por ese parametro
	 * @return ArrayList<Hotel> lista de hoteles segun ciudad introducida
	 */
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

	/**
	 * Metodo Hoteles Estrellas para buscar hoteles segun las estrellas que tenga
	 * @param con para conseguir la conexion
	 * @param estrellas para buscar los hoteles por ese parametro
	 * @return ArrayList<Hotel> lista de hoteles segun estrellas introducidas
	 */
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

	/**
	 * Metodo Hoteles Nombre para buscar hoteles por nombre
	 * @param con para conseguir la conexion
	 * @param nombre para buscar los hoteles por ese parametro
	 * @return ArrayList<Hotel> lista de hoteles segun nombre introducido
	 */
	public ArrayList<Hotel> hotelesNombre(Conector con, String nombre) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Hotel WHERE nombre LIKE ?";
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
	
	/**
	 * Metodo Agrupar sin duplicado para añadir los hoteles sin que existan duplicados
	 * @param actual lista de hoteles actuales
	 * @param nuevo lista de hoteles que se añaden
	 * @return ArrayList<Hotel> lista de hoteles sin duplicados
	 */
	public ArrayList<Hotel> AgruparSinDuplicado(ArrayList<Hotel> actual, ArrayList<Hotel> nuevo){
		
		TreeSet<Hotel> hoteles = new TreeSet<>();
		hoteles.addAll(actual);
		hoteles.addAll(nuevo);
		ArrayList<Hotel> hotelesAgrup = new ArrayList<>(hoteles);
		return hotelesAgrup;
	}
	
	public Hotel getHotelId(Conector con, int id) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT *  FROM Hotel WHERE id=?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setInt(1,id);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				return new Hotel(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<DatosTarjetaCiudades> listaHotelesOrdenCantidadCiudad(Conector con){
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT localizacion, COUNT(localizacion) as cantidad, SUM(estrellas) as sumEstrellas FROM Hotel GROUP BY localizacion ORDER BY COUNT(localizacion) DESC, localizacion ASC";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			ArrayList<DatosTarjetaCiudades> ciudades = new ArrayList<>();
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				ciudades.add(new DatosTarjetaCiudades(result.getString("localizacion"),result.getInt("cantidad"),result.getInt("sumEstrellas")));
			}
			return ciudades;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
