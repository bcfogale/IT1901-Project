package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.Leaderboard;
import core.Task;
import core.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class CleanEAppTest extends ApplicationTest {

    private CleanEController controller;
    private Leaderboard leaderboard;
    private User user1, user2;
    private Task task1, task4;

    @Override
    public void start(final Stage primaryStage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("cleanE.fxml"));
        final Parent parent = loader.load();
        this.controller = loader.getController();
        this.leaderboard = this.controller.getLeaderboard();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    /**Lager brukere med oppgaver før hver test */
    @BeforeEach
    public void setup() {
        user1 = new User("Sander");
        user2 = new User("Benji");

        leaderboard.addUser(user1);
        leaderboard.addUser(user2);

        task1 = new Task(user1, "Vaske badet", 10, "monday");
        new Task(user1, "Vaske rommet", 5, "tuesday");
        new Task(user1, "Vaske rommet", 5, "saturday");

        new Task(user2, "Vaske kjøkkenet", 20, "wednesday");
        task4 = new Task(user2, "Ta ut søppelet", 5, "friday");
        new Task(user2, "Vaske bil", 9, "sunday");

    }

    /**Tester om kontrollen og ledertavlen eksisterer slik at de andre testene vil fungere */
    @Test
    public void testControllerAndLeaderboard() {
        assertNotNull(this.controller);
        assertNotNull(this.leaderboard);
    }

    /**Skriver inn informasjon og tester om en oppgave med denne informasjonen faktisk blir laget */
    @Test
    public void testAddTask() {
        clickOn("#taskName").write("Støvsuge");
        clickOn("#assignedUser").write("Sander");
        clickOn("#pointsValue").write("5");
        clickOn("#dueDay").write("thursday");

        clickOn("#addButton");

        Task t1 = (Task) user1.getTasks().stream().filter(e -> e.getTaskName().equals("Støvsuge")).findFirst().orElse(null);
        assertEquals("Støvsuge", t1.getTaskName());
        assertEquals("Sander", t1.getAssignedUser().getName());
        assertEquals(5, t1.getPointsValue());
        assertEquals("thursday", t1.getDueDay());

    }

    /**Tester om "cancel"-knappen funker og fjerner informasjonen fra inputfeltene */
    @Test
    public void testClearTask() {
        clickOn("#taskName").write("Støvsuge");
        clickOn("#assignedUser").write("Sander");
        clickOn("#pointsValue").write("5");
        clickOn("#dueDay").write("sunday");

        clickOn("#cancelButton");

        assertEquals("", controller.getAssignesUser().getText());
        assertEquals("", controller.getTaskName().getText());
        assertEquals("", controller.getPointsValue().getText());
        assertEquals("", controller.getDueDay().getText());

    }

    /**Tester om "completed"-knappen fjerner den valgte oppgaven fra aktive oppgaver og oppdaterer antall
     * poeng til brukeren.
     */
    @Test
    public void testHandleCompletedTask() {
        clickOn("#taskName").write("Støvsuge");
        clickOn("#assignedUser").write("Sander");
        clickOn("#pointsValue").write("5");
        clickOn("#dueDay").write("thursday");

        clickOn("#addButton");

        clickOn("#monday");

        press(KeyCode.ENTER).release(KeyCode.ENTER);

        

        clickOn("#completedButton");

        assertEquals(10, user1.getPoints());
    }

    /**Tester om "update"-knappen sorterer ledertavlen */
    @Test
    public void testSortList() {
        task1.setTrue();
        task4.setTrue();

        clickOn("#taskName").write("Støvsuge");
        clickOn("#assignedUser").write("Sander");
        clickOn("#pointsValue").write("5");
        clickOn("#dueDay").write("thursday");

        clickOn("#addButton");


        ObservableList<User> temp = this.controller.getScoreList().getItems();

        assertEquals(user1, temp.get(0));

    }

    // taskName
    // assignedUser
    // pointsValue
    // dueDay
}
