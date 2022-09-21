package cleane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class CleanEController {

    private Leaderboard leaderboard;
    private Task task;
    private User user;
    private FileManagement manager = new FileManagement();

    @FXML
    private Button taskButton;


    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;


    @FXML
    private void initialize() {
        try {
            manager.readUser();
            updateListViews();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // private User userTextToObject(TextField userText){
    //     for (User user : User.users) {
    //         if (user.getName() == userText.getText()) {
    //             return user;
    //         }
    //     }
    //     return null;
    // }

    @FXML
    private void handleTest(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText("Something went wrong");
        alert.setContentText("Hallo");
        alert.showAndWait();
    }

    // @FXML
    // private void handleNewTask() {
    //     //selve dialogpane:
    //     TextInputDialog dialog = new TextInputDialog();
    //     dialog.setTitle("New Task");
    //     dialog.setHeaderText("Add new task");

    //     //inputfield for å legge inn bruker:
    //     TextField assignedUser = new TextField();
    //     assignedUser.setPromptText("User");
    //     System.out.println("Task was assigned to: " + assignedUser);

    //     //inputfield for å legge inn oppgavenavn:
    //     TextField taskName = new TextField();
    //     taskName.setPromptText("Name of task");
    //     System.out.println("Task name " + taskName + "was added");

    //     //inputfieldsfor å legge inn poeng:
    //     TextField pointsValue = new TextField();
    //     pointsValue.setPromptText("0");
    //     System.out.println("Points value " + pointsValue + "was added to task");

    //     //inputfield for å legge inn dato:
    //     TextField dueDay = new TextField();
    //     dueDay.setPromptText("Due day");
    //     System.out.println("Due day for task was set to: " + dueDay);

    //     User user = userTextToObject(assignedUser);

    //     new Task(user, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());

    //     updateListViews();
    // }


    @FXML
    private void updateListViews() {




        for (User user : User.users) {
            for (Task task : user.getTasks()) {
                
                if (task.getDueDay().equals("monday")) {
                    this.monday.getItems().add(task);
        
                }
                else if (task.getDueDay().equals("tuesday")  ) {
                    this.tuesday.getItems().add(task);
                }
                else if (task.getDueDay().equals("wednesday")) {
                    this.wednesday.getItems().add(task);
                }
                else if (task.getDueDay().equals("thursday") ) {
                    this.thursday.getItems().add(task);
                }
                else if (task.getDueDay().equals("friday")) {
                    this.friday.getItems().add(task);
                }
                else if (task.getDueDay().equals("saturday")) {
                    this.saturday.getItems().add(task);
                }
                else if (task.getDueDay().endsWith("sunday")) {
                    this.sunday.getItems().add(task);
                }
            }
        }


    }

}
