package score;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Model for JavaFX table. It contains the scores
 */
public class ScoreModel {
    /**
     * Points of the score
     */
    private SimpleIntegerProperty points;
    /**
     * Name of the player
     */
    private SimpleStringProperty name;
    /**
     * Date of the score
     */
    private SimpleStringProperty date;

    /**
     * Constructor with the initial values
     *
     * @param points New Points
     * @param name   Name of the player
     * @param date   Date of the score
     */
    public ScoreModel(Integer points, String name, Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        this.points = new SimpleIntegerProperty(points);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(dateFormat.format(date));
    }


    /**
     * Getter for name
     *
     * @return name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Setter for name
     *
     * @param name New name
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * Setter for date. Converts to String
     *
     * @param date New dates
     */
    public void setName(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        this.date = new SimpleStringProperty(dateFormat.format(date));
    }

    /**
     * Getter for points
     *
     * @return points
     */
    public int getPoints() {
        return points.get();
    }

    /**
     * Setter for points
     *
     * @param points New points
     */
    public void setPoints(int points) {
        this.points = new SimpleIntegerProperty(points);
    }

    /**
     * Getter for date
     *
     * @return Date in string
     */
    public String getDate() {
        return date.get();
    }
}
