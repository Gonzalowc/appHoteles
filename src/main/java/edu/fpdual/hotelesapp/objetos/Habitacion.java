package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase Habitacion
 * @author angela.bonilla.gomez
 *
 */
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
	private LocalDate fecha_entrada;

	/**
	 * Fecha de salida de la habitacion
	 */
	private LocalDate fecha_salida;
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
	public Habitacion( Hotel hotel, int num_personas, LocalDate fecha_entrada, LocalDate fecha_salida, boolean ocupada,
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
	public Habitacion(int id, Hotel hotel, int num_personas, LocalDate fecha_entrada, LocalDate fecha_salida, boolean ocupada,
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
			this.fecha_entrada = result.getDate("fecha_entrada").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.fecha_salida = result.getDate("fecha_salida").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.ocupada = result.getBoolean("ocupada");
			this.precio = result.getDouble("precio");
			this.usuario = null;
			this.serviciosHabitacion = new ArrayList<>();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Getters y setters 
	 * @return
	 */
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getNum_personas() {
		return num_personas;
	}
	public void setNum_personas(int num_personas) {
		this.num_personas = num_personas;
	}
	public LocalDate getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public LocalDate getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public boolean isOcupada() {
		return ocupada;
	}
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public ArrayList<Servicio> getServicios() {
		return serviciosHabitacion;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.serviciosHabitacion = servicios;
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
