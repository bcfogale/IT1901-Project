package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import core.Leaderboard;
import core.User;

public class LeaderboardDeserializer extends JsonDeserializer<Leaderboard> {

    private UserDeserializer userDeserializer = new UserDeserializer();

    /** Deserialiserer Leaderboard-klassen for JSON lagring. */
    @Override
    public Leaderboard deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            Leaderboard leaderboard = new Leaderboard();
            JsonNode userListNode = objectNode.get("users");
            if (userListNode instanceof ArrayNode) {
                for (JsonNode userNode : userListNode) {
                    User user = userDeserializer.deserialize(userNode);
                    if (user != null) {
                        leaderboard.addUser(user);
                    }
                }
            }
            return leaderboard;
        }
        return null;
    }

}
