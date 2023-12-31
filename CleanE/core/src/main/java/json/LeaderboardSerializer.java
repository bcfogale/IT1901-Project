package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Leaderboard;
import core.User;

public class LeaderboardSerializer extends JsonSerializer<Leaderboard> {

    /** Serialiserer Leaderboard-klassen for JSON lagring. */
    @Override
    public void serialize(Leaderboard leaderboard, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("users");
        for (User user : leaderboard.getUsers()) {
            gen.writeObject(user);
        }
        gen.writeEndArray();
        gen.writeEndObject();

    }

}
