package main;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainMenu scene's JavaFX controller
 */
public class MainMenu implements Initializable {
    /**
     * It does nothing
     *
     * @param location  javafx parameter
     * @param resources javafx parameter
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // do nothing
    }

    /**
     * Swaps to the ScoreBoard scene
     */
    public void ActivateScoreBoard() {
        Main.screenController.activate("ScoreBoard");
    }

    /**
     * Swaps to the PlayArea scene
     */
    public void ActivateGame() {
        Main.screenController.activate("PlayArea");
    }

    /**
     * Exits the game
     */
    public void ExitGame() {
        Platform.exit();
        System.exit(0);
    }
}
