package gamearea;

public class Coord {
    public int x, y;
    Coord(int width, int size){
        y = 0;
        x = (int)width/2 - 1; //rounds down
        if( size == 4){
            x -= 1;
        }
    }
}
