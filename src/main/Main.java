package main;

import gamearea.GameArea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import score.HighScore;
import score.Score;

public class Main extends Application {
    public static final int SIZE = 30;
    public static int XMAX;
    public static int YMAX;
    public static int height = 25;
    public static int width = 18;

    public static HighScore scoreTable;


    public static GameArea gamearea;
    public static ScreenController screenController;

    private static Pane squarePane;
    private static Scene scene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(height, width);

        scoreTable.load();
        scoreTable.addScore(new Score("körte", 24));
        scoreTable.addScore(new Score("barack", 40));
        scoreTable.save();

        scene = new Scene(new Pane(), 1000, 800);

        screenController = new ScreenController(scene);

        screenController.addScreen("PlayArea", FXMLLoader.load(getClass().getResource("Playarea.fxml")));
        screenController.addScreen("MainMenu", FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
        screenController.addScreen("ScoreBoard", FXMLLoader.load(getClass().getResource("ScoreBoard.fxml")));
        //screenController.activate("MainMenu");
        screenController.activate("PlayArea");
        //screenController.activate("ScoreBoard");


        primaryStage.setTitle("Tetris Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialize(int height, int width){
        YMAX = height * SIZE + height + 1;
        XMAX = width * SIZE + width + 1;
        scoreTable = new HighScore();
    }
}
