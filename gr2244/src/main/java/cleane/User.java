package cleane;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private int points;

    private List<Task> tasks = new ArrayList<>();

    private String name;

    public User(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        getTasks().add(task);
    }





    
}
