package main;

import gamearea.ArrayListMatrix;
import gamearea.Coord;
import gamearea.GameArea;
import gamearea.Tetronimo;
import javafx.application.Application;
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

public class PlayArea implements Initializable {
    public GameArea gameArea;

    public GridPane gamePanel;
    public GridPane pocket;
    public GridPane next1;
    public GridPane next2;
    public BorderPane gameBoard;

    public Text size;
    public Text time;
    public Text points;
    public Text level;
    public Text lines;


    private int SIZE = Main.SIZE;

    ArrayList<ArrayList<Rectangle>> myMap;
    ArrayList<ArrayList<Rectangle>> myPocket;
    ArrayList<ArrayList<Rectangle>> myNext1;
    ArrayList<ArrayList<Rectangle>> myNext2;

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
    }

    public void updateNexts(){
        Tetronimo[] nexts = gameArea.getNexts();
        updateArea(myNext1, nexts[0], 0, 0);
        updateArea(myNext2, nexts[1], 0, 0);
    }

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

    private void endGame() throws Exception {
        Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // disable
            }
        });
        Main.scoreTable.addScore(new Score("anonymous", gameArea.getPoints()));
        Main.screenController.activate("ScoreBoard");
    }

    public void updatePocket(){
        updateArea(myPocket, gameArea.getPocket(), 0, 0);
    }


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
