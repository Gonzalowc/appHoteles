package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Servicio;
import edu.fpdual.hotelesapp.objetos.TipoServicio;

public class ManejadorServicio {
	public static void main(String[] args) {
		Conector con = new Conector();
		ManejadorServicio ms = new ManejadorServicio();
		ms.nuevoServicio(con, "nuevo", 30, TipoServicio.HABITACION);
	}

	public boolean nuevoServicio(Conector con, String nombreServicio, double precio, TipoServicio tipo) {
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

		return false;
	}
	
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
