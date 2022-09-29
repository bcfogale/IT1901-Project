package cleane.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cleane.Leaderboard;
import cleane.User;

public class LeaderboardSerializer extends JsonSerializer<Leaderboard> {

    @Override
    public void serialize(Leaderboard leaderboard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("users");
        for (User user : leaderboard.getUsers()) {
            gen.writeObject(user);
        }
        gen.writeEndArray();
        gen.writeEndObject();
        
    }
    
}
