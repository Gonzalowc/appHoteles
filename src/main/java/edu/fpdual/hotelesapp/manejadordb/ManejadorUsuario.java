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

public class ManejadorUsuario {
	public static void main(String[] args) {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();

		System.out.println(mu.existeUsuario(con, "pepe"));
	}

	public boolean validarUsuario(Conector con, String usuario, String passwd) {

		Connection con2 = con.getMySQLConnection();

		try (Statement stmt = con2.createStatement()) {
			String sql = "SELECT * FROM Usuario WHERE nombre_usuario='" + usuario + "' AND password='" + passwd
					+ "' AND activo=1";
			ResultSet result = stmt.executeQuery(sql);
			result.beforeFirst();
			result.next();

			String user = result.getString("nombre_usuario");
			String pass = result.getString("password");
			if (user.equals(usuario) && pass.equals(passwd)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Usuario logging(Conector con, String usuario, String passwd) {
		Connection con2 = con.getMySQLConnection();
		if (validarUsuario(con, usuario, passwd)) {
			String sql = "SELECT * FROM Usuario WHERE usuario = ? AND password=?";

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
