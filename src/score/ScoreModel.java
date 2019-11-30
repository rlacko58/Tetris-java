package score;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreModel {
    private SimpleIntegerProperty points;
    private SimpleStringProperty name;
    private SimpleStringProperty date;

    public ScoreModel(Integer points, String name, Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        this.points = new SimpleIntegerProperty(points);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(dateFormat.format(date));
    }


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points = new SimpleIntegerProperty(points);
    }

    public String getDate() {
        return date.get();
    }

    public void setName(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        this.date = new SimpleStringProperty(dateFormat.format(date));
    }
}
