package edu.fpdual.hotelesapp.hotelesapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage2;

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws IOException {
    	this.stage2 = stage;
        scene = new Scene(loadFXML("logIn"));
        stage.setScene(scene);
        //stage.setMaximized(true);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static Scene setRoot(String fxml) throws IOException {
       scene.setRoot(loadFXML(fxml));
        return scene;
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getStage() {
    	return stage2;
    }
    public static void setStage(Stage stage) {
    	stage2 = stage;
    }
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