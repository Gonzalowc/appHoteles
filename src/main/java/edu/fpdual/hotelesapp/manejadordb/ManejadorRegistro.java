package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import edu.fpdual.hotelesapp.conector.Conector;

public class ManejadorRegistro {
	
	public ArrayList<LocalDate> getDaysWhenIsOcupped(Conector con, int id_habitacion){
		Connection con2 = con.getMySQLConnection();
		String sql ="SELECT fecha_entrada, fecha_salida FROM Registro WHERE id_habitacion=?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setInt(1, id_habitacion);
			ResultSet result = stmt.executeQuery();
			LocalDate entrada=null;
			LocalDate salida =null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ArrayList<LocalDate> diasNoDisponibles = new ArrayList<LocalDate>();
			while(result.next()) {
				String fechaE = result.getString("fecha_entrada");
				System.out.println("manejador Entrada "+fechaE);
				entrada = LocalDate.parse(fechaE,formatter);
				String fechaS = result.getString("fecha_salida");
				System.out.println("manejador Salida "+fechaS);
				salida = LocalDate.parse(fechaS,formatter);
				while(!entrada.equals(salida)) {
					diasNoDisponibles.add(entrada);
					entrada = entrada.plusDays(1);
				}
				diasNoDisponibles.add(salida);
			}
			return diasNoDisponibles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
