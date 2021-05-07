package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hotel {
	
	private int id;
	private String nombre;
	private String localizacion;
	private int estrellas;
	private String descripcion;
	
	public Hotel(int id, String nombre, String localizacion, int estrellas, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.estrellas = estrellas;
		this.descripcion = descripcion;
	}
	
	public Hotel(ResultSet result) {

		try {
			this.id = result.getInt("id");
			this.nombre = result.getString("nombre");
			this.localizacion = result.getString("localizacion");
			this.estrellas = result.getInt("estrellas");
			this.descripcion = result.getString("descripcion");
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", localizacion=" + localizacion + ", estrellas=" + estrellas
				+ ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	

}
