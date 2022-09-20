package cleane;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManagement {

    public void writeUser(ArrayList<User> users) throws IOException {
        Files.createDirectories(getFolderPath());
        try(PrintWriter writer = new PrintWriter(getFilePath().toFile())){
            for (User user : users) {
                writer.println("$" + user.getName() + "&" + user.getPoints());
                for (Task task : user.getTasks()) {
                    writer.println("¥" + task.getTaskName()+"~"+task.getPointsValue() +"~"+task.getDueDay());
                } 
            } 
        }
    }

    public void readUser() throws IOException {
        User user = null;
        
        try(Scanner scanner = new Scanner(getFilePath().toFile())){
            while (scanner.hasNextLine()) { 
                String scannedLine = scanner.nextLine();
                if (scannedLine.substring(0, 1).equals("$")) {
                    String[] userProperties = scannedLine.split("&");
                    String userID = userProperties[0];
                    int points = Integer.parseInt(userProperties[1]);
                    user = new User(userID);
                    user.addPoints(points);
                    
                } else if (scannedLine.substring(0, 1).equals("¥")) {
                    String[] taskProperties = scannedLine.split("~");
                    String taskName = taskProperties[0];
                    int pointsValue = Integer.parseInt(taskProperties[1]);
                    String dueDay = taskProperties[2];

                    new Task(user, taskName, pointsValue, dueDay);
                }

                
                }
                
            }
        } 

    
    public Path getFilePath() {
        return getFolderPath().resolve("cleane.txt");
    }

    public Path getFolderPath() {
        return Path.of(System.getProperty("user.home"), "savestates");
    }

    
}
