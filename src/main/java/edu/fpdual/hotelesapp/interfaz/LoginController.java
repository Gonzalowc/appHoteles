package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import edu.fpdual.hotelesapp.conector.Conector;
import edu.fpdual.hotelesapp.events.MD5;
import edu.fpdual.hotelesapp.manejadordb.ManejadorUsuario;
import edu.fpdual.hotelesapp.objetos.Usuario;
import excepciones.ManyFails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Clase Login Controller
 * 
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
	@FXML
	private Button btnLogin;

	private LinkedHashMap<String, Integer> intentosDeSesionFallidos = new LinkedHashMap<>();

	/**
	 * Metodo para salir de la app
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Metodo para cambiar de escena al registro
	 * 
	 * @throws IOException
	 */
	@FXML
	private void switchToRegister() throws IOException {
		Stage stage = App.getStage();
		Scene scene = new Scene(App.loadFXML("register"));
		stage.setScene(scene);
		// stage.setWidth(scene.getWidth());
		// stage.setHeight(scene.getHeight());
		App.setStage(stage);
	}

	/**
	 * Metodo para validar que el usuario esta registrado
	 * 
	 * @throws IOException
	 * @throws ManyFails
	 */
	public void validar() throws IOException, ManyFails {
		Conector con = new Conector();
		ManejadorUsuario mu = new ManejadorUsuario();
		if (mu.validarUsuario(con, txtUsuario.getText(), MD5.toMD5(txtPassword.getText()))) {
			Usuario user = mu.logging(con, txtUsuario.getText(), MD5.toMD5(txtPassword.getText()));
			msgError.setVisible(false);
			txtUsuario.setText("");
			txtPassword.setText("");

			App.getStage().close();
			Stage stage = new Stage();
			stage.close();
			stage.initStyle(StageStyle.DECORATED);

			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("aplication.fxml"));
			// creamos el panel a partir del loader
			BorderPane aux = (BorderPane) loader.load();
			// creamos el objeto controlador que queremos usar
			AplicationController appController = loader.<AplicationController>getController();
			// usamos sus metodos
			System.out.println(user);
			appController.setUsuario(user);
			appController.setAplicationLoader(loader);
			//aux.setCenter(aux);
			Scene scene = new Scene(aux);
			stage.hide();
			stage.setMaximized(true);
			stage.setScene(scene);
			stage.show();
			App.setStage(stage);
		} else {
			addFallo(con, mu);

			msgError.setVisible(true);
			System.out.println(intentosDeSesionFallidos);
			if (intentosDeSesionFallidos.get(txtUsuario.getText()) == 5) {
				desactivarCampos();
				String correo = mu.getEmailUser(con, txtUsuario.getText());
				throw new ManyFails("Error Inicio De sesión", correo);
			}

			txtPassword.setText(null);
			txtUsuario.setText(null);
		}
	}

	private void addFallo(Conector con, ManejadorUsuario mu) {
		if (mu.existeUsuario(con, txtUsuario.getText())) {
			if (intentosDeSesionFallidos.containsKey(txtUsuario.getText())) {
				intentosDeSesionFallidos.put(txtUsuario.getText(),
						intentosDeSesionFallidos.get(txtUsuario.getText()) + 1);
			} else {
				intentosDeSesionFallidos.put(txtUsuario.getText(), 1);
			}
		}
	}

	private void desactivarCampos() {
		txtPassword.setDisable(true);
		txtUsuario.setDisable(true);
		btnLogin.setDisable(true);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				txtPassword.setDisable(false);
				txtUsuario.setDisable(false);
				btnLogin.setDisable(false);
			}
		}, 25000);
		intentosDeSesionFallidos.remove(txtUsuario.getText());
	}

}
