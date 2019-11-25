package view;

import gamearea.GameArea;
import gamearea.Square;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GamePlayArea {
    public static final int SIZE = 25;
    public static int XMAX;
    public static int YMAX;

    private static Pane squarePane;
    private static Scene scene;

    ArrayList<ArrayList<Rectangle>> myMap;

    public GamePlayArea(int height, int width, Stage primaryStage) {
        myMap = new ArrayList<ArrayList<Rectangle>>();
        initialize(height, width);
        for(int i=0; i<height; i++){
            ArrayList<Rectangle> line = new ArrayList<Rectangle>();
            for(int j=0; j<width; j++){
                Rectangle rec = new Rectangle();
                rec.setX(j*SIZE + j + 1);
                rec.setY(i*SIZE + i + 1);
                rec.setWidth(SIZE);
                rec.setHeight(SIZE);
                line.add(rec);
            }
            myMap.add(line);
        }
        for(ArrayList<Rectangle> line: myMap){
            squarePane.getChildren().addAll(line);
        }
        primaryStage.setScene(scene);
    }

    private void initialize(int height, int width){
        YMAX = height * SIZE + height + 1;
        XMAX = width * SIZE + width + 1;
        squarePane = new Pane();
        scene = new Scene(squarePane, XMAX, YMAX);
    }

}
