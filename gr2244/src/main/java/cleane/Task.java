package cleane;

import java.util.Arrays;
import java.util.List;

public class Task {

    final private List<String> days =Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    
    private String taskName;
    private int pointsValue;
    private boolean completed = false;
    private String dueDay;
    private User assignedUser;
  
    public Task(User assignedUser, String taskName, int pointsValue, String dueDay) {
        this.assignedUser = assignedUser;
         // Adds this task to the assigned users task list
        assignedUser.addTask(this);;
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        if (days.contains(dueDay.toLowerCase())) {
            this.dueDay = dueDay;
        }
        else {
            throw new IllegalArgumentException("Choose a valid day.");
        }
    }

    // This constructor is only used for deserialization purposes
    public Task(String taskName, int pointsValue, String dueDay) {
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        this.dueDay = dueDay;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
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

    public boolean isCompleted() {
        return completed;
    }

    public String getDueDay() {
        return dueDay;
    }


    @Override
    public String toString() {
        return "Task [taskName=" + taskName + ", pointsValue=" + pointsValue + ", completed=" + completed + ", dueDay="
                + dueDay + ", assignedUser=" + assignedUser.getName() + "]";
    }
}
