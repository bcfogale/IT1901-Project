package cleane.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import cleane.Task;
import cleane.User;

public class UserDeserializer extends JsonDeserializer<User> {
    TaskDeserializer taskDeserializer = new TaskDeserializer();

    public User deserialize(JsonNode jsonNode) {

        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode nameNode = objectNode.get("name");

            if (nameNode instanceof TextNode) {
                User user = new User(((TextNode) nameNode).asText());
                JsonNode pointNode = objectNode.get("points");

                if (pointNode instanceof IntNode) {
                    user.addPoints(((IntNode) pointNode).asInt());
                }
                JsonNode taskListNode = objectNode.get("tasks");
                if (taskListNode instanceof ArrayNode) {

                    for (JsonNode taskNode : ((ArrayNode) taskListNode)) {

                        Task task = taskDeserializer.deserialize(taskNode);
                        if (task != null) {
                            task.setAssignedUser(user);
                            user.addTask(task);
                        }
                        
                    }
                }
                return user;
            }
        }
        return null;
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

}
