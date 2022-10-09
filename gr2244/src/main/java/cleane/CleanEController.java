package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;

/* import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; */
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
// import javafx.stage.Stage;


//source: https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode

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
        // getAssignedUser().getTasks().add(this);

        updateListViews();
        clearTask();
    }


//Kontrollerlogikk for leaderBoard

    @FXML
    private ListView<User> scoreList;

//metode for å laste inn users og points i leaderBoard

    @FXML
    private void leaderBoardList() throws IOException{

    }

    @FXML
    private void handleCompletedTask() throws IOException {
        checkMonday();
    
        
        leaderboard.addToUsers2();
        leaderboard.printLeaderboardStats();
        leaderboard.sortList();
        System.out.println(leaderboard.getUsers());

        // getAssignedUser().getTasks().remove(monday.getSelectionModel().getSelectedItem());
        // updateListViews();
    }

//hjelpemetoder for alle ukedager/handleCompletedTask
    private void checkMonday() {
        //sjekk om listview monday er tom eller ikke

        if (monday.getSelectionModel().isEmpty()) {
            System.out.println("No tasks this monday");
        }

        Task selectedTaskMon = monday.getSelectionModel().getSelectedItem();
        // System.out.println(selectedTaskMon.getAssignedUser().getPoints() + " poeng har du");
        selectedTaskMon.setTrue();
        
        System.out.println(selectedTaskMon.getAssignedUser() + " har");
        System.out.println(selectedTaskMon.getAssignedUser().getTasks().size() + " oppgaver");
        System.out.println(selectedTaskMon.getPointsValue() + " poeng er oppgaven verdt");
        // System.out.println(selectedTaskMon.getAssignedUser().getPoints() + " poeng fikk du");

        monday.getItems().remove(selectedTaskMon);   //fjerner valgt oppgave fra view
    }
/* 
    //tuesday

        tuesday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // tuesday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        // tuesday.getSelectionModel().getSelectedItem().
        // getAssignedUser().getTasks().remove(tuesday.getSelectionModel().getSelectedItem());

        
    //wednesday

        wednesday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // wednesday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        wednesday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(wednesday.getSelectionModel().getSelectedItem());

    //thursday

        thursday.getSelectionModel().getSelectedItem().isCompleted();     //endrer boolean completed til true
        // thursday.getItems().remove(monday.getSelectionModel().getSelectedItem()); //fjerner valgt oppgave fra view
        
        thursday.getSelectionModel().getSelectedItem().
        getAssignedUser().getTasks().remove(thursday.getSelectionModel().getSelectedItem());


        //fjern task fra listview
        //oppdater leaderboardview
    }
 */
    @FXML
    private void clearTask() throws IOException{
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
    }
}
