package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


//source: https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode

public class CleanEController {


    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    private FileManagement manager = new FileManagement();

//TODO: metode for switching mellom scenes
    // private Stage stage;
    // private Scene scene;
    //private Parent root; //usikker på hva denne skal gjøre enda...

    @FXML
    Button newTaskButton, adButton;


    public void switchToTask(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/cleane/newTask.fxml"));
        Stage window = (Stage) newTaskButton.getScene().getWindow();
        window.setScene(new Scene(root));
        // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
    }

    public void switchToCalendar() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/cleane/App.fxml"));

        Stage stage = (Stage) adButton.getScene().getWindow();
        
        stage.setScene(new Scene(root));
        
    }

    // private Leaderboard leaderboard;
    // private Task task;
    // private User user;

    // @FXML
    // private Button taskButton;

    // @FXML
    // private ListView<Task> taskView;

    


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
                    this.monday.getItems().clear();
                    this.monday.getItems().add(task);
        
                }
                else if (task.getDueDay().equals("tuesday")  ) {
                    this.tuesday.getItems().clear();
                    this.tuesday.getItems().add(task);
                }
                else if (task.getDueDay().equals("wednesday")) {
                    this.wednesday.getItems().clear();
                    this.wednesday.getItems().add(task);
                }
                else if (task.getDueDay().equals("thursday") ) {
                    this.thursday.getItems().clear();
                    this.thursday.getItems().add(task);
                }
                else if (task.getDueDay().equals("friday")) {
                    this.friday.getItems().clear();
                    this.friday.getItems().add(task);
                }
                else if (task.getDueDay().equals("saturday")) {
                    this.saturday.getItems().clear();
                    this.saturday.getItems().add(task);
                }
                else if (task.getDueDay().endsWith("sunday")) {
                    this.sunday.getItems().clear();
                    this.sunday.getItems().add(task);
                }
            }
        }
    }

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
    private User userTextToObject(String assignedUser){
        for (User user : User.users) {
            if (user.getName() == assignedUser) {
                return user;
            }
        }
        return new User(assignedUser);
    }

    @FXML
    private void appendTask() throws IOException {
        User user = userTextToObject(assignedUser.getText());
        new Task(user, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
        switchToCalendar();
        updateListViews();
    }
    public static void main(String[] args) {
        User u = new User("Sander");
        CleanEController c = new CleanEController();
        System.out.println(c.userTextToObject("Sander"));

    }

}
