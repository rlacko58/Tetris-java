package score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Stores every Score in a file
 */
public class HighScore {
    /**
     * List of the scores
     */
    ArrayList<Score> toplist;
    /**
     * Filename where it saves and loads from
     */
    String fileName;

    /**
     * Loads the Scores from a given file
     *
     * @param fileName Name of the file to work with
     */
    HighScore(String fileName) {
        toplist = new ArrayList<Score>();
        this.fileName = fileName;
        load();
    }

    /**
     * Default Constructor. Loads the Scores from the "save" file
     */
    public HighScore() {
        toplist = new ArrayList<Score>();
        this.fileName = "save";
        load();
    }

    /**
     * Adds a given Score to the topList
     *
     * @param s Score to add
     */
    public void addScore(Score s) {
        toplist.add(s);
        save();
    }

    /**
     * Returns n Score from the TopList as an ordered ArrayList
     *
     * @param orderBy What to order by ("Name", "Point", "Date")
     * @param count   How many Scores to be returned with
     * @return N Score in an Order
     */
    public ArrayList<Score> getTopList(String orderBy, int count) {
        Comparator<Score> cmp = null;
        switch (orderBy) {
            case "Name":
                cmp = new NameComparator();
                break;
            case "Point":
                cmp = new PointComparator();
                break;
            case "Date":
                cmp = new DateComparator();
                break;
            default:
                return getN(count);
        }
        Collections.sort(toplist, cmp);
        return getN(count);
    }

    /**
     * Returns N Score from the topList array
     *
     * @param count Number to return with
     * @return The new reduced Array
     */
    private ArrayList<Score> getN(int count) {
        ArrayList<Score> newArr = new ArrayList<Score>();
        for (int i = 0; i < toplist.size() && i < count; i++) {
            newArr.add(toplist.get(i));
        }
        return newArr;
    }

    /**
     * Loads the Scores from the given file.
     * If it doesn't exist it does nothing.
     */
    public void load() {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            toplist = (ArrayList<Score>) is.readObject();
        } catch (IOException e) {
            save();
        } catch (ClassNotFoundException e) {
            // do nothing
        }
    }

    /**
     * Saves the Scores to the given file. Creates a new if it doesn't exist.
     * If it can't it does nothing.
     */
    public void save() {
        try {
            File saveFile = new File(fileName);
            saveFile.createNewFile(); // if file already exists will do nothing
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(saveFile, false));
            os.writeObject(toplist);
        } catch (IOException e) {
            // do nothing
        }
    }
}
