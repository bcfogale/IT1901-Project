package cleane.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import cleane.Leaderboard;
import cleane.Task;
import cleane.User;

public class CleanEModule extends SimpleModule{
    private static final String NAME = "CleanEModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil(){};
    
    public CleanEModule() {
        super(NAME, VERSION_UTIL.version());
       
        
        addSerializer(Leaderboard.class, new LeaderboardSerializer());
    }
    
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CleanEModule c = new CleanEModule();
        mapper.registerModule(c);
        

        User u = new User("Sander");
        u.addPoints(10);
        Leaderboard l = new Leaderboard();
        l.addUser(u);
        new Task( u,"wash stuff", 5, "monday");
        new Task(u, "tidy", 3, "tuesday");
        System.out.println(mapper.writeValueAsString(l));
        

    }
    
    
}
