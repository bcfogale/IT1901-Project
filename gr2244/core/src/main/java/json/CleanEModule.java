package json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Leaderboard;
import core.Task;
import core.User;

public class CleanEModule extends SimpleModule {
    private static final String NAME = "CleanEModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {
    };

    public CleanEModule() {
        super(NAME, VERSION_UTIL.version());
        // We don't have custom serializers for User and Task
        // Because of cyclic referencing this would cause issues
        // The issues are easily resolved with the automatic serialization and the use
        // of @JsonIdentityInfo in the User class
        addSerializer(Leaderboard.class, new LeaderboardSerializer());
        addDeserializer(Leaderboard.class, new LeaderboardDeserializer());
        addDeserializer(User.class, new UserDeserializer());
        addDeserializer(Task.class, new TaskDeserializer());
    }

}
