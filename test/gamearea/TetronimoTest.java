package gamearea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TetronimoTest {

    @Test
    void Constructor_and_getArray() {
        Tetronimo t = new Tetronimo('T');
        boolean[][] b = new boolean[][]{
                {false, false, false},
                {false, true, false},
                {true, true, true}
        };
        assertArrayEquals(t.getArray(), b);
    }

    @Test
    void getSize() {
        Tetronimo s2 = new Tetronimo('O');
        Tetronimo s3 = new Tetronimo('T');
        Tetronimo s4 = new Tetronimo('I');
        assertEquals(s2.getSize(), 2);
        assertEquals(s3.getSize(), 3);
        assertEquals(s4.getSize(), 4);
    }

    @Test
    void getType() {
        Tetronimo s2 = new Tetronimo('O');
        Tetronimo s3 = new Tetronimo('T');
        Tetronimo s4 = new Tetronimo('I');
        assertEquals(s2.getType(), 'O');
        assertEquals(s3.getType(), 'T');
        assertEquals(s4.getType(), 'I');
    }

    @Test
    void rotateLeft() {
        Tetronimo t = new Tetronimo('T');
        t.RotateLeft();
        boolean[][] b = new boolean[][]{
                {false, false, true},
                {false, true, true},
                {false, false, true}
        };
        assertArrayEquals(t.getArray(), b);
    }

    @Test
    void rotateRight() {
        Tetronimo t = new Tetronimo('T');
        t.RotateRight();
        boolean[][] b = new boolean[][]{
                {true, false, false},
                {true, true, false},
                {true, false, false}
        };
        assertArrayEquals(t.getArray(), b);
    }

    @Test
    void getRotateLeft() {
        Tetronimo t = new Tetronimo('Z');
        boolean[][] b = new boolean[][]{
                {false, false, true},
                {false, true, true},
                {false, true, false}
        };
        assertArrayEquals(t.getRotateLeft(), b);
    }

    @Test
    void getRotateRight() {
        Tetronimo t = new Tetronimo('Z');
        boolean[][] b = new boolean[][]{
                {false, true, false},
                {true, true, false},
                {true, false, false}
        };
        assertArrayEquals(t.getRotateRight(), b);
    }
}