package main;

import gamearea.ArrayListMatrix;
import gamearea.Coord;
import gamearea.GameArea;
import gamearea.Tetronimo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import score.Score;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


/**
 * PlayArea scene's JavaFX controller
 */
public class PlayArea implements Initializable {
    /**
     * The gameArea which contains the logic of the game
     */
    public GameArea gameArea;

    /**
     * Gridpane for the gamePanel
     */
    public GridPane gamePanel;
    /**
     * Gridpane for the pocket
     */
    public GridPane pocket;
    /**
     * Gridpane for the next tetronimo
     */
    public GridPane next1;
    /**
     * Gridpane for the second next tetronimo
     */
    public GridPane next2;

    /**
     * Text for displaying the gamearea's size
     */
    public Text size;
    /**
     * Text for displaying the time
     */
    public Text time;
    /**
     * Text for displaying the points
     */
    public Text points;
    /**
     * Text for displaying the level
     */
    public Text level;
    /**
     * Text for displaying the full lines deleted
     */
    public Text lines;

    /**
     * Size of the Rectangle's
     */
    private int SIZE = Main.SIZE;

    /**
     * Matrix for the map rectangles
     */
    ArrayList<ArrayList<Rectangle>> myMap;
    /**
     * Matrix for the pocket rectangles
     */
    ArrayList<ArrayList<Rectangle>> myPocket;
    /**
     * Matrix for the next1 rectangles
     */
    ArrayList<ArrayList<Rectangle>> myNext1;
    /**
     * Matrix for the next2 rectangles
     */
    ArrayList<ArrayList<Rectangle>> myNext2;

    /**
     * Variable for the timer
     */
    int timecounter = 0;
    /**
     * Variable for the fall speed
     */
    int movecounter = 0;

    /**
     * Initializes the map and starts the movement and timer
     * @param location javafx parameter
     * @param resources javafx parameter
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameArea = new GameArea(Main.height, Main.width);

        myMap = new ArrayList<ArrayList<Rectangle>>();
        myPocket = new ArrayList<ArrayList<Rectangle>>();
        myNext1 = new ArrayList<ArrayList<Rectangle>>();
        myNext2 = new ArrayList<ArrayList<Rectangle>>();

        size.setText(Main.height + " X " + Main.width);
        points.setText(String.valueOf(gameArea.getPoints()));
        lines.setText(String.valueOf(gameArea.getLines()));
        level.setText(String.valueOf(gameArea.getLevel()));

        for(int i=0; i<Main.height; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<Main.width; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                rec.setStroke(Color.rgb(0,0,255, 0.5));
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);

                gamePanel.getChildren().addAll(rec);
                line.add(rec);
            }
            myMap.add(line);
        }

        for(int i=0; i<4; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<4; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                rec.setStroke(Color.rgb(0,0,255, 0.5));
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);

                pocket.getChildren().addAll(rec);
                line.add(rec);
            }
            myPocket.add(line);
        }

        for(int i=0; i<4; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<4; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                rec.setStroke(Color.rgb(0,0,255, 0.5));
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);

                next1.getChildren().addAll(rec);
                line.add(rec);
            }
            myNext1.add(line);
        }

        for(int i=0; i<4; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<4; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                rec.setStroke(Color.rgb(0,0,255, 0.5));
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);

                next2.getChildren().addAll(rec);
                line.add(rec);
            }
            myNext2.add(line);
        }

        updateNexts();
        updateMap();
        moveOnKeyPressed();
        Timer fall = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(timecounter >= 100){
                            gameArea.increaseTime();
                            setTime(gameArea.getTime());
                            timecounter = 0;
                        }
                        if(movecounter >= 100*Math.pow(0.9, gameArea.getLevel()-1)){
                            if(!gameArea.moveDown()){
                                if(!gameArea.newHand()){
                                    try {
                                        fall.cancel();
                                        fall.purge();
                                        endGame();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                updateNexts();
                            }
                            updateMap();
                            movecounter = 0;
                        }
                        movecounter++;
                        timecounter++;
                    }
                });
            }
        };
        fall.schedule(task, 0, 10);
    }

    /**
     * Updates the clock
     * @param time time in seconds
     */
    public void setTime(int time){
        int seconds = gameArea.getTime()%60;
        int minutes = (int)gameArea.getTime()/60;
        this.time.setText(String.format("%1$" + 2 + "s", minutes).replace(' ', '0') + ":" + String.format("%1$" + 2 + "s", seconds).replace(' ', '0') );

        updateMap();
    }

