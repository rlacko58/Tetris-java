package gamearea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NextTest {

    @Test
    void constructor() {
        Next p = new Next();
        assertEquals(p.t.size(), 3);
    }

    @Test
    void getTetro() {
        Next p = new Next();
        Tetronimo second = p.t.get(1);
        p.getTetro();
        assertEquals(p.getTetro(), second);
        assertEquals(p.t.size(), 3);
    }

    @Test
    void displayPocket() {
        Next p = new Next();
        char[] c = new char[]{
                p.t.get(0).getType(), p.t.get(1).getType(), p.t.get(2).getType()
        };
        assertArrayEquals(p.displayPocket(), c);
    }
}