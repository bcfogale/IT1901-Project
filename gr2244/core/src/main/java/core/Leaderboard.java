package core;

import java.util.ArrayList;
import java.util.List;


public class Leaderboard {

    private List<User> users = new ArrayList<>();

 

    public void sortList(){
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }

    public void addUser(User u){
        users.add(u);
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Leaderboard [users=" + users + "]";
    }

    

}
