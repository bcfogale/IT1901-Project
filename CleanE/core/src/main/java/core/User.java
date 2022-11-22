package core;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User {

    private int points;
    private String name;
    private Collection<Task> tasks = new ArrayList<>();

    /**
     * Konstruktør som tar inn et navn
     * @param name
     */
    public User(String name) {
        if (name.length()<1) {
            throw new IllegalArgumentException("Name should be 2 characters or more.");
        } else {
            this.name = name;
        }
        
    }



    public User() {
    }



    public int getPoints() {
        return points;
    }

    /**
     * Legger til poeng, slik at brukeren kan rangeres i Leaderboard.
     * @param additionalPoints
     */
    public void addPoints(int additionalPoints) {
        if (additionalPoints >= 0) {
            this.points += additionalPoints;
        }
        else{
            throw new IllegalArgumentException("Points must be greater than or equal to 1.");
        }
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Legger til en oppgave i listen over alle oppgavene til en bruker
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Fjerner en oppgave fra listen over alle oppgaver
     * @param task
     */
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void removeTaskByUUID(String uuid) {
        Task task = getTaskByUUID(uuid);
        // if (task != null) {
        //     removeTask(task);
        // }
        removeTask(task);
    }

    public Task getTaskByUUID(String uuid){
        Task task = this.tasks.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElse(null);
        return task;
    }
    @Override
    public String toString() {
        return "" + name + ": " + points + " poeng ";

        // + "Tasks: " + tasks
    }

}
