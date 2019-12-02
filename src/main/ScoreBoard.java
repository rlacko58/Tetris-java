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

public class ScoreBoard implements Initializable {
    public TableView scoreTable;
    //points name date tbData

    @FXML
    private TableView<ScoreModel> tbData;
    @FXML
    public TableColumn<ScoreModel, Integer> points;
    @FXML
    public TableColumn<ScoreModel, String> name;
    @FXML
    public TableColumn<ScoreModel, String> date;

    private ObservableList<ScoreModel> tableRows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        updateScoreTable();
    }

    public void updateScoreTable(){
        ArrayList<Score> scores = Main.scoreTable.getTopList("Point", 100);
        ArrayList<ScoreModel> rows = new ArrayList<ScoreModel>();
        for(Score s: scores){
            rows.add(new ScoreModel(s.getPoint(), s.getName(), s.getDate()));
        }
        tableRows = FXCollections.observableArrayList(rows);
        tbData.setItems(tableRows);
    }


    public void ActivateMainMenu() throws Exception {
        Main.screenController.activate("MainMenu");
    }
}
