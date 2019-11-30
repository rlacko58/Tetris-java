package main;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void ActivateScoreBoard(){
        Main.screenController.activate("ScoreBoard");
    }
    public void ActivateGame(){
        Main.screenController.activate("PlayArea");
    }

    public void ExitGame(){
        Platform.exit();
        System.exit(0);
    }
}
