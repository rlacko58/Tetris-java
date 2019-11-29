package main;

import gamearea.GameArea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int SIZE = 30;
    public static int XMAX;
    public static int YMAX;
    public static int height = 10;
    public static int width = 20;


    public static GameArea gamearea;

    private static Pane squarePane;
    private static Scene scene;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(height, width);

        Parent root = FXMLLoader.load(getClass().getResource("playarea.fxml"));

        primaryStage.setTitle("asd");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void initialize(int height, int width){
        YMAX = height * SIZE + height + 1;
        XMAX = width * SIZE + width + 1;
    }
}
