package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Clase Usuario
 * @author angela.bonilla.gomez
 *
 */
public class Usuario implements Comparable<Usuario>{
	/**
	 * ID de usuario
	 */
	private int id;
	/**
	 * Nombre del usuario
	 */
	private String nombre;
	/**
	 * Constraseña del usuario
	 */
	private String pass;
	/**
	 * DNI del usuario
	 */
	private String dni;
	/**
	 * Telefono del usuario
	 */
	private String telefono;
	/**
	 * Email del usuario
	 */
	private String email;
	
	/**
	 * Constructor de Usuario
	 * @param id
	 * @param nombre
	 * @param pass
	 * @param dni
	 * @param telefono
	 * @param email
	 */
	public Usuario(int id, String nombre, String pass, String dni, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}
	
	/**
	 * Constructor de usuario a partir de la base de datos
	 * @param result
	 */
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * ToString para mostrar los datos de la clase
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", dni=" + dni + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}

	/**
	 * Comparador por id
	 */
	@Override
	public int compareTo(Usuario u) {
		return this.id - u.getId();
	}
	
	
	
	
	
	

}
