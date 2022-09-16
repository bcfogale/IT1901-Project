package cleane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManagement {

    

    public void writeToFile(Leaderboard leaderboard) {

        

        try {
            FileOutputStream file = new FileOutputStream("clean-e.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(leaderboard);

            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Leaderboard readFromFile() {
        

        try {
            FileInputStream file = new FileInputStream("clean-e.txt");
            ObjectInputStream input = new ObjectInputStream(file);

            Leaderboard newLeaderboard = (Leaderboard) input.readObject();

            input.close();

            return newLeaderboard;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return null;
    }
}
