package cleane.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
        //We don't have custom serializers for User and Task
        //Because of cyclic referencing this would cause issues
        //The issues are easily resolved with the automatic serialization and the use of @JsonIdentityInfo in the User class
        addSerializer(Leaderboard.class, new LeaderboardSerializer());
        addDeserializer(Leaderboard.class, new LeaderboardDeserializer());
        addDeserializer(User.class, new UserDeserializer());
        addDeserializer(Task.class, new TaskDeserializer());
    }
    
    //This main method is only used for testing and should be removed when done
    public static void main(String[] args) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        CleanEModule c = new CleanEModule();
        mapper.registerModule(c);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);

        Leaderboard l = mapper.readerFor(Leaderboard.class).readValue(new File("gr2244/savestates/savefile.json"));
        System.out.println(l);
        
        

        /* User u = new User("Sander");
        u.addPoints(10);
        Leaderboard l = new Leaderboard();
        l.addUser(u);
        new Task( u,"wash stuff", 5, "monday");
        new Task(u, "tidy", 3, "tuesday");
        mapper.writeValue(new File("gr2244/savestates/savefile.json"), l);
        System.out.println(mapper.writeValueAsString(l)); */
        

    }
    
    
}
