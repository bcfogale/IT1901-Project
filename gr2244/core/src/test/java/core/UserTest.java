package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Task;
import core.User;

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
        Task t = new Task(s, "taskName", 5, "monday");
        assertEquals(1, s.getTasks().size());
    }

    @Test
    public void testAddPoint(){
        s.addPoints(10);
        assertEquals(10, s.getPoints());
        s.addPoints(40);
        assertEquals(50, s.getPoints());
    }
}
