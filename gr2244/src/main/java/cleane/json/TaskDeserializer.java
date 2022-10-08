package cleane.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import cleane.Task;

public class TaskDeserializer extends JsonDeserializer<Task>{

    public Task deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
        
            JsonNode taskNameNode = objectNode.get("taskName");
            JsonNode pointsValueNode = objectNode.get("pointsValue");
            JsonNode completedNode = objectNode.get("completed");
            JsonNode dueDayNode = objectNode.get("dueDay");
            
            if (taskNameNode instanceof TextNode && pointsValueNode instanceof IntNode && completedNode instanceof BooleanNode && dueDayNode instanceof TextNode) {
                String taskName = ((TextNode) taskNameNode).asText();
                int pointsValue = ((IntNode) pointsValueNode).asInt();
                String dueDay = ((TextNode) dueDayNode).asText();
                Task task = new Task(taskName, pointsValue, dueDay);
                if (((BooleanNode) completedNode).asBoolean()) {
                    task.setTrue();
                }
                return task;
            }
        }
        return null;
    }
    @Override
    public Task deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }
    
}
    
