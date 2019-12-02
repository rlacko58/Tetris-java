package gamearea;

/**
 * A coordinate which can calculate the starting coordinate
 */
public class Coord {
    /**
     * x and y coordinate
     */
    public int x, y;

    /**
     * Constructor which creates the default coordinates of the Tetronimo in the map
     * @param width Width of the map
     * @param size Size of the Tetronimo
     */
    Coord(int width, int size){
        y = 0;
        x = (int)width/2 - 1; //rounds down
        if( size == 4){
            x -= 1;
        }
    }

    /**
     * Constructor which sets the coordinates to the given x and y
     * @param x Column number
     * @param y Row number
     * @param a placeholder
     */
    Coord(int x, int y, boolean a){
        this.x = x;
        this.y = y;
    }
}
