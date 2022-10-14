package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test for first iteration
public class UserTest {

    User s;

    @BeforeEach
    public void setup(){
        s = new User("Sander");
    }
    
    @Test
    public void testConstructor(){
        assertEquals(0, s.getPoints());
        assertEquals("Sander", s.getName());
        assertTrue(s.getTasks().isEmpty()); 
    }

    @Test
    public void testAddTask(){
        s.addTask(new Task("taskName", 5, "monday"));;
        assertEquals(1, s.getTasks().size());
    }

    @Test
    public void testAddPoint(){
        s.addPoints(10);
        assertEquals(10, s.getPoints());
        s.addPoints(40);
        assertEquals(50, s.getPoints());
    }

    @Test
    public void testSetName() {
        assertEquals("Sander", s.getName());
        
        s.setName("Benji");

        assertEquals("Benji", s.getName());
    }

    @Test
    public void testRemoveTask() {
        Task t = new Task(s, "Vaske leilighet", 3, "monday");

        assertEquals(1, s.getTasks().size());

        s.removeTask(t);

        assertEquals(0, s.getTasks().size());


    }
}
