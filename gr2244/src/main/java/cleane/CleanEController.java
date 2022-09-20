package cleane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class CleanEController {

    private Leaderboard leaderboard;
    private Task task;
    private User user;
    private FileManagement manager;

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

    private User userTextToObject(TextField userText){
        for (User user : User.users) {
            if (user.getName() == userText.getText()) {
                return user;
            }
        }
        return null;
    }

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

        User user = userTextToObject(assignedUser);

        new Task(user, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());

        updateListViews();
    }


    @FXML
    private void updateListViews() {

        ObservableList<Task> monday = FXCollections.observableArrayList();
        ObservableList<Task> tuesday = FXCollections.observableArrayList();
        ObservableList<Task> wednesday = FXCollections.observableArrayList();
        ObservableList<Task> thursday = FXCollections.observableArrayList();
        ObservableList<Task> friday = FXCollections.observableArrayList();
        ObservableList<Task> saturday = FXCollections.observableArrayList();
        ObservableList<Task> sunday = FXCollections.observableArrayList();


        for (User user : User.users) {
            for (Task task : user.getTasks()) {
                
                if (task.getDueDay().equals("monday")) {
                    monday.add(task);
                }
                else if (task.getDueDay().equals("tuesday")  ) {
                    tuesday.add(task);
                }
                else if (task.getDueDay().equals("wednesday")) {
                    wednesday.add(task);
                }
                else if (task.getDueDay().equals("thursday") ) {
                    thursday.add(task);
                }
                else if (task.getDueDay().equals("friday")) {
                    friday.add(task);
                }
                else if (task.getDueDay().equals("saturday")) {
                    saturday.add(task);
                }
                else if (task.getDueDay().endsWith("sunday")) {
                    sunday.add(task);
                }
            }
        }


        this.monday.setItems(monday);
        this.tuesday.setItems(tuesday);
        this.wednesday.setItems(wednesday);
        this.thursday.setItems(thursday);
        this.friday.setItems(friday);
        this.saturday.setItems(saturday);
        this.sunday.setItems(sunday);

    }

}
