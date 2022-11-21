package ui;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import core.Leaderboard;
import core.Task;
import core.User;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import json.FileManagement;

public class CleanERemoteController {

    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    @FXML
    private Button completedButton;

    @FXML
    private TextField nameOfUser, points;

    private Leaderboard leaderboard = new Leaderboard();

    private FileManagement fm = new FileManagement();

    private RemoteCleanEAccess remoteCleanEAccess;


    

    public CleanERemoteController() throws URISyntaxException {
        this.remoteCleanEAccess = new RemoteCleanEAccess(new URI("http://localhost:8080/Leaderboard"));
    }

    public void initialize() {
        try {
            updateListViews();
            leaderBoardList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Laster innhold fra fil
     * @throws IOException
     */
    @FXML
    private void loadFromFile() throws IOException {
        leaderboard = fm.readFromFile();
        updateListViews();
        leaderBoardList();
    }

    /**Oppdaterer listviews slik at riktig informasjon vises */
    @FXML
    private void updateListViews() {
        this.monday.getItems().clear();
        this.tuesday.getItems().clear();
        this.wednesday.getItems().clear();
        this.thursday.getItems().clear();
        this.friday.getItems().clear();
        this.saturday.getItems().clear();
        this.sunday.getItems().clear();

        for (User user : remoteCleanEAccess.getUsers()) {
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
     * @throws IOException
     */
    @FXML
    private void handleSaveButton() throws IOException {
        fm.writeToFile(remoteCleanEAccess.getLeaderboard());
    }

    @FXML
    private void handleAddUserButton() throws IOException{
        User u = checkIfUserExists(nameOfUser.getText());
        int pointsToAdd = Integer.parseInt(points.getText());
        u.addPoints(pointsToAdd);
        remoteCleanEAccess.addUser(u);
        leaderBoardList();
        clearUserInput();
    }

    @FXML
    private void clearUserInput(){
        nameOfUser.clear();
        points.clear();
    }

    @FXML
    private TextField assignedUser;

    @FXML
    private TextField taskName;

    @FXML
    private TextField pointsValue;

    @FXML
    private TextField dueDay;

    /**
     * Hjelpemetode som sjekker om en navnet til en bruker allerede finnes
     * @param assignedUser
     * @return
     */
    private User userTextToObject(String assignedUser) {
        if (remoteCleanEAccess.getUsers().isEmpty()) {

            User u1 = new User(assignedUser);
            remoteCleanEAccess.addUser(u1);
            return u1;
        } else {
            for (User user : remoteCleanEAccess.getUsers()) {
                if (user.getName().equals(assignedUser)) {
                    return user;
                }
            }
        }
        User u2 = new User(assignedUser);
        remoteCleanEAccess.addUser(u2);
        return u2;
    }

    private User checkIfUserExists(String username) {
        if (remoteCleanEAccess.getUsers().isEmpty()) {
            return new User(username);
        } else {
            for (User user : remoteCleanEAccess.getUsers()) {
                if (user.getName().equals(username)) {
                    throw new IllegalArgumentException("User already exists.");
                }
            }
        }
        return new User(username);
    }

    /**
     * Legger til en oppgave til en bruker utifra hva som er skrevet
     * i input-feltene
     * @throws IOException
     */
    // sette inn if/else så at AddTask button er ubrukelig mens texfields er tomt
    @FXML
    private void appendTask() throws IOException {
        User u = userTextToObject(assignedUser.getText());
        remoteCleanEAccess.addTask(u, new Task(u, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText()));
        updateListViews();
        scoreList.getItems().clear();
        scoreList.getItems().setAll(remoteCleanEAccess.getUsers());
        clearTask();
    }


    // Kontrollerlogikk for leaderBoard

    @FXML
    private ListView<User> scoreList;

    /**
     * Sorterer Listview til ledertavlen
     * @throws IOException
     */
    @FXML
    private void leaderBoardList() throws IOException { // listen blir sortert når man trykker på update-knapp

        // remoteCleanEAccess.getLeaderboard().sortList();
        scoreList.getItems().setAll(remoteCleanEAccess.getUsers());
    }

    /**
     * Fjerner en oppgave når den er blitt gjort ferdig og oppdaterer
     * antall poeng til ansvarlig bruker.
     * @throws IOException
     */
    @FXML
    private void handleCompletedTask() throws IOException {
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
                    remoteCleanEAccess.addPoints(task.getAssignedUser(), task.getPointsValue());;
                    if (!remoteCleanEAccess.getUsers().contains(task.getAssignedUser())) {
                        remoteCleanEAccess.getUsers().add(task.getAssignedUser());
                    }
                    remoteCleanEAccess.removeTaskByUUID(task);
                }
            }
        }
        scoreList.getItems().setAll(remoteCleanEAccess.getUsers());
        leaderBoardList();
        updateListViews();
    }

    /**
     * Fjerner teksten i inputfeltene når man trykker på "cancel" knappen
     * @throws IOException
     */
    @FXML
    private void clearTask() throws IOException {
        this.assignedUser.clear();
        this.taskName.clear();
        this.pointsValue.clear();
        this.dueDay.clear();
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
