package score;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    Date date;
    int point;
    String name;

    Score(String name, int point) {
        this.name = name;
        this.point = point;
        this.date = new Date(System.currentTimeMillis());
    }

    public Date getDate() {
        return date;
    }

    public int getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }
}
