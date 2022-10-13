package core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(
  generator = ObjectIdGenerators.IntSequenceGenerator.class, 
  property = "id")
public class User {

    // public static List<User> users = new ArrayList<>();
    
    private int points;
    private String name;
    private List<Task> tasks = new ArrayList<>();
    

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

    public void removeTask(Task task) {
        getTasks().remove(task);
    }

    @Override
    public String toString() {
        return ""+ name + ": " + points + " poeng";
    }


    
}
