package core;

import java.util.ArrayList;
import java.util.List;


public class Leaderboard {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }    

   
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
        else {
            System.out.println("User already in list.");
        }
    }     

    
    public void sortList() {
        users.sort((u1, u2) -> u2.getPoints() - u1.getPoints());
        System.out.println("sorted users: " + users);
    }
    

    @Override
    public String toString() {
        return "Leaderboard [users=" + users + "]";
    }

    
    
}
