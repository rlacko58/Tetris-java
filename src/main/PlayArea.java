package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayArea implements Initializable {
    public GridPane gamePanel;
    public GridPane pocket;
    public GridPane next1;
    public GridPane next2;
    public BorderPane gameBoard;

    private int SIZE = Main.SIZE;

    ArrayList<ArrayList<Rectangle>> myMap;
    ArrayList<ArrayList<Rectangle>> myPocket;
    ArrayList<ArrayList<Rectangle>> myNext1;
    ArrayList<ArrayList<Rectangle>> myNext2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing the level...");

        myMap = new ArrayList<ArrayList<Rectangle>>();
        myPocket = new ArrayList<ArrayList<Rectangle>>();
        myNext1 = new ArrayList<ArrayList<Rectangle>>();
        myNext2 = new ArrayList<ArrayList<Rectangle>>();

        for(int i=0; i<Main.height; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<Main.width; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                rec.setStroke(Color.GRAY);
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
                rec.setStroke(Color.GRAY);
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
                rec.setStroke(Color.GRAY);
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
                rec.setStroke(Color.GRAY);
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);

                next2.getChildren().addAll(rec);
                line.add(rec);
            }
            myNext2.add(line);
        }
    }
}
