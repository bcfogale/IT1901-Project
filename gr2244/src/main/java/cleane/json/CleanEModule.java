package cleane.json;

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
        addSerializer(Task.class, new TaskSerializer());
        addSerializer(User.class, new UserSerializer());
        addSerializer(Leaderboard.class, new LeaderboardSerializer());
    }
    
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new CleanEModule());

        User u = new User("Sander");
        u.addPoints(10);
        Leaderboard l = new Leaderboard();
        l.addUser(u);
        Task t = new Task(u, "wash stuff", 5, "monday");
        
        System.out.println(mapper.writeValueAsString(u));

    }
    
    
}
