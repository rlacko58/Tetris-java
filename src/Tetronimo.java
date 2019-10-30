import java.util.Arrays;

public class Tetronimo {
    private boolean[][] t;
    private char type;

    Tetronimo(char which) {
        setTetro(which);
    }

    public boolean[][] getArray() {
        return t;
    }

    public int getSize() {
        return t.length;
    }

    public char getType() {
        return type;
    }

    public void RotateLeft() {
        t = rotate(t, false);
    }

    public void RotateRight() {
        t = rotate(t, true);
    }

    public boolean[][] getRotateLeft() {
        return rotate(t, false);
    }

    public boolean[][] getRotateRight() {
        return rotate(t, true);
    }

    private boolean[][] copyMatrix(boolean[][] arr) {
        return Arrays.stream(arr).map(boolean[]::clone).toArray(boolean[][]::new);
    }

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
