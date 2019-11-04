package gamearea;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListMatrixTest {
    ArrayListMatrix area;

    @Test
    void Constructor() {
        area = new ArrayListMatrix(5, 4);
        assertEquals(area.get(0).size(), 4);
        assertEquals(area.size(), 5);
        assertEquals(area.get(2).get(3).type, 'N');
    }

    @Test
    void delLine() {
        area = new ArrayListMatrix(5, 6);
        area.get(2).get(1).type = 'A';
        area.get(1).get(0).type = 'A';
        area.get(3).get(2).type = 'A';
        area.delLine(2);
        assertEquals(area.get(2).get(0).type, 'A'); // moved one line down
        assertEquals(area.get(3).get(2).type, 'A');
        assertEquals(area.size(), 5);
        assertEquals(area.get(0).size(), 6);
    }

    @Test
    void setSquare() {
        area = new ArrayListMatrix(5, 6);
        area.setSquare(0, 2, 'B');
        area.setSquare(3, 5, 'B');
        area.setSquare(4, 1, 'B');
        assertEquals(area.get(0).get(2).type, 'B');
        assertEquals(area.get(3).get(5).type, 'B');
        assertEquals(area.get(4).get(1).type, 'B');
    }

    @Test
    void getWidth() {
        area = new ArrayListMatrix(7, 3);
        assertEquals(area.getWidth(), 3);
    }

    @Test
    void getHeight() {
        area = new ArrayListMatrix(8, 3);
        assertEquals(area.getHeight(), 8);
    }

    @Test
    void getSquare() {
        area = new ArrayListMatrix(5, 6);
        area.setSquare(4, 3, 'C');
        assertEquals(area.getSquare(4, 5).type, 'N');
        assertEquals(area.getSquare(4, 3).type, 'C');
    }
}