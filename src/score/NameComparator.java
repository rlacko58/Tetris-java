package score;

import java.util.Comparator;

/**
 * Comparator by the player's name that achieved the Score
 */
public class NameComparator implements Comparator<Score> {
    /**
     * Compares two Score
     *
     * @param a First Score
     * @param b Second Score
     * @return Less, equal or more the a's value in the abc
     */
    @Override
    public int compare(Score a, Score b) {
        return a.name.compareTo(b.name);
    }
}
