package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;
import core.Task;
import core.User;

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
    public void testDeserializers() throws JsonMappingException, JsonProcessingException{
        Leaderboard leaderboardFromFile = mapper.readerFor(Leaderboard.class).readValue(jsonString);
        //this test checks field by field that the two comparing objects have the same field values.
        assertThat(leaderboardFromFile).usingRecursiveComparison().isEqualTo(l);
    }

    @Test
    public void testSerializersDeserializers() throws JsonProcessingException{
        String mapperString2 = mapper.writeValueAsString(l);
        Leaderboard leaderboardFromMapper = mapper.readerFor(Leaderboard.class).readValue(mapperString2);
        assertThat(leaderboardFromMapper).usingRecursiveComparison().isEqualTo(l);
    }
  
}
