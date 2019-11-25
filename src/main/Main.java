package main;

import gamearea.ArrayListMatrix;
import gamearea.GameArea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.GamePlayArea;

import java.util.ArrayList;

public class Main extends Application {


    public static GameArea gamearea;

    private static Pane squarePane;
    private static Scene scene;




    @Override
    public void start(Stage primaryStage) throws Exception {

        GamePlayArea gpa = new GamePlayArea(10, 20, primaryStage);

        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    private void drawRectangles(ArrayList<Rectangle> rect){
        for(Rectangle rec: rect){
            squarePane.getChildren().addAll(rec);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
