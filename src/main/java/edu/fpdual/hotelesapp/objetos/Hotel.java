package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase Hotel
 * @author angela.bonilla.gomez
 *
 */

@Getter
@Setter
public class Hotel implements Comparable<Hotel> {
	/**
	 * ID del hotel
	 */
	private int id;
	/**
	 * Nombre del hotel
	 */
	private String nombre;
	/**
	 * Localizacion del hotel
	 */
	private String localizacion;
	/**
	 * Estrellas del hotel
	 */
	private int estrellas;
	/**
	 * Descripcion del hotel
	 */
	private String descripcion;
	/**
	 * Lista de habitaciones que contiene el hotel
	 */
	private ArrayList<Habitacion> habitaciones;
	/**
	 * Lista de servicios del que dispone el hotel
	 */
	private ArrayList<Servicio> serviciosHotel;
	
	/**
	 * Constructor de Hotel
	 * @param nombre del hotel
	 * @param localizacion del hotel
	 * @param estrellas del hotel
	 * @param descripcion del hotel
	 */
	public Hotel(String nombre, String localizacion, int estrellas, String descripcion) {
		this.id = 0;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.estrellas = estrellas;
		this.descripcion = descripcion;
		habitaciones = new ArrayList<>();
		serviciosHotel = new ArrayList<>();
	}
	
	/**
	 * Constructor de Hotel a partir de la base de datos
	 * @param result
	 */
	public Hotel(ResultSet result) {

		try {
			this.id = result.getInt("id");
			this.nombre = result.getString("nombre");
			this.localizacion = result.getString("localizacion");
			this.estrellas = result.getInt("estrellas");
			this.descripcion = result.getString("descripcion");
			habitaciones = new ArrayList<>();
			serviciosHotel = new ArrayList<>();
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
		Hotel other = (Hotel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * ToString para mostrar los datos de la clase
	 */
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", localizacion=" + localizacion + ", estrellas=" + estrellas
				+ ", descripcion=" + descripcion + ", habitaciones=" + habitaciones + ", serviciosHotel="
				+ serviciosHotel + "]";
	}

	/**
	 * Comparador por id
	 */
	@Override
	public int compareTo(Hotel h) {
		return this.id - h.getId();
	}

	
	
	
	
	
	
	

}
