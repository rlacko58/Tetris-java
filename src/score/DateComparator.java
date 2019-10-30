package score;

import java.util.Comparator;

public class DateComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        return a.date.compareTo(b.date);
    }
}
