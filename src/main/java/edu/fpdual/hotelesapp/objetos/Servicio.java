package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase Servicio
 * @author angela.bonilla.gomez
 *
 */

@Getter
@Setter
public class Servicio implements Comparable<Servicio> {
	/**
	 * ID del servicio
	 */
	private int id;
	/**
	 * Nombre del servicio
	 */
	private String nombre_servicio;
	/**
	 * Precio del servicio a contratar
	 */
	private double precio;
	/**
	 * Tipo del servicio
	 */
	private TipoServicio tipo;
	
	/**
	 * Constructor de Servicio
	 * @param id
	 * @param nombre_servicio
	 * @param precio
	 * @param tipo
	 */
	public Servicio(int id, String nombre_servicio, double precio, TipoServicio tipo) {
		super();
		this.id = id;
		this.nombre_servicio = nombre_servicio;
		this.precio = precio;
		this.tipo = tipo;
	}
	
	/**
	 * Constructor de servicio a partir de la base de datos
	 * @param result
	 */
	public Servicio(ResultSet result) {
		
		try {
			this.id = result.getInt("id");
			this.nombre_servicio = result.getString("nombre_servicio");
			this.precio = result.getDouble("precio");
			this.tipo = (result.getString("tipo").equalsIgnoreCase("hotel")) ? TipoServicio.HOTEL : TipoServicio.HABITACION;
		} catch (SQLException e) {
			e.printStackTrace();
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
		Servicio other = (Servicio) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * ToString para mostrar los datos de la clase
	 */
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre_servicio=" + nombre_servicio + ", precio=" + precio + ", tipo=" + tipo
				+ "]";
	}
	
	/**
	 * Comparador por id
	 */
	@Override
	public int compareTo(Servicio s) {
		return this.id - s.getId();
	}
	
	
	
}
