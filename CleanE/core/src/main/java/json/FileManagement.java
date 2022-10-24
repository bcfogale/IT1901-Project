package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;

public class FileManagement {

    private ObjectMapper mapper;
    private final File file = new File("../savestates/savefile.json");

    public FileManagement() {
        mapper = new ObjectMapper();
        CleanEModule mod = new CleanEModule();
        mapper.registerModule(mod);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public void writeToFile(Leaderboard leaderboard) throws IOException{
        mapper.writeValue(file, leaderboard);
    }

    public Leaderboard readFromFile() throws IOException{
        return mapper.readerFor(Leaderboard.class).readValue(file);
        
    }
    
    
}
