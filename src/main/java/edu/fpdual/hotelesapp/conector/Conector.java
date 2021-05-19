package edu.fpdual.hotelesapp.conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Clase responsable de la conexion con bbdd.
 *
 */
public class Conector {
    Properties prop = new Properties();

    /**
     * Constructor del conector
     */
    public Conector() {
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para conseguir la conexion con MySQL indicando el driver que vamos a usar y creando la conexion basada en la URL
     * @return conexion basada en la URL
     */
    public Connection getMySQLConnection() {
    	try {

    	//Indica que driver vamos a usar.
    	Class.forName(prop.getProperty(MySQLConstants.DRIVER));

    		try {
    			//Crea la conexion basado en la URL.
    			return DriverManager.getConnection(getURL());
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    		return null;
    }
    /**
     * Obtener la URL para conectar la base de datos.
     * @return 
     */

	private String getURL() {
		return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
				.append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
				.append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
				.append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
				.append(prop.getProperty(MySQLConstants.USER)).append("&password=")
				.append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
				.append(prop.getProperty(MySQLConstants.URL_SSL)).toString();
		// --- jdbc::mysql://localhost:3306/world?user=sa&password=psswd&userlSSL=false
	}
}