package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.events.MD5;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase Register Controller
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 */
public class RegisterController {
	/**
	 * Campo de texto de usuario
	 */
	@FXML
	private TextField txtUsuario;
	/**
	 * Campo de texto de contraseña
	 */
	@FXML
	private PasswordField txtPassword;
	/**
	 * Campo de texto de dni
	 */
	@FXML
	private TextField txtDNI;
	/**
	 * Campo de texto de telefono
	 */
	@FXML
	private TextField txtPhone;
	/**
	 * Campo de texto de email
	 */
	@FXML
	private TextField txtEmail;
	
	
	/**
	 * Metodo para salir de la app
	 */
	public void exit() {
		System.exit(0);
	}
	/**
	 * Etiqueta de mensaje de error
	 */
	@FXML
	private Label msgError;
	/**
	 * Etiqueta de mensaje valido
	 */
	@FXML
	private Label msgValid;

	/**
	 * Metodo para registrar un usuario
	 * @throws IOException Error
	 */
	public void registrar() throws IOException {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		msgError.setVisible(false);
		msgValid.setVisible(false);
		if (!mu.existeUsuario(con, txtUsuario.getText())) {
			if(mu.nuevoUsuario(con, txtUsuario.getText(),MD5.toMD5(txtPassword.getText()) , txtDNI.getText(), txtPhone.getText(),
					txtEmail.getText())) {
				msgValid.setVisible(true);
				txtUsuario.setText("");
				txtPassword.setText("");
				txtDNI.setText("");
				txtPhone.setText("");
			}
		}else {
			msgError.setVisible(true);
		}

	}
	
	/**
	 * Metodo para volver atras
	 * @throws IOException Error
	 */
	public void goBack() throws IOException {
		Stage stage = App.getStage();
		Scene scene = new Scene(App.loadFXML("logIn"));
		stage.setScene(scene);
		App.setStage(stage);
	}
}
