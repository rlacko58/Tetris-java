package gamearea;

/**
 * A square in the game map
 */
public class Square {
    /**
     * Is it a tetronimo?
     */
    public boolean val;
    /**
     * Type of the tetronimo
     */
    public char type;

    /**
     * Initial Square without tetronimo
     */
    Square(){
        val = false;
        type = 'N';
    }

    /**
     * Initializes with a type
     * @param type Type of the tetronimo
     */
    Square(char type){
        if(type == 'N'){
            this.val = false;
        }else {
            this.val = true;
        }
        this.type = type;
    }

    /**
     * sets a new type
     * @param type New type of the square
     */
    public void set(char type){
        this.type = type;
        this.val = true;
    }
}
