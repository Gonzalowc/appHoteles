package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.events.MD5;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Clase Register Controller
 * @author angela.bonilla.gomez
 *
 */
public class RegisterController {
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtDNI;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextField txtEmail;
	

	/**
	 * Metodo para salir de la app
	 */
	public void exit() {
		System.exit(0);
	}

	@FXML
	private Label msgError;
	
	@FXML
	private Label msgValid;

	/**
	 * Metodo para registrar un usuario
	 * @throws IOException
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
	 * @throws IOException
	 */
	public void goBack() throws IOException {
		Stage stage = App.getStage();
		Scene scene = new Scene(App.loadFXML("logIn"));
		stage.setScene(scene);
		App.setStage(stage);
	}
}
