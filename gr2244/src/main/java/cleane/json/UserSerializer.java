package cleane.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cleane.Task;
import cleane.User;

public class UserSerializer extends JsonSerializer<User>{

    /*
     * Format:
     * {
     *      "name": "..."
     *      "points": 5
     *      "tasks": [...]
     * }
     */

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", user.getName());
        gen.writeNumberField("points", user.getPoints());
        gen.writeArrayFieldStart("tasks");
        for (Task task : user.getTasks()) {
            gen.writeObject(task);
        }
        gen.writeEndArray();
        gen.writeEndObject();
        
    }
    
}
