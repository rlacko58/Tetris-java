package gamearea;

public class Square {
    public boolean val;
    public char type;
    Square(){
        val = false;
        type = 'N';
    }
    public void set(char type){
        this.type = type;
        this.val = true;
    }
}
