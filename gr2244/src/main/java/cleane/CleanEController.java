package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class CleanEController {


    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    private FileManagement manager = new FileManagement();

    private Leaderboard leaderboard = new Leaderboard();

    @FXML
    Button newTaskButton, calendarButton, scoreBoardButton;


    public void initialize() {
        try {
            updateListViews();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    @FXML
    private void loadFromFile() throws IOException {
        manager.readUser();
        
        updateListViews();
    }


    @FXML
    private void handleTest(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText("Something went wrong");
        alert.setContentText("Hallo");
        alert.showAndWait();
    }


    @FXML
    private void updateListViews() {
        this.monday.getItems().clear();
        this.tuesday.getItems().clear();
        this.wednesday.getItems().clear();
        this.thursday.getItems().clear();
        this.friday.getItems().clear();
        this.saturday.getItems().clear();
        this.sunday.getItems().clear();

        for (User user : leaderboard.getUsers()) {
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
                else if (task.getDueDay().equals("sunday")) {
                    this.sunday.getItems().add(task);
                }
            }
        }
    }


    @FXML
    private void handleSaveButton() throws IOException {
        //manager.writeUser(User.users);
    }


    @FXML
    private TextField assignedUser;

    @FXML
    private TextField taskName;

    @FXML
    private TextField pointsValue;

    @FXML
    private TextField dueDay;
    

    //hjelpemetode
     private User userTextToObject(String assignedUser) {
        if (leaderboard.getUsers().isEmpty()) {
            return new User(assignedUser);
        }
        else {
            for (User user : leaderboard.getUsers()) {
                if(user.getName().equals(assignedUser)) {
                    return user;
                }
            }
        }
        return new User(assignedUser);
    }

    @FXML
    private void appendTask() throws IOException {
        User u = userTextToObject(assignedUser.getText());
        new Task(u, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
        leaderboard.addUser(u);
        updateListViews();
        System.out.println(u.getTasks());
        clearTask();
    }


//Kontrollerlogikk for leaderBoard

    @FXML
    private ListView<User> scoreList;

    @FXML
    private void leaderBoardList() throws IOException { //listen blir sortert når man trykker på update-knapp
        
        
        leaderboard.sortList();
        scoreList.getItems().setAll(leaderboard.getUsers());
    }
    

    @FXML
    private void handleCompletedTask() throws IOException {
        List<ListView<Task>> listviews = new ArrayList<>();

        listviews.add(this.monday);
        listviews.add(this.tuesday);
        listviews.add(this.wednesday);
        listviews.add(this.thursday);
        listviews.add(this.friday);
        listviews.add(this.saturday);
        listviews.add(this.sunday);

        for (ListView<Task> list : listviews) {
            for (Task task : list.getItems()) {
                if (task.equals(list.getSelectionModel().getSelectedItem())) {
                    task.setTrue();
                    scoreList.getItems().add(task.getAssignedUser());
                    task.getAssignedUser().removeTask(task);   
                }
            }
        }
        
        updateListViews();
    }


    @FXML
    private void clearTask() throws IOException{
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
    }
}
