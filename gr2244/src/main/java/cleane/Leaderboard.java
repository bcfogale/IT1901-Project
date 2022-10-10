package cleane;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private List<User> users = new ArrayList<>();


    public Leaderboard(List<User>users) {
        this.users = users;
    }


    public List<User> getUsers() {
        return users;
    }    

   
/*     //TODO: metode som finner users med completed tasks og legger til dem i leaderboard
    public void addToUsers() {
        for (User user : User.users) {
            for (Task task : user.getTasks()) {
                if (task.isCompleted()) {
                    if (!users.contains(user)) {
                        users.add(user);
                    }
                }
            }
        }
        // System.out.println(users2.toString());
    }      */

    
    public void sortList() {
        users.sort((u1, u2) -> u1.getPoints() - u2.getPoints());
    }


    public void printLeaderboardStats() {
        for (User user : users) {
            // System.out.println("Oppgave(r) har blitt utført");
           System.out.println(user.getName() +" har " + user.getPoints() + " poeng.");
        }
    }

    

    //TODO: skriv toString som viser navn og poeng for alle users
    
    
}
