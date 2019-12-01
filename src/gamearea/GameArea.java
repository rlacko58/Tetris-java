package gamearea;

import javafx.fxml.FXML;
import score.HighScore;
import score.Score;

import java.util.ArrayList;
import java.util.Timer;

public class GameArea {
    Tetronimo hold;
    Next next;
    Tetronimo hand;
    Coord c;
    ArrayListMatrix area;
    int points = 0;
    boolean canSwap;
    int lines = 0;

    public GameArea(int height, int width){
        hold = null;
        next = new Next();
        hand = null;
        area = new ArrayListMatrix(height, width);
        newHand();
        canSwap = true;
    }

    public void rotateLeft(){
        if(!checkCollision(hand.getRotateLeft(), c.x, c.y)){
            hand.RotateLeft();
        }
    }

    public void rotateRight(){
        if(!checkCollision(hand.getRotateRight(), c.x, c.y)){
            hand.RotateRight();
        }
    }

    public boolean moveDown(){
        if(!checkCollision(hand.getArray(), c.x, c.y+1)){
            c.y = c.y+1;
            return true;
        } else {
            placeHandInToMap();
            return false;
        }
    }
    public boolean moveRight(){
        if(!checkCollision(hand.getArray(), c.x+1, c.y)){
            c.x = c.x+1;
            return true;
        }
        return false;
    }
    public boolean moveLeft(){
        if(!checkCollision(hand.getArray(), c.x-1, c.y)){
            c.x = c.x-1;
            return true;
        }
        return false;
    }

    public void placeHandToDown(){
        Coord downCoord = getDownTetroCoord();
        for(int i=0; i<hand.getSize(); i++){
            for(int j=0; j<hand.getSize(); j++){
                if(hand.getArray()[i][j]){
                    area.setSquare(j+downCoord.y, i+downCoord.x, hand.getType());
                }
            }
        }

        points += 100;
    }

    public int getPoints(){
        return points;
    }

    private void placeHandInToMap(){
        for(int i=0; i<hand.getSize(); i++){
            for(int j=0; j<hand.getSize(); j++){
                if(hand.getArray()[i][j]){
                    area.setSquare(j+c.y, i+c.x, hand.getType());
                }
            }
        }
        points += 100;
    }

    private boolean checkCollision(boolean[][] tetr, int x, int y){
        for(int i=0; i<tetr.length; i++){
            for(int j=0; j<tetr.length; j++){
                if(tetr[i][j]){
                    if(j+y<0 || j+y >=area.getHeight() || i+x < 0 || i+x >= area.getWidth()){
                        return true;
                    }
                    if(area.getSquare(j+y, i+x).val){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean newHand(){
        hand = next.getTetro();
        c = new Coord(area.getWidth(), hand.getSize());
        canSwap = true;
        if(checkCollision(hand.getArray(), c.x, c.y)){
            return false;
        } else{
            return true;
        }
    }

    public void swapHold(){
        if(hold == null){
            hold = hand;
            hand = next.getTetro();
        } else {
            if(canSwap){
                Tetronimo tmp = hold;
                hold = hand;
                hand = tmp;
                canSwap = false;
            }
        }
    }

    public Tetronimo[] getNexts(){
        char[] nexts = next.displayPocket();
        return new Tetronimo[]{
                new Tetronimo(nexts[0]),
                new Tetronimo(nexts[1])
        };
    }

    public Tetronimo getPocket(){
        return hold;
    }

    public ArrayListMatrix getMapwithHand(){
        ArrayListMatrix copyArea = new ArrayListMatrix(area);
        boolean[][] h = hand.getArray();
        for(int i=0; i<hand.getSize(); i++){
            for(int j=0; j<hand.getSize(); j++){
                if(h[i][j]){
                    copyArea.setSquare(j+c.y, i+c.x, hand.getType());
                }
            }
        }
        return copyArea;
    }

    public Coord getDownTetroCoord(){
        int newY = c.y;
        while(!checkCollision(hand.getArray(), c.x, newY+1)){
            newY += 1;
        }
        return new Coord(c.x, newY, true);
    }

    public Tetronimo getDownTetro(){
        return hand;
    }



    public Coord getCoord(){
        return c;
    }
}
