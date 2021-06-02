package excepciones;

import java.io.IOException;

import edu.fpdual.hotelesapp.mail.Sender;

public class ManyFails extends Exception{

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
