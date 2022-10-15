package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    User u;
    Task t;

    @BeforeEach
    public void setup() {
        u = new User("name");
        t = new Task(u, "taskName", 5, "monday");
    }

    @Test
    public void testConstructor() {
        assertEquals("taskName", t.getTaskName());
        assertEquals(u, t.getAssignedUser());
        assertEquals(5, t.getPointsValue());
        assertEquals("monday", t.getDueDay());
        assertEquals(false, t.isCompleted());
        assertThrows(IllegalArgumentException.class, () -> new Task(u, "taskName", 5, "NotAVaildDay"));
    }

    @Test
    public void testSetTrue() {
        t.setTrue();
        assertEquals(true, t.isCompleted());

    }

}
