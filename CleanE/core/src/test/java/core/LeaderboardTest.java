package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderboardTest {

    Leaderboard l;

    /**Konstruerer et nytt Leaderboard objekt før hver test. */
    @BeforeEach
    public void setup() {
        l = new Leaderboard();
    }

    /**Tester om konstruktøren faktisk lager et objekt. */
    @Test
    public void testConstructor() {
        assertEquals(0, l.getUsers().size());
    }

    /**Tester om sortList-funksjonen sorterer listen etter antall poeng. */
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
}
