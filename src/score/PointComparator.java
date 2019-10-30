package score;

import java.util.Comparator;

/**
 * Comparator by the Score's points
 */
public class PointComparator implements Comparator<Score> {
    /**
     * Compares two Score
     * @param a First Score
     * @param b Second Score
     * @return Less, equal or more the a's value
     */
    @Override
    public int compare(Score a, Score b) {
        return Integer.compare(a.point, b.point);
    }
}
