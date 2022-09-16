package cleane;

import java.util.Arrays;
import java.util.List;

public class Task {

    final private List<String> days =Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    
    private User assignedUser;
    private String taskName;
    private int pointsValue;
    private boolean completed = false;
    private String dueDay;

    
    public Task(User assignedUser, String taskName, int pointsValue, String dueDay) {
        this.assignedUser = assignedUser;
        assignedUser.addTask(this); // Adds this task to the assigned users task list
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        if (days.contains(dueDay.toLowerCase())) {
            this.dueDay = dueDay;
        }
        else {
            throw new IllegalArgumentException("Choose a valid day.");
        }
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

    

    

    



}
