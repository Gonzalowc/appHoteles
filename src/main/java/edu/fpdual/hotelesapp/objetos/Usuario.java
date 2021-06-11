package edu.fpdual.hotelesapp.objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase Usuario
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 * @author g.moreno.rodriguez
 *
 */

@Getter
@Setter
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
	 * @param id de usuario
	 * @param nombre de usuario
	 * @param pass contraseña de usuario
	 * @param dni del usuario
	 * @param telefono del usuario
	 * @param email del usuario
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
	 * @throws SQLException 
	 */
	public Usuario(ResultSet result) throws SQLException {
		try {
			this.id = result.getInt("id");
			this.nombre = result.getString("nombre_usuario");
			this.pass = null;
			this.dni = result.getString("dni");
			this.telefono = result.getString("telefono");
			this.email = result.getString("email");
		}catch(SQLException e) {
			e.getStackTrace();
			throw e;
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