    /**
     * Updates the next tetronimos
     */
    public void updateNexts(){
        Tetronimo[] nexts = gameArea.getNexts();
        updateArea(myNext1, nexts[0], 0, 0);
        updateArea(myNext2, nexts[1], 0, 0);
    }

    /**
     * Updates the map
     */
    public void updateMap(){
        ArrayListMatrix map = gameArea.getMapwithHand();

        Tetronimo downTetro = gameArea.getDownTetro();
        Coord downCoord = gameArea.getDownTetroCoord();

        for(int i=0; i<map.getWidth(); i++){
            for(int j=0; j<map.getHeight(); j++){
                if(map.getSquare(j, i).val){
                    myMap.get(j).get(i).setFill(convertToColor(map.getSquare(j, i).type, 1));
                } else {
                    if(i < downCoord.x+downTetro.getSize() && i >= downCoord.x
                            && j < downCoord.y+downTetro.getSize() && j >= downCoord.y){
                        if(downTetro.getArray()[i-downCoord.x][j-downCoord.y]){
                            myMap.get(j).get(i).setFill(convertToColor(downTetro.getType(), 0.5));
                        }else{
                            myMap.get(j).get(i).setFill(Color.BLACK);
                        }
                    }else{
                        myMap.get(j).get(i).setFill(Color.BLACK);
                    }
                }
            }
        }

        points.setText(String.valueOf(gameArea.getPoints()));
        lines.setText(String.valueOf(gameArea.getLines()));
        level.setText(String.valueOf(gameArea.getLevel()));
    }

    /**
     * Key events
     */
    private void moveOnKeyPressed(){
        Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        gameArea.moveRight();
                        break;
                    case LEFT:
                        gameArea.moveLeft();
                        break;
                    case DOWN:
                        if(!gameArea.moveDown()){
                            if(!gameArea.newHand()){
                                try {
                                    endGame();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            updateNexts();
                        }
                        break;
                    case UP:
                        gameArea.rotateRight();
                        break;
                    case SPACE:
                        gameArea.placeHandToDown();

                        if(!gameArea.newHand()){
                            try {
                                endGame();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        updateNexts();
                        break;
                    case C:
                        gameArea.swapHold();
                        updateNexts();
                        updatePocket();
                        break;
                }
                updateMap();
            }
        });
    }

    /**
     * Ends the game, saves the score and swaps scene to the ScoreBoard
     */
    private void endGame() {
        Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // disable
            }
        });
        Main.scoreTable.addScore(new Score("anonymous", gameArea.getPoints()));
        Main.screenController.activate("ScoreBoard");
    }

    /**
     * Updates the pocket
     */
    public void updatePocket(){
        updateArea(myPocket, gameArea.getPocket(), 0, 0);
    }


    /**
     * Updates a given area in the rectangle matrix
     * @param array Matrix of rectangles
     * @param tetr Tetronimo
     * @param x Column number
     * @param y Row number
     */
    public void updateArea(ArrayList<ArrayList<Rectangle>> array, Tetronimo tetr, int x, int y){
        boolean[][] area = tetr.getArray();

        for(int i=0; i<array.size(); i++){
            for(int j=0; j<array.get(i).size(); j++){
                if(i<tetr.getSize() && j<tetr.getSize()){
                    if(area[i][j]){
                        array.get(i+x).get(j+y).setFill(convertToColor(tetr.getType(), 1));
                    } else {
                        array.get(i + x).get(j + y).setFill(Color.BLACK);
                    }
                } else{
                    array.get(i + x).get(j + y).setFill(Color.BLACK);
                }
            }
        }
    }

    /**
     * Converts the Tetronimo type to a color
     * @param type Tetronimo type
     * @param opacity Opacity
     * @return Color in RGB
     */
    public Color convertToColor(char type, double opacity) {
        switch (type) {
            case 'I':
                return Color.rgb(0, 255, 255, opacity);
            case 'J':
                return Color.rgb(0, 0, 255, opacity);
            case 'L':
                return Color.rgb(255, 165, 0, opacity);
            case 'O':
                return Color.rgb(255, 255, 0, opacity);
            case 'S':
                return Color.rgb(0, 255, 0, opacity);
            case 'T':
                return Color.rgb(128, 0, 128, opacity);
            case 'Z':
                return Color.rgb(255, 0, 0, opacity);
            default:
                return Color.BLACK;
        }
    }
}
