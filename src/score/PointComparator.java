package score;

import java.util.Comparator;

public class PointComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        return Integer.compare(a.point, b.point);
    }
}
