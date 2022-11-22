package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    User u;
    Task t;

    /**Konstruerer nytt User objekt og nytt Task objekt før hver test. */
    @BeforeEach
    public void setup() {
        u = new User("name");
        t = new Task(u, "taskName", 5, "monday");
    }

    /**Tester om konstruktøren lager et nytt Task objekt. */
    @Test
    public void testConstructor() {
        assertEquals("taskName", t.getTaskName());
        assertEquals(u, t.getAssignedUser());
        assertEquals(5, t.getPointsValue());
        assertEquals("monday", t.getDueDay());
        assertEquals(false, t.isCompleted());
        assertThrows(IllegalArgumentException.class, () -> new Task(u, "taskName", 5, "NotAVaildDay"));
    }

    /**Tester om setTrue metoden funker. */
    @Test
    public void testSetTrue() {
        t.setTrue();
        assertEquals(true, t.isCompleted());

    }

}
