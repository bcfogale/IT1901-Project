package core;

import java.util.ArrayList;
import java.util.List;

// Importing class


public class Leaderboard {

    private List<User> users = new ArrayList<>();

    

    public Leaderboard() {
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
        // return user;
    }

    /**
     * Legger user til i listen over brukere i Leaderboard.
     * @param user
     */
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        } else {
            throw new IllegalArgumentException("User already in list.");
        }
    }

    /**Sorterer listen over brukere etter poengsum */
    public void sortList() {
        users.sort((u1, u2) -> u2.getPoints() - u1.getPoints());
    }

    public User getUser(String name) {
		User u = getUsers().stream().filter(e -> e.getName().equals(name)).findFirst().get();
		return u;
	}
    
    @Override
    public String toString() {
        return "Leaderboard [users=" + users + "]";
    }

}
