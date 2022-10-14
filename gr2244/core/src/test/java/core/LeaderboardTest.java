package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LeaderboardTest {

    Leaderboard l;
    
    @BeforeEach
    public void setup(){
        l = new Leaderboard();
    }

    @Test
    public void testConstructor(){
        assertEquals(0, l.getUsers().size());
    }

    @Test
    public void testSortList() {
        User u = new User("Sander");
        User i = new User("Sandra");
        User o = new User("Safura");

        l.addUser(o);
        l.addUser(u);
        l.addUser(i);

        u.addPoints(1);
        i.addPoints(10);
        o.addPoints(20);

        assertEquals(o, l.getUsers().get(0));
        assertEquals(u, l.getUsers().get(1));
        assertEquals(i, l.getUsers().get(2));

        l.sortList();

        assertEquals(o, l.getUsers().get(0));
        assertEquals(i, l.getUsers().get(1));
        assertEquals(u, l.getUsers().get(2));


    }

    @Test
    public void testAddUser() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        User u = new User("Sander");

        l.addUser(u);
        l.addUser(u);

        assertEquals("User already in list.\r\n", outContent.toString());

        System.setOut(originalOut);
    }
}
