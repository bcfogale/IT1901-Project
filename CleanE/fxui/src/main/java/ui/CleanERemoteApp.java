package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starter applikasjonen
 */
public class CleanERemoteApp extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("CleanE");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CleanERemoteApp.fxml"))));
        primaryStage.show();
    }
}
