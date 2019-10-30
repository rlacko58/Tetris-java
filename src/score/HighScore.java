package score;

import java.io.*;
import java.util.ArrayList;

public class HighScore {
    ArrayList<Score> toplist;
    String fileName;

    HighScore(String fileName) {
        toplist = new ArrayList<Score>();
        this.fileName = fileName;
        load();
    }

    HighScore() {
        toplist = new ArrayList<Score>();
        this.fileName = "save";
        load();
    }

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

    public void save() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(toplist);
        } catch (IOException e) {
            // do nothing
        }
    }
}
