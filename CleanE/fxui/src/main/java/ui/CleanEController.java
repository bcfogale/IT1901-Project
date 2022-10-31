package ui;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Leaderboard;
import core.Task;
import core.User;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import json.FileManagement;

public class CleanEController {

    @FXML
    private ListView<Task> monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    @FXML
    private Button completedButton;

    @FXML
    private TextField nameOfUser, points;

    private Leaderboard leaderboard = new Leaderboard();

    private FileManagement fm = new FileManagement();

    public void initialize() {
        try {
            updateListViews();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // load list fra cleane.txt
    @FXML
    private void loadFromFile() throws IOException {
        leaderboard = fm.readFromFile();
        updateListViews();
        leaderBoardList();
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

    @FXML
    private void handleSaveButton() throws IOException {
        fm.writeToFile(leaderboard);
    }

    @FXML
    private void handleAddUserButton() throws IOException{
        User u = userTextToObject(nameOfUser.getText());
        int pointsToAdd = Integer.parseInt(points.getText());
        u.addPoints(pointsToAdd);
        leaderboard.addUser(u);
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

    // hjelpemetode
    private User userTextToObject(String assignedUser) {
        if (leaderboard.getUsers().isEmpty()) {
            return new User(assignedUser);
        } else {
            for (User user : leaderboard.getUsers()) {
                if (user.getName().equals(assignedUser)) {
                    return user;
                }
            }
        }
        return new User(assignedUser);
    }

    // sette inn if/else så at AddTask button er ubrukelig mens texfields er tomt
    @FXML
    private void appendTask() throws IOException {
        User u = userTextToObject(assignedUser.getText());
        new Task(u, taskName.getText(), Integer.parseInt(pointsValue.getText()), dueDay.getText());
        addUserToLeaderboard(u);
        updateListViews();
        scoreList.getItems().clear();
        scoreList.getItems().setAll(leaderboard.getUsers());
        System.out.println(u.getTasks());
        clearTaskInput();
    }

    private void addUserToLeaderboard(User u) {
        if (!leaderboard.getUsers().contains(u)) {
            leaderboard.addUser(u);
        }
    }

    // Kontrollerlogikk for leaderBoard

    @FXML
    private ListView<User> scoreList;

    @FXML
    private void leaderBoardList() throws IOException { // listen blir sortert når man trykker på update-knapp

        leaderboard.sortList();
        scoreList.getItems().setAll(leaderboard.getUsers());
    }

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

    @FXML
    private void clearTaskInput() throws IOException {
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
