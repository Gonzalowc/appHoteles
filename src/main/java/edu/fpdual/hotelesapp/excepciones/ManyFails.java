package edu.fpdual.hotelesapp.excepciones;

import java.io.IOException;

import edu.fpdual.hotelesapp.mail.Sender;

/**
 * Clase Many Fails que se trata de una excepcion propia
 * @author angela.bonilla.gomez
 * @author g.moreno.rodriguez
 * @author g.waack.carneado
 *
 */
public class ManyFails extends Exception{

	/**
	 * Metodo que devuelve un error cuando hay demasiados fallos de inicio de sesion y que se usa para enviar correos
	 * @param message mensaje a enviar
	 * @param correo correo desde el que se envia
	 */
	public ManyFails(String message,String correo) {
		super(message);
		
		Sender email = new Sender();
		try {
			email.send("hotelesapp@gmail.com", 
					correo, 
					"Multiple Fallo Inicio Sesion", 
					"En un corto periodo de tiempo se ha intentado acceder a su cuenta. Si no ha sido usted, Cambie la contraseña, Gracias.", 
					"");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
