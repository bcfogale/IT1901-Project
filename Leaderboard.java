package main.java.app;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private List<User> users = new ArrayList<>();
    
    public void sortList(){
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }
}
