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
        }
        else {
            
             System.out.println("User already in list.");
        }
        
    }     

    
    public void sortList() {
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }


    public void printLeaderboardStats() {
        for (User user : users) {
            // System.out.println("Oppgave(r) har blitt utført");
           System.out.println(user.getName() +" har " + user.getPoints() + " poeng.");
        }
    }


    @Override
    public String toString() {
        return "Leaderboard [users=" + users + "]";
    }

    
    
}
