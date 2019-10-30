package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PocketTest {

    @Test
    void constructor() {
        Pocket p = new Pocket();
        assertEquals(p.t.size(), 3);
    }

    @Test
    void getTetro() {
        Pocket p = new Pocket();
        Tetronimo second = p.t.get(1);
        p.getTetro();
        assertEquals(p.getTetro(), second);
        assertEquals(p.t.size(), 3);
    }

    @Test
    void displayPocket() {
        Pocket p = new Pocket();
        char[] c = new char[]{
                p.t.get(0).getType(),p.t.get(1).getType(),p.t.get(2).getType()
        };
        assertArrayEquals(p.displayPocket(), c);
    }
}