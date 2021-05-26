package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import edu.fpdual.hotelesapp.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Clase Login Controller
 * @author angela.bonilla.gomez
 *
 */
public class LoginController {
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label msgError;

	/**
	 * Metodo para salir de la app
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Metodo para cambiar de escena al registro
	 * @throws IOException
	 */
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
	
	/**
	 * Metodo para validar que el usuario esta registrado
	 * @throws IOException
	 */
	public void validar() throws IOException {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		if (mu.validarUsuario(con, txtUsuario.getText(), txtPassword.getText())) {
			Usuario user = mu.logging(con, txtUsuario.getText(), txtPassword.getText());
			msgError.setVisible(false);
			txtUsuario.setText("");
			txtPassword.setText("");
			
//			App.cambiarVentana("aplication",StageStyle.DECORATED,true);
			Stage stage = App.getStage();
			
			//crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("aplication.fxml"));
			//creamos el panel a partir del loader
			BorderPane aux = (BorderPane) loader.load();
			//creamos el objeto controlador que queremos usar
			AplicationController appController = loader.<AplicationController>getController();
			//usamos sus metodos
			System.out.println(user);
			appController.setUsuario(user);
			appController.setAplicationLoader(loader);
			Scene scene = new Scene(aux);
			stage.hide();
			//stage.initStyle(StageStyle.DECORATED);
			stage.setMaximized(true);
			stage.setScene(scene);
			stage.show();
			App.setStage(stage);
			
			
		} else {
			msgError.setVisible(true);
			txtPassword.setText("");

		}
	}


	
}
