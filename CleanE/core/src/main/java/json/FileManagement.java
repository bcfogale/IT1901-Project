package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;

public class FileManagement {

    private ObjectMapper mapper;
    private final File file = new File("../savestates/savefile.json");

    /**
     * Oppsett av mapper gjøres i konstruktøren.
     */
    public FileManagement() {
        mapper = new ObjectMapper();
        CleanEModule mod = new CleanEModule();
        mapper.registerModule(mod);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    /**
     * Metode som skriver til fil
     * @param leaderboard -> Leaderboard-instansen du vil skrive til fil 
     * @throws IOException
     */
    public void writeToFile(Leaderboard leaderboard) throws IOException{
        mapper.writeValue(file, leaderboard);
    }

    /**
     * Metode som leser fra fil
     * @return Leaderboard-instansen som leses inn og vises i programmet
     * @throws IOException
     */
    public Leaderboard readFromFile() throws IOException{
        return mapper.readerFor(Leaderboard.class).readValue(file);
        
    }
    
    
}
