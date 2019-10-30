package score;

import java.io.Serializable;
import java.util.Date;

/**
 * Stores each games date, point and name.
 * It can be saved (serializable).
 */
public class Score implements Serializable {
    /**
     * The date of the game
     */
    Date date;
    /**
     * The point that the player achieved
     */
    int point;
    /**
     * The players name
     */
    String name;

    /**
     * Constructor of the Score. The date is automatically set to the current Time.
     * @param name Player's name
     * @param point Player's point
     */
    public Score(String name, int point) {
        this.name = name;
        this.point = point;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Returns the date of the Score
     * @return Date of the Score
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the Points of the Score
     * @return Points of the Score
     */
    public int getPoint() {
        return point;
    }

    /**
     * Returns the player's name
     * @return  Player's name
     */
    public String getName() {
        return name;
    }
}
