package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Servicio;
import edu.fpdual.hotelesapp.objetos.TipoServicio;

/**
 * Clase Manejador Servicio para realizar todas las consultas en cuanto a los servicios
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 */
public class ManejadorServicio {

	/**
	 * Metodo Nuevo Servicio para crear nuevos servicios
	 * @param con para conseguir la conexion
	 * @param nombreServicio para insertar un nombre al servicio
	 * @param precio para insertar un precio al servicio
	 * @param tipo para insertar el tipo de servicio
	 * @return true si se ha insertado correctamente, false en caso contrario
	 */
	public void nuevoServicio(Conector con, String nombreServicio, double precio, TipoServicio tipo) {
		Connection con2 = con.getMySQLConnection();
		String sql = "INSERT INTO Servicio(`nombre_servicio`,`precio`,`tipo`) VALUES(?,?,?)";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, nombreServicio);
			stmt.setDouble(2, precio);
			stmt.setString(3, tipo.name());
			System.out.println(tipo.name());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo Servicios por tipo para buscar servicios segun su tipo
	 * @param con para conseguir la conexion
	 * @param tipo para buscar servicios por este parametro
	 * @return ArrayList<Servicio> lista de servicios segun el tipo introducido
	 */
	public ArrayList<Servicio> ServiciosPorTipo(Conector con, TipoServicio tipo ){
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT * FROM Servicio WHERE tipo=?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setString(1, tipo.name());
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			ArrayList<Servicio> services = new ArrayList<>();
			while(result.next()) {
				services.add(new Servicio(result));
			}
			return services;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
