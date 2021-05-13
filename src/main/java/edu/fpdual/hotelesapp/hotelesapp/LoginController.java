package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label msgError;

	public void exit() {
		System.exit(0);
	}

	@FXML
	private void switchToRegister() throws IOException {
		App.setRoot("register");
		Stage stage = App.getStage();
		Scene scene = new Scene(App.loadFXML("register"));
		stage.setScene(scene);
		stage.setWidth(scene.getWidth());
		stage.setHeight(scene.getHeight());
		App.setStage(stage);
	}
	
	@FXML
	private void switchToNewHotel() throws IOException {
		App.setRoot("insertHotel");
		Stage stage = App.getStage();
		Scene scene = new Scene(App.loadFXML("insertHotel"));
		stage.setScene(scene);
		stage.setWidth(scene.getWidth());
		stage.setHeight(scene.getHeight());
		App.setStage(stage);
	}


	public void validar() throws IOException {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		if (mu.validarUsuario(con, txtUsuario.getText(), txtPassword.getText())) {
			msgError.setVisible(false);
			txtUsuario.setText("");
			txtPassword.setText("");
			App.cambiarVentana("menu",StageStyle.DECORATED,true);
		} else {
			msgError.setVisible(true);
			txtPassword.setText("");

		}
	}

	
}
