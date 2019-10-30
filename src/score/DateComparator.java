package score;

import java.util.Comparator;

/**
 * Comparator by the Score's date
 */
public class DateComparator implements Comparator<Score> {

    /**
     * Compares two Score
     * @param a First Score
     * @param b Second Score
     * @return Less, equal or more the a's date
     */
    @Override
    public int compare(Score a, Score b) {
        return b.date.compareTo(a.date);
    }
}
