package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cleane.Leaderboard;
import cleane.Task;
import cleane.User;

public class CleanEModuleTest {

    private ObjectMapper mapper;
    private CleanEModule mod;
    private Leaderboard l;
    private User u;
    private final String jsonString = "{\"users\":[{\"id\":1,\"points\":10,\"name\":\"Sander\",\"tasks\":[{\"taskName\":\"wash stuff\",\"pointsValue\":5,\"completed\":false,\"dueDay\":\"monday\",\"assignedUser\":1}]}]}";

    @BeforeEach
    public void setup(){
        mod = new CleanEModule();
        mapper = new ObjectMapper();
        mapper.registerModule(mod);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);

        l = new Leaderboard();
        u = new User("Sander");
        l.addUser(u);
        u.addPoints(10);
        new Task(u, "wash stuff", 5, "monday");  
    }

    @Test
    public void testSerializers() throws JsonProcessingException{
        String mapperString = mapper.writeValueAsString(l);
        assertEquals(jsonString, mapperString);
    }
    
    @Test
    public void testDeserializers() throws IOException{
        Leaderboard leaderboardFromFile = mapper.readerFor(Leaderboard.class).readValue(jsonString);
        assertThat(leaderboardFromFile).usingRecursiveComparison().isEqualTo(l);
        
    }
  
}
