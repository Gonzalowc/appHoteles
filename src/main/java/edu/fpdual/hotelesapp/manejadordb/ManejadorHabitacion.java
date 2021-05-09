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

public class ManejadorHabitacion {
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
	
	
	// Buscar por localizacion
	// SELECT * FROM Habitacion JOIN Hotel ON Hotel.id = Habitacion.id_hotel WHERE Hotel.localizacion LIKE ?; 
		
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
	
	public boolean crearHabitacion(Conector con, Habitacion habitacion) {
		Connection con2 = con.getMySQLConnection();
		String sql = "INSERT INTO Habitacion(`id_hotel`,`num_personas`,`fecha_entrada`,`fecha_salida`,`ocupada`,`precio`,`id_usuario`) VALUES(?,?,?,?,?,?,?)";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setObject(1,habitacion.getHotel());
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
		return false;
	}
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
	
}
