package ui;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Leaderboard;
import core.Task;
import core.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import json.FileManagement;

public class CleanEController {

    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    
    @FXML
    private Button completedButton;
    
    @FXML
    private TextField nameOfUser, points;
    
    @FXML
    private TextField assignedUser;

    @FXML
    private TextField taskName;

    @FXML
    private TextField pointsValue;

    @FXML
    private TextField dueDay;
    
    @FXML
    private ListView<User> scoreList;

    private Leaderboard leaderboard = new Leaderboard();

    private FileManagement fm = new FileManagement();

    public void initialize() {
        try {
            updateListViews();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Laster innhold fra fil
     */
    @FXML
    private void loadFromFile(){
        try {
            leaderboard = fm.readFromFile();
        } catch (IOException e) {
            showErrorMessage("There was an error loading the savefile. Either the savefile was manually edited, or the savefile was moved from its location");
        }
        updateListViews();
        leaderBoardList();
    }

    /** Oppdaterer listviews slik at riktig informasjon vises */
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
                } else if (task.getDueDay().equals("tuesday")) {
                    this.tuesday.getItems().add(task);
                } else if (task.getDueDay().equals("wednesday")) {
                    this.wednesday.getItems().add(task);
                } else if (task.getDueDay().equals("thursday")) {
                    this.thursday.getItems().add(task);
                } else if (task.getDueDay().equals("friday")) {
                    this.friday.getItems().add(task);
                } else if (task.getDueDay().equals("saturday")) {
                    this.saturday.getItems().add(task);

                } else if (task.getDueDay().equals("sunday")) {
                    this.sunday.getItems().add(task);
                }
            }
        }
    }

    /**
     * Lagrer innhold til fil
     */
    @FXML
    private void handleSaveButton() throws IOException {
        fm.writeToFile(leaderboard);
    }

    /**
     * Metoden gkør det mulig å skrive inn nye brukere og gi dem en mengde poeng.
     * @throws IOException
     */
    @FXML
    private void handleAddUserButton() {
        try {
            User u = userTextToObject(nameOfUser.getText());
            int pointsToAdd = Integer.parseInt(points.getText());
            u.addPoints(pointsToAdd);
            leaderboard.addUser(u);
            leaderBoardList();
            clearUserInput();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("For input string: \"\"")) {
                showErrorMessage("Please fill out ALL fields with valid values.");
            } else {
                showErrorMessage(e.getMessage());
            }
        }

    }

    /**Fjerner tekst fra inputfeltene når man skal lage ny bruker. */
    @FXML
    private void clearUserInput() {
        nameOfUser.clear();
        points.clear();
    }


    /**
     * Hjelpemetode som sjekker om en navnet til en bruker allerede finnes
     * 
     * @param assignedUser
     * @return
     */
    private User userTextToObject(String assignedUser) {
        if (leaderboard.getUsers().isEmpty()) {
            return new User(assignedUser);
        } else {
            for (User user : leaderboard.getUsers()) {
                if (user.getName().equals(assignedUser)) {
                    return user;
                }
            }
            return new User(assignedUser);
        }

    }

    /**
     * Legger til en oppgave til en bruker utifra hva som er skrevet
     * i input-feltene
     */
    @FXML
    private void appendTask() {
        try {
            User u = userTextToObject(assignedUser.getText());
            new Task(u, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
            addUserToLeaderboard(u);
            updateListViews();
            scoreList.getItems().clear();
            scoreList.getItems().setAll(leaderboard.getUsers());
            System.out.println(u.getTasks());
            clearTaskInput();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("For input string: \"\"")) {
                showErrorMessage("Please fill out ALL fields with valid values.");
            } else {
                showErrorMessage(e.getMessage());
            }
        }

    }

    /**
     * Legger en bruker til Leaderboard
     * 
     * @param u
     */
    private void addUserToLeaderboard(User u) {
        if (!leaderboard.getUsers().contains(u)) {
            leaderboard.addUser(u);
        }
    }

    // Kontrollerlogikk for leaderBoard


    /**
     * Sorterer Listview til ledertavlen
     */
    @FXML
    private void leaderBoardList() {
        leaderboard.sortList();
        scoreList.getItems().setAll(leaderboard.getUsers());
    }

    /**
     * Fjerner en oppgave når den er blitt gjort ferdig og oppdaterer
     * antall poeng til ansvarlig bruker.
     */
    @FXML
    private void handleCompletedTask() {
        scoreList.getItems().clear();

        List<ListView<Task>> listviews = new ArrayList<>();

        listviews.add(this.monday);
        listviews.add(this.tuesday);
        listviews.add(this.wednesday);
        listviews.add(this.thursday);
        listviews.add(this.friday);
        listviews.add(this.saturday);
        listviews.add(this.sunday);

        for (ListView<Task> day : listviews) {
            for (Task task : day.getItems()) {
                if (task.equals(day.getSelectionModel().getSelectedItem())) {
                    task.setTrue();
                    if (!leaderboard.getUsers().contains(task.getAssignedUser())) {
                        leaderboard.getUsers().add(task.getAssignedUser());
                    }
                    task.getAssignedUser().removeTask(task);
                }
            }
        }
        scoreList.getItems().setAll(leaderboard.getUsers());
        updateListViews();
        leaderBoardList();
    }

    /**
     * Fjerner teksten i inputfeltene når man trykker på "cancel" knappen
     */
    @FXML
    private void clearTaskInput() {
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
    }

    private void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    // Gettere for testing
    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    public TextField getTaskName() {
        return this.taskName;
    }

    public TextField getAssignesUser() {
        return this.assignedUser;
    }

    public TextField getPointsValue() {
        return this.pointsValue;
    }

    public TextField getDueDay() {
        return this.dueDay;
    }

    public ListView<User> getScoreList() {
        return this.scoreList;
    }

}
