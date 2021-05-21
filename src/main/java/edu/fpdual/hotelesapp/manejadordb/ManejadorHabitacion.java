package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Habitacion;
import edu.fpdual.hotelesapp.objetos.Hotel;

/**
 * Clase Manejador Habitacion para realizar todas las consultas en cuanto a habitaciones
 * @author angela.bonilla.gomez
 *
 */
public class ManejadorHabitacion {
	/**
	 * Clase main para ejecutar los métodos
	 * @param args
	 */
	public static void main(String[] args) {
		ManejadorHabitacion mh = new ManejadorHabitacion();
		Conector con  = new Conector();
		Hotel hotel = (Hotel)new ManejadorHotel().hotelesNombre(con, "Barceló").get(0);
		Date d = new Date(2021,6,5);
		
		Habitacion h = new Habitacion(hotel, 5, d , d, false, 3, null);
		mh.crearHabitacion(con, h);
	}
	
	/**
	 * Metodo Buscar Habitacion para consultar habitacion por precio
	 * @param con para conseguir la conexion
	 * @param precio para buscar por ese parametro las habitaciones
	 * @return ArrayList<Habitacion> lista de habitaciones segun el precio buscado
	 */
	public ArrayList<Habitacion> buscarHabitacionPrecio(Conector con, double precio) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Habitacion WHERE precio <= ?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setDouble(1, precio);
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			ArrayList<Habitacion> habs = new ArrayList<Habitacion>();
			while(result.next()) {
				Habitacion habitacion = new Habitacion(result);
				habs.add(habitacion);
			}
			return habs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo Buscar Habitacion dependiendo del numero de personas
	 * @param con para conseguir la conexion
	 * @param numPersona para buscar por ese parametro las habitaciones
	 * @return ArrayList<Habitacion> lista de habitaciones segun el numero de personas buscado
	 */
	public ArrayList<Habitacion> buscarHabitacionNumPersonas(Conector con, int numPersona) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Habitacion WHERE num_personas = ?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setInt(1, numPersona);
			ResultSet result = stmt.executeQuery();
			
			result.beforeFirst();
			ArrayList<Habitacion> habs = new ArrayList<Habitacion>();
			while(result.next()) {
				Habitacion habitacion = new Habitacion(result);
				habs.add(habitacion);
			}
			return habs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	 
	/**
	 * Metodo Buscar Habitacion dependiendo de la localizacion
	 * @param con para conseguir la conexion
	 * @param localizacion para buscar por ese parametro las habitaciones
	 * @return ArrayList<Habitacion> lista de habitaciones segun la localizacion buscada
	 */
	public ArrayList<Habitacion> buscarHabitacionLocalizacion(Conector con, String localizacion) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Habitacion JOIN Hotel ON Hotel.id = Habitacion.id_hotel WHERE Hotel.localizacion LIKE ?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setString(1, "%"+localizacion+"%");
			ResultSet result = stmt.executeQuery();
			
			result.beforeFirst();
			ArrayList<Habitacion> habs = new ArrayList<Habitacion>();
			while(result.next()) {
				Habitacion habitacion = new Habitacion(result);
				habs.add(habitacion);
			}
			
			return habs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo Crear Habitacion para agregar nuevas habitaciones
	 * @param con para conseguir la conexion
	 * @param habitacion para añadir ese nuevo objeto
	 * @return true si se crea y añade correctamente, false en caso contrario
	 */
	public boolean crearHabitacion(Conector con, Habitacion habitacion) {
		if(habitacion.getHotel().getId()!=0) {
			Connection con2 = con.getMySQLConnection();
			String sql = "INSERT INTO Habitacion(`id_hotel`,`num_personas`,`fecha_entrada`,`fecha_salida`,`ocupada`,`precio`,`id_usuario`) VALUES(?,?,?,?,?,?,?)";
			try(PreparedStatement stmt = con2.prepareStatement(sql)){
				stmt.setInt(1,habitacion.getHotel().getId());
				stmt.setInt(2,habitacion.getNum_personas());
				stmt.setDate(3,(Date) habitacion.getFecha_entrada());
				stmt.setDate(4,(Date) habitacion.getFecha_salida());
				stmt.setBoolean(5,habitacion.isOcupada());
				stmt.setDouble(6,habitacion.getPrecio());
				stmt.setObject(7,habitacion.getUsuario());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo Lista Habitaciones para sacar la lista de todas las habitaciones de las que se dispone
	 * @param con para conseguir la conexion
	 * @return ArrayList<Habitacion> lista de habitaciones total
	 */
	public ArrayList<Habitacion> listaHabitaciones(Conector con){
		Connection con2 = con.getMySQLConnection();
		
		try(Statement stmt = con2.createStatement()){
			String sql = "SELECT * FROM Habitacion";
			ResultSet result= stmt.executeQuery(sql);
			result.beforeFirst();
			ArrayList<Habitacion> lista_habitaciones = new ArrayList<>();
			while(result.next()) {
				Habitacion habitacion = new Habitacion(result);
				lista_habitaciones.add(habitacion);
			}
			return lista_habitaciones;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo Lista Habitaciones Hotel para sacar la lista de todas las habitaciones que pertenecen a un hotel
	 * @param con para conseguir la conexion
	 * @param codeHotel ID del hotel que contiene las habitaciones
	 * @return ArrayList<Habitacion> Lista de habitaciones de un hotel
	 */
	public ArrayList<Habitacion> listaHabitacionesHotel(Conector con, int codeHotel){
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Habitacion where id_hotel=?";
		ArrayList<Habitacion> habitaciones= new ArrayList<Habitacion>();
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setInt(1, codeHotel);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				habitaciones.add(new Habitacion(result));
			}
			return habitaciones;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
}
