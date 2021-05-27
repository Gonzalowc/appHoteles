package edu.fpdual.hotelesapp.manejadordb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.objetos.Usuario;

/**
 * Clase Manejador Usuario para realizar todas las consultas en cuanto a los usuarios
 * @author angela.bonilla.gomez
 *
 */
public class ManejadorUsuario {
	/**
	 * Clase main para ejecutar los métodos
	 * @param args
	 */
	public static void main(String[] args) {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();

		System.out.println(mu.existeUsuario(con, "pepe"));
		System.out.println(mu.logging(con,"Gonzalo", "1234"));
	}

	/**
	 * Metodo Validar Usuario para validar los credenciales introducidos por el usuario
	 * @param con para conseguir la conexion
	 * @param usuario user de la persona que se ha registrado
	 * @param passwd contraseña de la persona que se ha registrado
	 * @return true si la informacion es correcta, false en caso contrario
	 */
	public boolean validarUsuario(Conector con, String usuario, String passwd) {

		Connection con2 = con.getMySQLConnection();

		String sql = "SELECT * FROM Usuario WHERE nombre_usuario=? AND password=? AND activo=1";
		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, usuario);
			stmt.setString(2, passwd);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				System.out.println("NombreUser"+result.getString("nombre_usuario"));
				String user = result.getString("nombre_usuario");
				String pass = result.getString("password");
				if (user.equals(usuario) && pass.equals(passwd)) {
					return true;
				} else {
					return false;
				}
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Metodo Logging para iniciar sesion como usuario
	 * @param con para conseguir la conexion
	 * @param usuario user de la persona que inicia sesion
	 * @param passwd contraseña de la persona que inicia sesion
	 * @return Usuario el usuario se registra satisfactoriamente, null con un mensaje de error en caso contrario
	 */
	public Usuario logging(Conector con, String usuario, String passwd) {
		Connection con2 = con.getMySQLConnection();
		if (validarUsuario(con, usuario, passwd)) {
			String sql = "SELECT id,nombre_usuario,dni,telefono,email FROM Usuario WHERE nombre_usuario =? AND password=?";

			try (PreparedStatement stmt = con2.prepareStatement(sql)) {

				stmt.setString(1, usuario);
				stmt.setString(2, passwd);
				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					System.out.println(
							"Usuario Iniciado.--->" + result.getInt("id") + " " + result.getString("nombre_usuario"));
					return new Usuario(result);

				} else {
					System.out.println("El usuario no se encuentra");
					return null;
				}
			} catch (SQLException e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("Usuario no valido");
			return null;
		}
		System.out.println("Usuario no entra");
		return null;

	}

	/**
	 * Metodo Existe Usuario para comprobar que el usuario ya existe y ha sido registrado 
	 * @param con para conseguir la conexion
	 * @param usuario user de la persona registrada
	 * @return true si el usuario existe, false en caso contrario
	 */
	public boolean existeUsuario(Conector con, String nombre) {
		Connection con2 = con.getMySQLConnection();
		String sql = "SELECT count(id) as num FROM Usuario WHERE nombre_usuario = ?";

		try (PreparedStatement stmt = con2.prepareStatement(sql)) {
			stmt.setString(1, nombre);

			ResultSet result = stmt.executeQuery();
			result.beforeFirst();
			if (result.next()) {
				System.out.println(result.getString("num"));
				if (result.getInt("num") > 0) {
					return true;
				} else {
					return false;
				}

			}

			return false;
		} catch (SQLException e) {
			e.getStackTrace();

		}
		return false;
	}

	/**
	 * Metodo Nuevo Usuario para registrarse como un nuevo usuario
	 * @param con para conseguir la conexion
	 * @param nombre user del usuario
	 * @param pass contraseña del usuario
	 * @param dni dni del usuario
	 * @param telefono telefono del usuario
	 * @param email email del usuario
	 * @return true si ha sido registrado con exito, false en caso contrario (ya existente o error creandolo)
	 */
	public boolean nuevoUsuario(Conector con, String nombre, String pass, String dni, String telefono, String email) {
		Connection con2 = con.getMySQLConnection();
		if (!existeUsuario(con, nombre) && !nombre.equals("")) {
			String sql = "INSERT INTO Usuario (`nombre_usuario`,`password`,`dni`,`telefono`,`email`) VALUES(?,?,?,?,?)";
			try (PreparedStatement stmt = con2.prepareStatement(sql)) {
				stmt.setString(1, nombre);
				stmt.setString(2, pass);
				stmt.setString(3, dni);
				stmt.setString(4, telefono);
				stmt.setString(5, email);
				stmt.execute();
				System.out.println("Usuario creado");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Usuario ya registrado");
			return false;
		}
		return false;
	}

}
