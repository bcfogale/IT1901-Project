package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    User s;

    /**Lager ny User objekt før hver test. */
    @BeforeEach
    public void setup() {
        s = new User("Sander");
    }

    /**Tester konstruktøren til User-klassen. */
    @Test
    public void testConstructor() {
        assertEquals(0, s.getPoints());
        assertEquals("Sander", s.getName());
        assertTrue(s.getTasks().isEmpty());
    }

    /**Tester om addTask metoden legger til oppgaver til brukeren. */
    @Test
    public void testAddTask() {
        s.addTask(new Task("taskName", 5, "monday", UUID.randomUUID().toString()));
        
        assertEquals(1, s.getTasks().size());
    }

    /**Tester om addPoint metoden legger til poeng. */
    @Test
    public void testAddPoint() {
        s.addPoints(10);
        assertEquals(10, s.getPoints());
        s.addPoints(40);
        assertEquals(50, s.getPoints());
    }

    /**Tester om det er mulig å endre navn på brukeren med setName metoden. */
    @Test
    public void testSetName() {
        assertEquals("Sander", s.getName());

        s.setName("Benji");

        assertEquals("Benji", s.getName());
    }

    /**Tester om det er mulig å fjerne Task objekter fra en bruker. */
    @Test
    public void testRemoveTask() {
        Task t = new Task(s, "Vaske leilighet", 3, "monday");

        assertEquals(1, s.getTasks().size());

        s.removeTask(t);

        assertEquals(0, s.getTasks().size());

    }
}
