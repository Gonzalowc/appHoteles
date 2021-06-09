package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase Registro
 * 
 * @author angela.bonilla.gomez
 *
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Registro implements Comparable<Registro> {
	/**
	 * ID del registro
	 */
	private int id;
	private String nombreHotel;
	private String localizacion;
	private int estrellas;
	private int id_habitacion;
	private int num_personas;
	private String fecha_Entrada;
	private String fecha_salida;
	private double precio;
	private String nombre_usuario;
	private String dni;
	private String telefono;
	private String email;
	private String id_services;

	/**
	 * Constructor de registro
	 * 
	 * @param id         del registro
	 * @param habitacion relacionada con el registro
	 * @param usuario    relacionado con el registro
	 * @param precio     de la reserva
	 * @param fecha      del registro
	 */

	/**
	 * Constructor de registro a partir de la base de datos
	 * 
	 * @param result
	 * @throws SQLException
	 */
	public Registro(ResultSet result, Boolean all) throws SQLException {
		try {
			id = result.getInt("id");
			nombreHotel = result.getString("nombreHotel");
			localizacion = result.getString("localizacion");
			estrellas = result.getInt("estrellas");
			id_habitacion = result.getInt("id_habitacion");
			num_personas = result.getInt("num_personas");
			fecha_Entrada = result.getString("fecha_Entrada");
			fecha_salida = result.getString("fecha_salida");
			precio = result.getDouble("precio");
			nombre_usuario = result.getString("nombre_usuario");
			dni = result.getString("dni");
			telefono = result.getString("telefono");
			email = result.getString("email");
			id_services = result.getString("id_services");
		} catch (SQLException e) {
			e.getStackTrace();
			throw e;
		}

	}

	/**
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Equals por id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Comparador por id
	 */
	@Override
	public int compareTo(Registro r) {
		return this.id - r.getId();
	}

}
