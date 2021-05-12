package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
	
	public void registrar() throws IOException {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		System.out.println(txtUsuario.getText() + txtPassword.getText() + txtDNI.getText() + txtPhone.getText() + txtEmail.getText());
		mu.nuevoUsuario(con, txtUsuario.getText(), txtPassword.getText(), txtDNI.getText(), txtPhone.getText(), txtEmail.getText());
	}
	
}
