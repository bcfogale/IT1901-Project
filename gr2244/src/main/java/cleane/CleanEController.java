package cleane;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;


//source: https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode

public class CleanEController {
    
//TODO: metode for switching mellom scenes
    private Stage stage;
    private Scene scene;
    private Parent root; //usikker på hva denne skal gjøre enda...

    @FXML
    Button newTaskButton;

    public void switchToTask(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/newTask.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCalendar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/App.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // private Leaderboard leaderboard;
    // private Task task;
    // private User user;

    // @FXML
    // private Button taskButton;

    // @FXML
    // private ListView<Task> taskView;

    @FXML
    private TextField assignedUser;

    @FXML
    private TextField taskName;

    @FXML
    private TextField pointsValue;

    @FXML
    private TextField dueDay;
    

    

    //For scene: newTask

    //hjelpemetode
    private User userTextToObject(TextField assignedUser){
        for (User user : User.users) {
            if (user.getName() == assignedUser.getText()) {
                return user;
            }
        }
        return null;
    }

    @FXML
    private void addTask() {
        User user = userTextToObject(assignedUser);
        new Task(user, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
    }

}
