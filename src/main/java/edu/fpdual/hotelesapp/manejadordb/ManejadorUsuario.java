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
	
	public boolean validarUsuario(Conector con, String usuario, String passwd) {
        Connection con2 = con.getMySQLConnection();

        try(Statement stmt = con2.createStatement()){
            String sql = "SELECT * FROM Usuario WHERE nombre='"+usuario+"' AND pass='"+passwd+"' AND activo=1";
            ResultSet result = stmt.executeQuery(sql);
            result.next();

            String user = result.getString("nombre");
            String pass = result.getString("pass");
            if(user.equals(usuario) && pass.equals(passwd)) {
                return true;
            }else {
                return false;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
	
	@SuppressWarnings("null")
	public boolean logging(Usuario usr) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		con = ((Conector) con).getMySQLConnection();
		
		String sql = "SELECT id, nombre, pass FROM usuarios WHERE usuario = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getNombre());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(usr.getPass().equals(rs.getString(3))) {
					usr.setId(rs.getInt(1));
					usr.setNombre(rs.getString(2));
					return true;
				} else {
					return false;
				}
				
			}
			
			return false;
		} catch(SQLException ex) {
			Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	@SuppressWarnings("null")
	public int existeUsuario(String nombre) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		con = ((Conector) con).getMySQLConnection();
		
		
		String sql = "SELECT count(id) FROM usuarios WHERE nombre = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
			return 1;
		} catch(SQLException ex) {
			Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
			return 1;
		}
	}
	
}
