package gamearea;

import java.util.LinkedList;

/**
 * Stores the next Tetronimoes.
 */
public class Next {
    /**
     * List which stores the Tetronimoes.
     */
    LinkedList<Tetronimo> t;

    /**
     * Initializes the Pocket with 3 Tetronimo.
     */
    Next() {
        t = new LinkedList<Tetronimo>();
        t.addLast(generateTetro());
        t.addLast(generateTetro());
        t.addLast(generateTetro());
    }

    /**
     * Returns the next Tetronimo and deletes it.
     * Generates a new and ads it.
     *
     * @return Next Tetronimo
     */
    public Tetronimo getTetro() {
        t.addLast(generateTetro());
        return t.removeFirst();
    }

    /**
     * Returns the next 3 Tetronimoes type (I, J, L, ...)
     *
     * @return Next 3 Tetronimoes type
     */
    public char[] displayPocket() {
        return new char[]{
                t.get(0).getType(),
                t.get(1).getType(),
                t.get(2).getType()
        };
    }

    /**
     * Returns a newly generated Tetronimo.
     *
     * @return Generated Tetronimo
     */
    private Tetronimo generateTetro() {
        return new Tetronimo(generateShape());
    }

    /**
     * Generates a new Tetronimo type (I, J, L, ...)
     *
     * @return new Tetronimo type
     */
    private char generateShape() {
        int randomNum = (int) (Math.random() * (7)) + 1;
        switch (randomNum) {
            case 1:
                return 'I';
            case 2:
                return 'J';
            case 3:
                return 'L';
            case 4:
                return 'O';
            case 5:
                return 'S';
            case 6:
                return 'T';
            default:
                return 'Z'; // 7
        }
    }
}
