package score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScore {
    ArrayList<Score> toplist;
    String fileName;

    HighScore(String fileName) {
        toplist = new ArrayList<Score>();
        this.fileName = fileName;
        load();
    }

    public HighScore() {
        toplist = new ArrayList<Score>();
        this.fileName = "save";
        load();
    }

    public void addScore(Score s){
        toplist.add(s);
    }

    public ArrayList<Score> getTopList(String orderBy, int count){
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

    private ArrayList<Score> getN(int count){
        ArrayList<Score> newArr = new ArrayList<Score>();
        for(int i=0; i<toplist.size() && i<count; i++){
            newArr.add(toplist.get(i));
        }
        return newArr;
    }

    public void load() {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("files/" + fileName));
            toplist = (ArrayList<Score>) is.readObject();
        } catch (IOException e) {
            save();
        } catch (ClassNotFoundException e) {
            // do nothing
        }
    }

    public void save() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/" + fileName));
            os.writeObject(toplist);
        } catch (IOException e) {
            // do nothing
        }
    }
}
