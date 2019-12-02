package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import score.Score;
import score.ScoreModel;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * ScoreTable scene's JavaFX controller
 */
public class ScoreBoard implements Initializable {
    /**
     * Table data
     */
    @FXML
    private TableView<ScoreModel> tbData;
    /**
     * Points column
     */
    @FXML
    public TableColumn<ScoreModel, Integer> points;
    /**
     * Name column
     */
    @FXML
    public TableColumn<ScoreModel, String> name;
    /**
     * Date column
     */
    @FXML
    public TableColumn<ScoreModel, String> date;

    /**
     * Each row
     */
    private ObservableList<ScoreModel> tableRows;

    /**
     * Initialize the table
     * @param location javafx parameter
     * @param resources javafx parameter
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        updateScoreTable();
    }

    /**
     * Updates the rows in the table
     */
    public void updateScoreTable() {
        ArrayList<Score> scores = Main.scoreTable.getTopList("Point", 100);
        ArrayList<ScoreModel> rows = new ArrayList<ScoreModel>();
        for(Score s: scores){
            rows.add(new ScoreModel(s.getPoint(), s.getName(), s.getDate()));
        }
        tableRows = FXCollections.observableArrayList(rows);
        tbData.setItems(tableRows);
    }

    /**
     * Swaps to the main menu
     */
    public void ActivateMainMenu() {
        Main.screenController.activate("MainMenu");
    }
}
