import java.util.Arrays;

/**
 * Represents a Tetronimo in the game area
 * Many Tetronimo can be in the game area
 */

public class Tetronimo {
    /**
     * The matrix that stores the shape of a Tetronimo
     * This will placed into the game area, can be rotated
     */
    private boolean[][] t;
    /**
     * Defines the type of the Tetronimo
     * We need it for its color
     */
    private char type;

    /**
     * Creates the new Tetronimo from the given shape type
     * @param which Tetronimo shape (I, J, L, O, S, T, Z)
     */
    Tetronimo(char which) {
        setTetro(which);
    }

    /**
     *
     * @return Shape in a boolean matrix
     */
    public boolean[][] getArray() {
        return t;
    }

    /**
     *
     * @return Size of the matrix ( size x size )
     */
    public int getSize() {
        return t.length;
    }

    /**
     *
     * @return Type of the Tetronimo
     */
    public char getType() {
        return type;
    }

    /**
     * Rotates the shape Counterclockwise
     */
    public void RotateLeft() {
        t = rotate(t, false);
    }

    /**
     * Rotates the shape Clockwise
     */
    public void RotateRight() {
        t = rotate(t, true);
    }

    /**
     * Return the Counterclockwise rotated shape
     * Doesn't change the Tetronimo
     * @return Counterclockwise rotated shape
     */
    public boolean[][] getRotateLeft() {
        return rotate(t, false);
    }

    /**
     * Return the Clockwise rotated shape
     * Doesn't change the Tetronimo
     * @return Clockwise rotated shape
     */
    public boolean[][] getRotateRight() {
        return rotate(t, true);
    }

    /**
     * Makes a copy of a given matrix
     * @param arr Matrix to be copied
     * @return New copied matrix
     */
    private boolean[][] copyMatrix(boolean[][] arr) {
        return Arrays.stream(arr).map(boolean[]::clone).toArray(boolean[][]::new);
    }

    /**
     * Rotates the given matrix and returns it
     * If the direction is TRUE, then rotates clockwise, otherwise Counterclockwise
     * @param arr Matrix to be rotated
     * @param direction Direction of the rotation (clockwise / counterclockwise)
     * @return Rotated matrix
     */
    private boolean[][] rotate(boolean[][] arr, boolean direction) {
        boolean[][] tempArr = copyMatrix(arr);
        // direction == true : RIGHT
        // direction == false : LEFT
        if (direction) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    tempArr[j][arr.length - 1 - i] = arr[i][j];
                }
            }
            return tempArr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    tempArr[j][i] = arr[i][arr.length - 1 - j];
                }
            }
            return tempArr;
        }
    }

    /**
     * Sets the Tetronimo's shape and type
     * It has every shapes matrix in a switch statement
     * @param which Type of the tetronimo to be set
     */
    private void setTetro(char which) {
        type=which;
        switch (which) {
            case 'I':
                t = new boolean[][]{
                        {false, false, false, false},
                        {false, false, false, false},
                        {false, false, false, false},
                        {true, true, true, true}
                };
                break;
            case 'J':
                t = new boolean[][]{
                        {false, false, false},
                        {true, false, false},
                        {true, true, true}
                };
                break;
            case 'L':
                t = new boolean[][]{
                        {false, false, false},
                        {false, false, true},
                        {true, true, true}
                };
                break;
            case 'O':
                t = new boolean[][]{
                        {true, true},
                        {true, true}
                };
                break;
            case 'S':
                t = new boolean[][]{
                        {false, false, false},
                        {false, true, true},
                        {true, true, false}
                };
                break;
            case 'T':
                t = new boolean[][]{
                        {false, false, false},
                        {false, true, false},
                        {true, true, true}
                };
                break;
            case 'Z':
                t = new boolean[][]{
                        {false, false, false},
                        {true, true, false},
                        {false, true, true}
                };
                break;
            default:
                //do nothing
                break;

        }
    }
}
