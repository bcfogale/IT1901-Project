package json;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;

public class FileManagement {

    private ObjectMapper mapper;
    private final static File file = new File("../savestates/savefile.json");

    private boolean b1;
    private boolean b2;

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

    public void deleteSavedContent() throws IOException {
        String s = System.getProperty("user.dir");
        File f = new File(s+"/CleanE/savestates/savefile.json");

        b1 = f.delete();
        b2 = f.createNewFile();
    }

    public boolean isB1() {
        return b1;
    }

    public boolean isB2() {
        return b2;
    } 
    
    public static void main(String[] args) throws IOException {
        FileManagement m = new FileManagement();
        m.deleteSavedContent();
    }
}
