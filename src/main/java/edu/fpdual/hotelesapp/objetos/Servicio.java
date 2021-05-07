package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Servicio {
	
	private int id;
	private String nombre_servicio;
	private double precio;
	private TipoServicio tipo;
	
	public Servicio(int id, String nombre_servicio, double precio, TipoServicio tipo) {
		super();
		this.id = id;
		this.nombre_servicio = nombre_servicio;
		this.precio = precio;
		this.tipo = tipo;
	}
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
	
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_servicio() {
		return nombre_servicio;
	}
	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public TipoServicio getTipo() {
		return tipo;
	}
	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre_servicio=" + nombre_servicio + ", precio=" + precio + ", tipo=" + tipo
				+ "]";
	}
	
	
	
}
