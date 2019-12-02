package gamearea;

import javafx.fxml.FXML;
import score.HighScore;
import score.Score;

import java.util.ArrayList;
import java.util.Timer;

/**
 * The class which contains everything on the map
 */
public class GameArea {
    /**
     * Tetronimo which is in the players pocket
     */
    Tetronimo hold;
    /**
     * Next Tetronimos
     */
    Next next;
    /**
     * Tetronimo that the player moves
     */
    Tetronimo hand;
    /**
     * Coordinate of the hand in the map
     */
    Coord c;
    /**
     * The matrix of the map
     */
    ArrayListMatrix area;
    /**
     * Player's points
     */
    int points = 0;
    /**
     * Is the player able to swap with the pocket's content
     */
    boolean canSwap;
    /**
     * Number of full lines that got deleted
     */
    int lines = 0;
    /**
     * Difficulty level
     */
    int level = 1;

    /**
     * Initializes the map with the given sizes
     * @param height Number of Rows
     * @param width Number of Columns
     */
    public GameArea(int height, int width){
        hold = null;
        next = new Next();
        hand = null;
        area = new ArrayListMatrix(height, width);
        newHand();
        canSwap = true;
    }

    /**
     * Returns the number of deleted lines
     * @return Number of deleted lines
     */
    public int getLines(){
        return lines;
    }

    /**
     * Returns the difficulty level
     * @return Difficulty level
     */
    public int getLevel(){
        return level;
    }

    //rotateLeft currently is not being used
    /*
    public void rotateLeft(){
        if(!checkCollision(hand.getRotateLeft(), c.x, c.y)){
            hand.RotateLeft();
        }
    }*/

    /**
     * Rotates the hand Tetronimo clockwise
     */
    public void rotateRight(){
        if(!checkCollision(hand.getRotateRight(), c.x, c.y)){
            hand.RotateRight();
        }
    }

    /**
     * Moves down the hand, if it doesn't move, returns with false
     * @return Succesful move
     */
    public boolean moveDown(){
        if(!checkCollision(hand.getArray(), c.x, c.y+1)){
            c.y = c.y+1;
            return true;
        } else {
            placeHandInToMap();
            return false;
        }
    }

    /**
     * Moves the hand to the right
     * @return Succesful move
     */
    public boolean moveRight(){
        if(!checkCollision(hand.getArray(), c.x+1, c.y)){
            c.x = c.x+1;
            return true;
        }
        return false;
    }

    /**
     * Moves the hand to the left
     * @return Succesful move
     */
    public boolean moveLeft(){
        if(!checkCollision(hand.getArray(), c.x-1, c.y)){
            c.x = c.x-1;
            return true;
        }
        return false;
    }

    /**
     * Places the hand Tetronimo to the lowest point possible (moves down the hand first)
     */
    public void placeHandToDown(){
        c = getDownTetroCoord();
        placeHandInToMap();
    }

    /**
     * After placing the hand checks lines and adds points
     */
    private void afterPlace(){
        points += 100;
        for(int i=0; i<hand.getSize(); i++){
            if(c.y+i<area.getHeight()){
                fixLine(c.y+i);
            }
        }
    }

    /**
     * Deletes the line if possible and updates the lines and points
     * @param num Line number
     */
    private void fixLine(int num){
        boolean gotcha = true;

        for(int i=0; i<area.get(num).size(); i++){
            if(!area.getSquare(num, i).val){
                gotcha = false;
            }
        }
        if(gotcha){
            area.delLine(num);
            lines += 1;
            points += (int)level*0.2*area.getWidth()*100;
        }
    }

    /**
     * Returns the points of the player
     * @return Current Points
     */
    public int getPoints(){
        return points;
    }

    /**
     * Places the hand Tetronimo to the map
     */
    private void placeHandInToMap(){
        for(int i=0; i<hand.getSize(); i++){
            for(int j=0; j<hand.getSize(); j++){
                if(hand.getArray()[i][j]){
                    area.setSquare(j+c.y, i+c.x, hand.getType());
                }
            }
        }
        afterPlace();
    }

    /**
     * Check collision in the map with the given boolean matrix of the Tetronimo
     * @param tetr boolean matrix
     * @param x Column number
     * @param y Row number
     * @return true if there is collision
     */
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

    /**
     * Puts a new hand to the map. If there is collision returns with false
     * @return Succesful hand generation
     */
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

    /**
     * Swaps the hand with hold (puts to the pocket)
     */
    public void swapHold(){
        if(hold == null){
            hold = hand;
            c = new Coord(area.getWidth(), area.getHeight());
            hand = next.getTetro();
        } else {
            if(canSwap){
                Tetronimo tmp = hold;
                c = new Coord(area.getWidth(), area.getHeight());
                hold = hand;
                hand = tmp;
                canSwap = false;
            }
        }
    }

    /**
     * Returns the next two tetronimo
     * @return The next two tetronimo
     */
    public Tetronimo[] getNexts(){
        char[] nexts = next.displayPocket();
        return new Tetronimo[]{
                new Tetronimo(nexts[0]),
                new Tetronimo(nexts[1])
        };
    }

    /**
     * Returns the pocket
     * @return The pocket
     */
    public Tetronimo getPocket(){
        return hold;
    }

    /**
     * Returns a full map with hand for the graphics
     * @return ArrayListMatrix copy of the map with the hand in it
     */
    public ArrayListMatrix getMapwithHand(){
        ArrayListMatrix copyArea = new ArrayListMatrix(area);
        boolean[][] h = hand.getArray();
        if(!checkCollision(hand.getArray(),c.x, c.y)){
            for(int i=0; i<hand.getSize(); i++){
                for(int j=0; j<hand.getSize(); j++){
                    if(h[i][j]){
                        copyArea.setSquare(j+c.y, i+c.x, hand.getType());
                    }
                }
            }
        }
        return copyArea;
    }

    /**
     * Returns the lowest coordinate for the hand
     * @return Lowest coordinate for the hand
     */
    public Coord getDownTetroCoord(){
        int newY = c.y;
        while(!checkCollision(hand.getArray(), c.x, newY+1)){
            newY += 1;
        }
        return new Coord(c.x, newY, true);
    }

    /**
     * Returns the hand
     * @return hand
     */
    public Tetronimo getDownTetro(){
        return hand;
    }

    /**
     * Returns the hand's coordinate
     * @return coordinate
     */
    public Coord getCoord(){
        return c;
    }
}
