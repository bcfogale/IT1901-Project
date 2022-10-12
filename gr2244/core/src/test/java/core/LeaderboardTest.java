package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Leaderboard;

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
}
