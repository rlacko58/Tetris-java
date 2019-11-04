package gamearea;

import score.HighScore;

import java.util.ArrayList;
import java.util.Timer;

public class GameArea {
    Tetronimo hold;
    Next next;
    HighScore hs;
    Tetronimo hand;
    ArrayListMatrix area;

    public GameArea(int height, int width){
        hold = null;
        next = new Next();
        hs = new HighScore(); //load from save
        hand = null;
        area = new ArrayListMatrix(height, width);
    }
}
