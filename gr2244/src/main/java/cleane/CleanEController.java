package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;

import javafx.collections.ObservableList;
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
    Button newTaskButton, calendarButton, scoreBoardButton;


    public void initialize() {
        try {
            updateListViews();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


/*     public void switchToTask(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/cleane/newTask.fxml"));
        Stage window = (Stage) newTaskButton.getScene().getWindow();
        window.setScene(new Scene(root));
        // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
    } */

/*     public void switchToCalendar() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/cleane/App.fxml"));
        Stage stage = (Stage) calendarButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    } */

/* //  creates the link to scoreboard window
    public void switchToScoreboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/cleane/scoreBoard.fxml"));
        Stage window = (Stage) scoreBoardButton.getScene().getWindow();
        window.setScene(new Scene(root));

        
    } */
     


    
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
                else if (task.getDueDay().equals("sunday")) {
                    this.sunday.getItems().add(task);
                }
            }
        }
    }


    @FXML
    private void handleSaveButton() throws IOException {
        manager.writeUser(User.users);
    }


    //For scene: newTask

    @FXML
    private TextField assignedUser;

    @FXML
    private TextField taskName;

    @FXML
    private TextField pointsValue;

    @FXML
    private TextField dueDay;
    

    //hjelpemetode
    private User userTextToObject(String assignedUser){
        if (User.users.isEmpty()) {
            return new User(assignedUser);
        }
        else {
            for (User user : User.users) {
                if (user.getName().equals(assignedUser)) {
                    return user;
                } else {
                    return new User(assignedUser);
                }
    
            }
        }
        return null;
    }

    @FXML
    private void appendTask() throws IOException {
        new Task(userTextToObject(assignedUser.getText()), taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
        // switchToCalendar();
        updateListViews();
        clearTask();
    }

    //Kontrollerlogikk for leaderBoard


    @FXML
    private void leaderBoardList() throws IOException{
        
    }

    @FXML
    private void handleCompletedTask() {
    //sjekk om listview monday er tom eller ikke
        int mondayTasks = monday.getItems().size();
        if (mondayTasks <= 0) {
            System.out.println("No tasks to complete");
        }
        monday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // monday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        monday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(monday.getSelectionModel().getSelectedItem());

    //tuesday
        int tuesdayTasks = tuesday.getItems().size();
        if (tuesdayTasks <= 0) {
            System.out.println("No tasks to complete");
        }
        tuesday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // tuesday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        tuesday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(tuesday.getSelectionModel().getSelectedItem());

        
    //wednesday
        int wednesdayTasks = wednesday.getItems().size();
        if (wednesdayTasks <= 0) {
            System.out.println("No tasks to complete");
        }
        wednesday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // wednesday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        wednesday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(wednesday.getSelectionModel().getSelectedItem());

    //thursday
        int thursdayTasks = thursday.getItems().size();
        if (thursdayTasks <= 0) {
            System.out.println("No tasks to complete");
        }
        thursday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // thursday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        thursday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(thursday.getSelectionModel().getSelectedItem());


        //fjern task fra listview
        //oppdater leaderboardview
    }

    private void clearTask() throws IOException{
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
    }
}
