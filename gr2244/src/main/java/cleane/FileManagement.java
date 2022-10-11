package cleane;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class FileManagement {

    /* gamle koder
    public void writeUser(List<User> users) throws IOException {
        Files.createDirectories(getFolderPath());
        try(PrintWriter writer = new PrintWriter(getFilePath().toFile())){
            for (User user : users) {
                writer.println("Name : " + user.getName() + " & Total Points : " + user.getPoints());
                for (Task task : user.getTasks()) {
                    writer.println("Taskname : " + task.getTaskName()+" ~ "+task.getPointsValue() +" ~ "+task.getDueDay());
                } 
            } 
        }
    }

    public void readUser() throws IOException 
    {
        User user = null;
        
        try(Scanner scanner = new Scanner(getFilePath().toFile())){
            while (scanner.hasNextLine()) 
            { 
                String scannedLine = scanner.nextLine();

                    if (scannedLine.substring(0, 6).equals("Name : ")) {
                        String[] userProperties = scannedLine.split(" & Total Points : ");
                        String userID = userProperties[0].substring(7);
                        int points = Integer.parseInt(userProperties[1]);
                        user = new User(userID);
                        user.addPoints(points);
                    }

                    else if (scannedLine.substring(0, 1).equals("Taskname : ")) {
                        String[] taskProperties = scannedLine.split("~");
                        String taskName = taskProperties[0].substring(1);
                        int pointsValue = Integer.parseInt(taskProperties[1]);
                        String dueDay = taskProperties[2];

                        new Task(user, taskName, pointsValue, dueDay);
                    }
            }
                
        }
    } 
     */
    
    //rydde opp write og load funksjonen til cleane.txt og updatelistview()
    public void writeUser(List<User> users) throws IOException {
        Files.createDirectories(getFolderPath());
        try(PrintWriter writer = new PrintWriter(getFilePath().toFile())){
            for (User user : users) {
                writer.println("Name: " + user.getName() + " Points: " + user.getPoints());
                for (Task task : user.getTasks()) {
                    writer.println("Task: " + task.getTaskName()+" ~ "+task.getPointsValue() +" ~ "+task.getDueDay());
                } 
            } 
        }
    }

    public void readUser() throws IOException 
    {
        User user = null;
        
        try(Scanner scanner = new Scanner(getFilePath().toFile())){
            while (scanner.hasNextLine()) 
            { 
                String scannedLine = scanner.nextLine();

                    if (scannedLine.substring(0, 6).equals("Name: ")) {
                        String[] userProperties = scannedLine.split(" Points: ");
                        String userID = userProperties[0].substring(5);
                        int points = Integer.parseInt(userProperties[1]);
                        user = new User(userID);
                        user.addPoints(points);
                    }

                    else if (scannedLine.substring(0, 6).equals("Task: ")) {
                        String[] taskProperties = scannedLine.split(" ~ ");
                        String taskName = taskProperties[0].substring(6);
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
        return Path.of(System.getProperty("user.dir"), "savestates");
    }

    public static void main(String[] args) throws IOException {
        FileManagement f = new FileManagement();
        f.readUser();
        for (User user : User.users) {
            for (Task task : user.getTasks()) {
                System.out.println(task);
            }
        }
    }
}
