package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class CleanEController {

    private Leaderboard leaderboard;
    private Task task;
    private User user;

    @FXML
    private Button taskButton;


    @FXML
    private ListView<Task> taskView;

    @FXML
    private void handleNewTask() {
        //selve dialogpane:
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Task");
        dialog.setHeaderText("Add new task");

        //inputfield for å legge inn bruker:
        TextField assignedUser = new TextField();
        assignedUser.setPromptText("User");
        System.out.println("Task was assigned to: " + assignedUser);

        //inputfield for å legge inn oppgavenavn:
        TextField taskName = new TextField();
        taskName.setPromptText("Name of task");
        System.out.println("Task name " + taskName + "was added");

        //inputfieldsfor å legge inn poeng:
        TextField pointsValue = new TextField();
        pointsValue.setPromptText("0");
        System.out.println("Points value " + pointsValue + "was added to task");

        //inputfield for å legge inn dato:
        TextField dueDay = new TextField();
        dueDay.setPromptText("Due day");
        System.out.println("Due day for task was set to: " + dueDay);
    }

}
