package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Registro {
	
	private int id;
	private Habitacion habitacion;
	private Usuario usuario;
	private double precio;
	private Date fecha;
	
	public Registro(int id, Habitacion habitacion, Usuario usuario, double precio, Date fecha) {
		this.id = id;
		this.habitacion = habitacion;
		this.usuario = usuario;
		this.precio = precio;
		this.fecha = fecha;
	}
	
	public Registro(ResultSet result) {
		try {
		this.id = result.getInt("id");
		this.habitacion = null;//result.getInt("id_habitacion");
		this.usuario = null;//result.getInt("id_usuario");
		this.precio = result.getDouble("precio");
		this.fecha = result.getDate("fecha");	
		}catch(SQLException e){
			e.getStackTrace();
		}
		
	}

	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Registro [id=" + id + ", habitacion=" + habitacion + ", usuario=" + usuario + ", precio=" + precio
				+ ", fecha=" + fecha + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

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
	

	
}
