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
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Legger til poeng, slik at brukeren kan rangeres i Leaderboard.
     * @param additionalPoints
     */
    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
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

    @Override
    public String toString() {
        return "" + name + ": " + points + " poeng";
    }

}
