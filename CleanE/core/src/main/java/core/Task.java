package core;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Task {

    final private static List<String> days = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday",
            "saturday",
            "sunday");

    private String taskName;
    private int pointsValue;
    private boolean completed = false;
    private String dueDay;
    private User assignedUser;
    private String uuid;

    public Task() {
    }

    /**
     * Konstruerer objektet og setter alle feltene til klassen unntatt dueDay, som
     * allerede er satt til "false".
     * 
     * @param assignedUser
     * @param taskName
     * @param pointsValue
     * @param dueDay
     */
    public Task(User assignedUser, String taskName, int pointsValue, String dueDay) {
        this.assignedUser = assignedUser;
        assignedUser.addTask(this);
        if (taskName.length() < 2) {
            throw new IllegalArgumentException("Taskname must be at least 2 characters long.");
        }
        this.taskName = taskName;
        if (pointsValue <= 0) {
            throw new IllegalArgumentException("Points must be greater than 0.");
        }
        this.pointsValue = pointsValue;
        this.uuid = UUID.randomUUID().toString();
        if (days.contains(dueDay.toLowerCase())) {
            this.dueDay = dueDay;

        } else {
            throw new IllegalArgumentException("Choose a valid day.");
        }
    }

    /**
     * Konstruerer objektet og setter alle feltene til klassen unntatt dueDay, som
     * allerede er satt til "false".
     * 
     * @param assignedUser
     * @param taskName
     * @param pointsValue
     * @param dueDay
     */
    public Task(User assignedUser, String taskName, int pointsValue, String dueDay, String uuid) {
        this.assignedUser = assignedUser;
        assignedUser.addTask(this);
        ;
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        this.uuid = uuid;
        if (days.contains(dueDay.toLowerCase())) {
            this.dueDay = dueDay;

        } else {
            throw new IllegalArgumentException("Choose a valid day.");
        }
    }

    /**
     * Enda en konstruktør, men denne brukes kun for deserialisering og testing.
     * 
     * @param taskName
     * @param pointsValue
     * @param dueDay
     */
    public Task(String taskName, int pointsValue, String dueDay, String uuid) {
        this.taskName = taskName;
        this.pointsValue = pointsValue;
        this.dueDay = dueDay;
        this.uuid = uuid;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    /**
     * Setter completed til true og legger til oppgavens poengsum til den ansvarlige
     * brukeren.
     */
    public void setTrue() {
        this.completed = true;
        getAssignedUser().addPoints(pointsValue);
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

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Task [taskName=" + taskName + ", pointsValue=" + pointsValue + ", completed=" + completed + ", dueDay="
                + dueDay + ", assignedUser=" + assignedUser.getName() + "]";
    }
}
