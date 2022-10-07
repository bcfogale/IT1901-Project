package cleane;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.CheckBox;

public class Task {

    final private List<String> days =Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    
    private User assignedUser;
    private String taskName;
    private int pointsValue;
    private boolean completed;
    private String dueDay;
    private CheckBox checkBox;

    
    public Task(User assignedUser, String taskName, int pointsValue, String dueDay) {
        this.assignedUser = assignedUser;
        assignedUser.addTask(this); // Adds this task to the assigned users task list
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        this.completed = false;
        if (days.contains(dueDay.toLowerCase())) {
            this.dueDay = dueDay;
        }
        else {
            throw new IllegalArgumentException("Choose a valid day.");
        }
        checkBox.setSelected(false);
    }

    public void setTrue() {
        this.completed = true;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public String getDueDay() {
        return dueDay;
    }

    public void completeTask() {
        if (checkBox.isSelected()) {
            this.setTrue();
            assignedUser.addPoints(pointsValue);
        }
    }

    @Override
    public String toString() {
        return "Taskname: " + taskName + "   " + "AssignedUser: " + assignedUser + "   " + "Points: " + pointsValue + "   " + "Dueday: " + dueDay;
    }
    

}
