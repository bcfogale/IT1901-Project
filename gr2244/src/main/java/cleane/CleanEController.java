package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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
        Task task = new Task(assignedUser, taskName, pointsValue, dueDay);
        tasks.addTask(task);
    }

}
