package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import score.HighScore;
import score.Score;
import score.ScoreModel;

import java.util.ArrayList;

/**
 * Initializes the map and starts the game
 */
public class Main extends Application {
    /**
     * Size of the squares
     */
    public static final int SIZE = 30;
    /**
     * Height of the map
     */
    public static int height = 25;
    /**
     * Width of the map
     */
    public static int width = 18;

    /**
     * Highscores
     */
    public static HighScore scoreTable;

    /**
     * ScreenController for swapping scenes
     */
    public static ScreenController screenController;
    /**
     * Scene for the game
     */
    public static Scene scene;

    /**
     * Calls JavaFX's default launch method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the scoreTable and scenes and starts the application
     * @param primaryStage javafx parameter
     */
    @Override
    public void start(Stage primaryStage) {
        scoreTable = new HighScore();

        scene = new Scene(new Pane(), 1000, 800);

        screenController = new ScreenController(scene);

        screenController.addScreen("PlayArea", "Playarea.fxml");
        screenController.addScreen("MainMenu", "MainMenu.fxml");
        screenController.addScreen("ScoreBoard", "ScoreBoard.fxml");
        screenController.activate("MainMenu");

        primaryStage.setTitle("Tetris Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
