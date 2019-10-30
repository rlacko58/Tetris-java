package score;

import java.util.Comparator;

public class NameComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        return a.name.compareTo(b.name);
    }
}
