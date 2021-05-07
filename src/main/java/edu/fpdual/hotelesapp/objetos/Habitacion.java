package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Habitacion {
	private int id;
	private Hotel hotel;
	private int num_personas;
	private Date fecha_entrada;
	private Date fecha_salida;
	private boolean ocupada;
	private double precio;
	private Usuario usuario;
	
	public Habitacion(int id, Hotel hotel, int num_personas, Date fecha_entrada, Date fecha_salida, boolean ocupada,
			double precio, Usuario usuario) {
		this.id = id;
		this.hotel = hotel;
		this.num_personas = num_personas;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.ocupada = ocupada;
		this.precio = precio;
		this.usuario = usuario;
	}
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
	
	
	public Habitacion(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.hotel = null;
			this.num_personas = result.getInt("num_personas");
			this.fecha_entrada = result.getDate("fecha_entrada");
			this.fecha_salida = result.getDate("fecha_salida");
			this.ocupada = result.getBoolean("ocupada");
			this.precio = result.getDouble("precio");
			this.usuario = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
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
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
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
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", hotel=" + hotel + ", num_personas=" + num_personas + ", fecha_entrada="
				+ fecha_entrada + ", fecha_salida=" + fecha_salida + ", ocupada=" + ocupada + ", precio=" + precio
				+ ", usuario=" + usuario + "]";
	}
	
	
	
	
	
}
