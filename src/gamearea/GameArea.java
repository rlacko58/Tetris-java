package gamearea;

import score.HighScore;

import java.util.ArrayList;
import java.util.Timer;

public class GameArea {
    Tetronimo hold;
    Next next;
    HighScore hs;
    Tetronimo hand;
    Coord c;
    ArrayListMatrix area;

    public GameArea(int height, int width){
        hold = null;
        next = new Next();
        hs = new HighScore(); //load from save
        hand = null;
        area = new ArrayListMatrix(height, width);
    }

    public void newHand(){
        hand = next.getTetro();
        c = new Coord(area.getWidth(), hand.getSize());
    }

    public void swapHold(){
        if(hold == null){
            hold = hand;
            hand = next.getTetro();
        } else {
            Tetronimo tmp = hold;
            hold = hand;
            hand = tmp;
        }
    }
}
