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
    public BorderPane gameBoard;

    private int SIZE = Main.SIZE;

    ArrayList<ArrayList<Rectangle>> myMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing the level...");

        myMap = new ArrayList<ArrayList<Rectangle>>();
        for(int i=0; i<Main.height; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<Main.width; j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                rec.setFill(Color.BLACK);
                GridPane.setColumnIndex(rec, j);
                GridPane.setRowIndex(rec, i);
                GridPane.setHalignment(rec, HPos.CENTER);
                gamePanel.getChildren().addAll(rec);
                line.add(rec);
            }
            myMap.add(line);
        }
    }
}
