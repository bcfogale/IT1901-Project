package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Importing classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
// Annotation
@SpringBootApplication

/**Starter applikasjonen */
public class CleanEApp extends Application {

    public static void main(String[] args) {
        //Application.launch(args);
        SpringApplication.run(
            Application.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("CleanE");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("cleanE.fxml"))));
        primaryStage.show();
    }

}
