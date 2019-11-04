package gamearea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameAreaTest {

    @Test
    void newHand() {
        GameArea g = new GameArea(17, 24);
        Tetronimo tmp = g.next.t.get(0);
        g.newHand();
        assertEquals(g.hand, tmp);
    }

    @Test
    void swapHold() {
        GameArea g = new GameArea(17, 24);
        g.newHand();
        assertEquals(g.hold, null);
        Tetronimo tmp = g.hand;
        g.swapHold();
        assertEquals(tmp, g.hold);
        g.swapHold();
        assertEquals(tmp, g.hand);


    }
}