package edu.fpdual.hotelesapp.manejadordb;

import java.io.IOException;

import edu.fpdual.hotelesapp.hotelesapp.App;
import javafx.fxml.FXML;

public class CambiarRegistrar {
	  @FXML
	    private void switchToRegister() throws IOException {
	        App.setRoot("register");
	    }
}
