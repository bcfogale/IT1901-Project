package cleane;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private List<User> users = new ArrayList<>();



    public List<User> getUsers() {
        return users;
    }    

   
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            System.out.println(user.getName() + " was added to list.");
        }
        else {
            
             System.out.println("User already in list.");
        }
        
    }     

    
    public void sortList() {
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }


    @Override
    public String toString() {
        return "Leaderboard [users=" + users + "]";
    }

    
    
}
