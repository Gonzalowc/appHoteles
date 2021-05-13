package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

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
	
	@FXML
	private Label msgError;
	
	@FXML
	private Label msgValid;

	public void registrar() throws IOException {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		msgError.setVisible(false);
		msgValid.setVisible(false);
		if (!mu.existeUsuario(con, txtUsuario.getText())) {
			if(mu.nuevoUsuario(con, txtUsuario.getText(), txtPassword.getText(), txtDNI.getText(), txtPhone.getText(),
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
	
	public void goBack() throws IOException {
		App.cambiarVentana("logIn",StageStyle.UNDECORATED,false);
	}
}
