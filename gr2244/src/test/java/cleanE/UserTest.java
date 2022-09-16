package cleanE;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cleane.User;

// Test for first iteration

public class UserTest 
{
    
    @Test
    public void testConstructor()
    {
        User s = new User("Sander");
        assertEquals(0, s.getPoints());
        assertEquals("Sander", s.getName());
        assertTrue(s.getTasks().isEmpty()); 
    }
}
