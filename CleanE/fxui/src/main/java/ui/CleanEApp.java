package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Starter applikasjonen.*/
public class CleanEApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("CleanE");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("cleanE.fxml"))));
        primaryStage.show();
    }

}
