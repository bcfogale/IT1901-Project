package cleane;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private List<User> users = new ArrayList<>();
    
    public Leaderboard(){}

    public Leaderboard(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void sortList(){
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }
}
