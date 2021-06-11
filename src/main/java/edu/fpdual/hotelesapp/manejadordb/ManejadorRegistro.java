package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfPTable;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Registro;

public class ManejadorRegistro {

	public ArrayList<LocalDate> getDaysWhenIsOcupped(Conector con, int id_habitacion) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT fecha_entrada, fecha_salida FROM Registro WHERE id_habitacion=?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setInt(1, id_habitacion);
			ResultSet result = stmt.executeQuery();
			LocalDate entrada = null;
			LocalDate salida = null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ArrayList<LocalDate> diasNoDisponibles = new ArrayList<LocalDate>();
			while (result.next()) {
				String fechaE = result.getString("fecha_entrada");
				entrada = LocalDate.parse(fechaE, formatter);
				String fechaS = result.getString("fecha_salida");
				salida = LocalDate.parse(fechaS, formatter);
				while (!entrada.equals(salida)) {
					diasNoDisponibles.add(entrada);
					entrada = entrada.plusDays(1);
				}
				diasNoDisponibles.add(salida);
			}
			return diasNoDisponibles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setDataTablaHotel(Conector con, PdfPTable tablaHotel, int idRegistro) {
		Connection con2 = con.getMySQLConnection();
		/**
		 * Realizamos las consultas de la tabla
		 */
		String sql = "SELECT nombreHotel,localizacion,Estrellas,id_habitacion,num_personas,fecha_entrada,fecha_salida,precio FROM Registro WHERE id = ?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setInt(1, idRegistro);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				tablaHotel.addCell(result.getString(1));
				tablaHotel.addCell(result.getString(2));
				tablaHotel.addCell(result.getString(3));
				tablaHotel.addCell(result.getString(4));
				tablaHotel.addCell(result.getString(5));
				tablaHotel.addCell(result.getString(6));
				tablaHotel.addCell(result.getString(7));
				tablaHotel.addCell(result.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDataTableUser(Conector con, PdfPTable tablaUsuario, int idRegistro) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT nombre_usuario,dni,telefono,email FROM Registro WHERE id = ?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setInt(1, idRegistro);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				tablaUsuario.addCell(result.getString(1));
				tablaUsuario.addCell(result.getString(2));
				tablaUsuario.addCell(result.getString(3));
				tablaUsuario.addCell(result.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getIdRegistro(Conector con, String serviciosID) {
		Connection con2 = con.getMySQLConnection();
		String sql = "select id  from Registro where id_services=?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setString(1, serviciosID);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				return result.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public ArrayList<Registro> getRegistrosPorUsuario(Conector con, String nombreUsuario) {
		ArrayList<Registro> registros = new ArrayList<>();
		Connection con2 = con.getMySQLConnection();
		/**
		 * Realizamos las consultas de la tabla
		 */
		String sql = "SELECT * FROM Registro WHERE nombre_usuario=?";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, nombreUsuario);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Registro registro = new Registro(result);
				registros.add(registro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registros;
	}
	public Registro getRegistroPorID(Conector con, int idRegistro) {
		Connection con2 = con.getMySQLConnection();
		String sql = "Select *  from Registro where id=?";
		try(PreparedStatement stmt = con2.prepareStatement(sql)){
			stmt.setInt(1, idRegistro);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				return new Registro(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
