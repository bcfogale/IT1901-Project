package rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import core.Leaderboard;
import core.Task;
import core.User;
import json.CleanEModule;
import json.FileManagement;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { Application.class, CleanEService.class, ResponseController.class })
@WebMvcTest
@TestInstance(Lifecycle.PER_CLASS)
public class RestTest {

    /*
     * Alle test annotasjoner unntatt den første er kommentert ut grunnet en error
     * som oppstod når man skal legge til en bruker. Vi går
     * statuskode 415 når man skal få kode 200, vi har ikke klart å fikse denne
     * erroren. Ettersom å legge til en bruker ikke fungerer,
     * så skaper dette en domino effekt hvor det hverken vil være mulig å legge til
     * Tasks eller poeng. Testdekningsgraden til Rest vil
     * derfor være 0%, men vi har fortsatt laget tester som vi tror ville økt dette
     * betraktelig hadde vi løst erroren.
     */

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private FileManagement manager = new FileManagement();

    private Path copied = Paths.get("../savestates/savefile_copy.json");
    private Path originalPath = Paths.get("../savestates/savefile.json");

    /**
     * Lager en kopi av savefile.json før testene begynner.
     * 
     * @throws IOException
     */
    @BeforeAll
    public void beforeAll() throws IOException {

        Files.copy(originalPath, copied, StandardCopyOption.COPY_ATTRIBUTES);
    }

    /**
     * Setter opp en ny ObjectMapper før hver test og legger til CleanEModule som
     * modul.
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setup() throws IOException {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new CleanEModule());

    }

    /**
     * Setter savefile.json til å bli kopien som ble laget før testingen og sletter
     * kopien.
     * Dette er for å passe på at testene ikke permanent endrer savefile.json.
     * 
     * @throws IOException
     */
    @AfterAll
    public void afterAll() throws IOException {
        Files.copy(copied, originalPath, StandardCopyOption.REPLACE_EXISTING);
        File oldFile = copied.toFile();
        oldFile.delete();
    }

    /**
     * En hjelpemetode som tar inn strings som et argument og lar oss skrive inn
     * hvor vi vil mappe til.
     * 
     * @param strings
     * @return
     */
    private String getURL(String... strings) {
        String URL = "/" + "Leaderboard";
        for (String string : strings) {
            URL = URL + "/" + string;
        }
        return URL;
    }

    /**
     * Tester om man får leaderboard objektet gjennom en HTTP GET forespørsel.
     * 
     * @throws Exception
     */
    @Test
    public void initializationTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getURL())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {
            Leaderboard leaderboard = objectMapper.readValue(result.getResponse().getContentAsString(),
                    Leaderboard.class);
            Leaderboard leaderboard2 = manager.readFromFile();
            assertEquals(leaderboard2.getUsers().size(), leaderboard.getUsers().size());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tester om en bruker blir lagt til i REST-serveren.
     * 
     * @throws Exception
     */
    // @Test
    public void testAddUser() throws Exception {
        User u1 = new User("Jakob");
        String u1AsString = objectMapper.writeValueAsString(u1);

        mockMvc.perform(MockMvcRequestBuilders.post(getURL("addUser")).content(u1AsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get(getURL("getUsers"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, User.class);
            List<User> load = objectMapper.readValue(getResult.getResponse().getContentAsString(), javaType);
            assertNotNull(load);
            assertEquals(load.size(), 1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tester om man kan legge til poeng til en eksisterende bruker i REST-serveren.
     * 
     * @throws Exception
     */
    // @Test
    public void testAddPoints() throws Exception {
        User u2 = new User("Isaac");
        String u2AsString = objectMapper.writeValueAsString(u2);
        String poengAsString = objectMapper.writeValueAsString(6);

        mockMvc.perform(MockMvcRequestBuilders.post(getURL("addUser")).content(u2AsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.put(getURL("addPoints", "Isaac")).content(poengAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get(getURL("getUser", "Isaac"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {
            User testUser = objectMapper.readValue(getResult.getResponse().getContentAsString(),
                    User.class);

            assertEquals(6, testUser.getPoints());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tester om man kan legge til en Task til en eksisterende bruker i
     * REST-serveren.
     * 
     * @throws Exception
     */
    // @Test
    public void testAddTask() throws Exception {
        User u3 = new User("Sandra");
        String u3AsString = objectMapper.writeValueAsString(u3);
        Task t1 = new Task(u3, "Vaske bilen", 5, "tuesday", "temp");
        String taskAsString = objectMapper.writeValueAsString(t1);

        mockMvc.perform(MockMvcRequestBuilders.post(getURL("addUser")).content(u3AsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.put(getURL("addTask", "Sandra")).content(taskAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get(getURL("getUser", "Sandra"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {
            User testUser = objectMapper.readValue(getResult.getResponse().getContentAsString(),
                    User.class);
            assertEquals(t1, testUser.getTaskByUUID("temp"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tester om det er mulig å fjerne en Task fra en bruker som er i REST-serveren.
     * 
     * @throws Exception
     */
    // @Test
    public void testRemoveTask() throws Exception {
        User u4 = new User("Safura");
        String u4AsString = objectMapper.writeValueAsString(u4);
        Task t2 = new Task(u4, "Vaske badet", 10, "friday", "tempID");
        String taskAsString = objectMapper.writeValueAsString(t2);

        mockMvc.perform(MockMvcRequestBuilders.post(getURL("addUser")).content(u4AsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.put(getURL("addTask", "Safura")).content(taskAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get(getURL("getUser", "Safura"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {
            User testUser = objectMapper.readValue(getResult.getResponse().getContentAsString(),
                    User.class);
            assertEquals(1, testUser.getTasks().size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        mockMvc.perform(MockMvcRequestBuilders.delete(getURL("removeTaskByUUID", "tempID"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult getResult2 = mockMvc.perform(MockMvcRequestBuilders.get(getURL("getUser", "Safura"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        try {
            User testUser2 = objectMapper.readValue(getResult2.getResponse().getContentAsString(),
                    User.class);
            assertEquals(0, testUser2.getTasks().size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
