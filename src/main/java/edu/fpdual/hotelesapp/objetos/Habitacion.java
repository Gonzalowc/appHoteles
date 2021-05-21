package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Habitacion
 * @author angela.bonilla.gomez
 *
 */
@Getter
@Setter
public class Habitacion implements Comparable<Habitacion> {
	/**
	 * ID de la habitacion
	 */
	private int id;
	/**
	 * Hotel relacionado con la habitacion
	 */
	private Hotel hotel;
	/**
	 * Numero de personas de la habitacion
	 */
	private int num_personas;
	
	/**
	 * Fecha de entrada a la habitacion
	 */
	private Date fecha_entrada;

	/**
	 * Fecha de salida de la habitacion
	 */
	private Date fecha_salida;
	/**
	 * Si la habitacion esta ocupada o no
	 */
	private boolean ocupada;
	/**
	 * Precio de la habitacion
	 */
	private double precio;
	/**
	 * Usuario relacionado con la habitacion
	 */
	private Usuario usuario;
	/**
	 * Lista de servicios de la habitacion
	 */
	private ArrayList<Servicio> serviciosHabitacion;
	

		/**
		 * Constructor de Habitacion
		 * @param hotel
		 * @param num_personas
		 * @param fecha_entrada 
		 * @param fecha_salida
		 * @param ocupada 
		 * @param precio
		 * @param usuario
		 */
	public Habitacion( Hotel hotel, int num_personas, Date fecha_entrada, Date fecha_salida, boolean ocupada,
			double precio, Usuario usuario) {
		this.id = 0;
		this.hotel = hotel;
		this.num_personas = num_personas;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.ocupada = ocupada;
		this.precio = precio;
		this.usuario = usuario;
		serviciosHabitacion = new ArrayList<>();
	}

	/**
	 * Constructor de habitacion que no tenga un usuario relacionado
	 * @param id
	 * @param hotel
	 * @param num_personas
	 * @param fecha_entrada
	 * @param fecha_salida
	 * @param ocupada
	 * @param precio
	 */
	public Habitacion(int id, Hotel hotel, int num_personas, Date fecha_entrada, Date fecha_salida, boolean ocupada,
			double precio) {
		this.id = id;
		this.hotel = hotel;
		this.num_personas = num_personas;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.ocupada = ocupada;
		this.precio = precio;
		this.usuario = null;
	}
	
	/**
	 * Constructor de habitacion a partir de la base de datos
	 * @param result
	 */
	public Habitacion(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.hotel = null;
			this.num_personas = result.getInt("num_personas");
			this.fecha_entrada = result.getDate("fecha_entrada");//.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.fecha_salida = result.getDate("fecha_salida");//.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.ocupada = result.getBoolean("ocupada");
			this.precio = result.getDouble("precio");
			this.usuario = null;
			this.serviciosHabitacion = new ArrayList<>();
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
		Habitacion other = (Habitacion) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * ToString para mostrar los datos de la clase
	 */
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", hotel=" + hotel + ", num_personas=" + num_personas + ", fecha_entrada="
				+ fecha_entrada + ", fecha_salida=" + fecha_salida + ", ocupada=" + ocupada + ", precio=" + precio
				+ ", usuario=" + usuario + ", serviciosHabitacion=" + serviciosHabitacion + "]";
	}
	
	/**
	 * Comparador de habitaciones por id
	 */
	@Override
	public int compareTo(Habitacion h) {
		return this.id - h.getId();
	}
	
	
	
	
	
	
	
}
