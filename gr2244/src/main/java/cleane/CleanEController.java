package cleane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;

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
        clearTask();
    }


//Kontrollerlogikk for leaderBoard

    @FXML
    private ListView<User> scoreList;

    @FXML
    private void leaderBoardList() throws IOException { //listen blir sortert når man trykker på update-knapp
        leaderboard.sortList();
    }
    

    @FXML
    private void handleCompletedTask() throws IOException {
        checkMonday();
        // checkTuesday();
        // checkWednesday();
        // checkThursday();
        // checkFriday();
        // checkSaturday();
        // checkSunday();
    }

    //hjelpemetoder for alle ukedager som kalles i handleCompletedTask
    private void checkMonday() {
        if (monday.getSelectionModel().isEmpty()) {
            System.out.println("No tasks this Monday");
        }
        Task selectedTaskMon = monday.getSelectionModel().getSelectedItem();
        selectedTaskMon.setTrue();                                  //endrer completed = true
        scoreList.getItems().add(selectedTaskMon.getAssignedUser());//legger til user i leaderboard
                    //fjerner valgt oppgave fra view
        
        // System.out.println("för clear");
        // System.out.println( this.monday.getItems());
        this.monday.getItems().remove(selectedTaskMon); 
        // // this.monday.getItems().clear();
        // System.out.println("etter clear");
        // System.out.println(monday.getItems());
        // System.out.println("scorelist:");
        // System.out.println(scoreList.getItems());
        
    }


    // private void checkTuesday() {
    //     if (tuesday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Tuesday");
    //     }
    //     Task selectedTaskTue = tuesday.getSelectionModel().getSelectedItem();
    //     selectedTaskTue.setTrue();                                 
    //     scoreList.getItems().add(selectedTaskTue.getAssignedUser());
    //     monday.getItems().remove(selectedTaskTue);   
    // }

    // private void checkWednesday() {
    //     if (wednesday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Wednesday");
    //     }
    //     Task selectedTaskWed = wednesday.getSelectionModel().getSelectedItem();
    //     selectedTaskWed.setTrue();                                  
    //     scoreList.getItems().add(selectedTaskWed.getAssignedUser());
    //     monday.getItems().remove(selectedTaskWed);                  
    // }

    // private void checkThursday() {
    //     if (thursday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Thursday");
    //     }
    //     Task selectedTaskThu = thursday.getSelectionModel().getSelectedItem();
    //     selectedTaskThu.setTrue();                                 
    //     scoreList.getItems().add(selectedTaskThu.getAssignedUser());
    //     monday.getItems().remove(selectedTaskThu);
    // }

    // private void checkFriday() {
    //     if (friday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Friday");
    //     }
    //     Task selectedTaskFri = friday.getSelectionModel().getSelectedItem();
    //     selectedTaskFri.setTrue();                                 
    //     scoreList.getItems().add(selectedTaskFri.getAssignedUser());
    //     friday.getItems().remove(selectedTaskFri);   
    // }

    // private void checkSaturday() {
    //     if (saturday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Saturday");
    //     }
    //     Task selectedTaskSat = saturday.getSelectionModel().getSelectedItem();
    //     selectedTaskSat.setTrue();                                 
    //     scoreList.getItems().add(selectedTaskSat.getAssignedUser());
    //     monday.getItems().remove(selectedTaskSat); 
    // }

    // private void checkSunday() {
    //     if (sunday.getSelectionModel().isEmpty()) {
    //         System.out.println("No tasks this Sunday");
    //     }
    //     Task selectedTaskSun = sunday.getSelectionModel().getSelectedItem();
    //     selectedTaskSun.setTrue();                                 
    //     scoreList.getItems().add(selectedTaskSun.getAssignedUser());
    //     monday.getItems().remove(selectedTaskSun); 
    // }

    
    @FXML
    private void clearTask() throws IOException{
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
    }
}
