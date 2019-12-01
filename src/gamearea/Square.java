package gamearea;

public class Square {
    public boolean val;
    public char type;
    Square(){
        val = false;
        type = 'N';
    }
    Square(char type){
        if(type == 'N'){
            this.val = false;
        }else {
            this.val = true;
        }
        this.type = type;
    }
    public void set(char type){
        this.type = type;
        this.val = true;
    }
}
