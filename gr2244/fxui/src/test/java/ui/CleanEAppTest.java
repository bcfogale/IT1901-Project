package ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import core.Leaderboard;
import core.Task;
import core.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CleanEAppTest extends ApplicationTest{
    
    private CleanEController controller;
    private Leaderboard leaderboard;
    private User user1, user2;
    private Task task1, task2, task3, task4;


    
    public void start(final Stage primaryStage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("cleanE.fxml"));
        final Parent parent = loader.load();
        this.controller = loader.getController();
        this.leaderboard = this.controller.getLeaderboard();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
        
    }


    @BeforeEach
    public void setup() {
        user1 = new User("Sander");
        user2 = new User("Benji");

        leaderboard.addUser(user1);
        leaderboard.addUser(user2);
        
        task1 = new Task(user1, "Vaske badet", 10, "monday");
        task2 = new Task(user1, "Vaske rommet", 5, "tuesday");

        task3 = new Task(user2, "Vaske kjøkkenet", 20, "saturday");
        task4 = new Task(user2, "Ta ut søppelet", 5, "saturday");

        
    }


    @Test
    public void testControllerAndLeaderboard() {
        assertNotNull(this.controller);
        assertNotNull(this.leaderboard);
    }

    @Test
    public void testAddTask() {
        clickOn("#taskName").write("Støvsuge");
        clickOn("#assignedUser").write("Sander");
        clickOn("#pointsValue").write("5");
        clickOn("#dueDay").write("thursday");
    }

    // taskName
    // assignedUser
    // pointsValue
    // dueDay
}
