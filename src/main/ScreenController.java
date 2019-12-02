package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

/**
 * Swaps the scenes
 */
public class ScreenController {
    /**
     * HashMap that contains the scenes paths
     */
    private HashMap<String, String> screenMap = new HashMap<>();
    /**
     * The main scene
     */
    private Scene main;

    /**
     * Sets the scene to the given
     * @param main New scene to set
     */
    public ScreenController(Scene main) {
        this.main = main;
    }

    /**
     * Adds a new FXML path
     * @param name FXML path short name
     * @param pane path
     */
    protected void addScreen(String name, String pane){
        screenMap.put(name, pane);
    }

    /**
     * Removes an FXML path by it's name
     * @param name Name of scene
     */
    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * Swaps the scene. In case of IOException, throws an error.
     * @param name Name of the FXML path
     */
    protected void activate(String name) {
        try {
            main.setRoot(FXMLLoader.load(getClass().getResource(screenMap.get(name))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}