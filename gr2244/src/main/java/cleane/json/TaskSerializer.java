package cleane.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cleane.Task;

/* 
 * format:
 * {
 *      "name": "..."
 *      "user": User u
 *      "points": 5
 *      "completed": false
 *      "due": "monday"
 * 
 * }
*/

public class TaskSerializer extends JsonSerializer<Task>{

    @Override
    public void serialize(Task task, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", task.getTaskName());
        gen.writeObjectField("user", task.getAssignedUser());
        gen.writeBooleanField("completed", task.isCompleted());
        gen.writeStringField("due", task.getDueDay());
        gen.writeEndObject();
        
    }
    
}
