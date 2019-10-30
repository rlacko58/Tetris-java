package main;

import java.util.LinkedList;

public class Pocket {
    LinkedList<Tetronimo> t;
    Pocket(){
        t = new LinkedList<Tetronimo>();
        t.addLast(generateTetro());
        t.addLast(generateTetro());
        t.addLast(generateTetro());
    }

    public Tetronimo getTetro(){
        t.addLast(generateTetro());
        return t.removeFirst();
    }

    public char[] displayPocket(){
        return new char[]{
                t.get(0).getType(),
                t.get(1).getType(),
                t.get(2).getType()
        };
    }

    private Tetronimo generateTetro(){
        return new Tetronimo(generateShape());
    }

    private char generateShape(){
        int randomNum = (int)(Math.random()*(7))+1;
        switch(randomNum){
            case 1: return 'I';
            case 2: return 'J';
            case 3: return 'L';
            case 4: return 'O';
            case 5: return 'S';
            case 6: return 'T';
            default: return 'Z'; // 7
        }
    }
}
