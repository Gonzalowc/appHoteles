package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
	
	private int id;
	private String nombre;
	private String pass;
	private String dni;
	private String telefono;
	private String email;
	
	public Usuario(int id, String nombre, String pass, String dni, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}
	
	public Usuario(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.nombre = result.getString("nombre");
			this.pass = result.getString("pass");
			this.dni = result.getString("dni");
			this.telefono = result.getString("telefono");
			this.email = result.getString("email");
		}catch(SQLException e) {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", dni=" + dni + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}
	
	
	
	
	
	

}
