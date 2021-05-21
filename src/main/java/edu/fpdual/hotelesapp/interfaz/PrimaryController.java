package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;
import javafx.fxml.FXML;
/**
 * Clase Primary Controller
 * @author angela.bonilla.gomez
 *
 */
public class PrimaryController {

	/**
	 * Clase para cambiar 
	 * @throws IOException
	 */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
