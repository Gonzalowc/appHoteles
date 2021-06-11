package edu.fpdual.hotelesapp.interfaz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 * @author angela.bonilla.gomez
 * @author g.waack.carneado
 */
public class App extends Application {

	/**
	 * Escena
	 */
    private static Scene scene;
    /**
     * Escenario
     */
    private static Stage stage2;

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws IOException {
    	this.stage2 = stage;
        scene = new Scene(loadFXML("logIn"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * Metodo para cargar el FXML en la escena
     * @param fxml Parametro que corresponde al archivo fxml
     * @return Scene la escena
     * @throws IOException
     */
    public static Scene setRoot(String fxml) throws IOException {
       scene.setRoot(loadFXML(fxml));
        return scene;
    }
    /**
     * Metodo para cargar el FXML
     * @param fxml Parametro que corresponde al archivo fxml
     * @return fxmlLoader 
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo para lanzar la aplicacion
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    
    public static Stage getStage() {
    	return stage2;
    }
    
    public static void setStage(Stage stage) {
    	stage2 = stage;
    }
    
    /**
     * Metodo para cambiar de ventana
     * @param FXML Parametro que corresponde al archivo fxml
     * @param style Parametro que corresponde al estilo del escenario
     * @param maximized Parametro que te permite maximizar el escenario
     * @throws IOException Error
     */
    public static void cambiarVentana(String FXML,StageStyle style,boolean maximized) throws IOException {
		App.getStage().close();
		Stage stage = new Stage();
		Scene scene = new Scene(App.loadFXML(FXML));
		stage.setScene(scene);
		stage.initStyle(style);
		stage.setMaximized(maximized);
		App.setStage(stage);
		stage.show();
	}    
    
    
    public static Scene getScene() {
    	return scene;
    }
}