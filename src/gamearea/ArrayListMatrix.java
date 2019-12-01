package gamearea;

import java.util.ArrayList;

public class ArrayListMatrix extends ArrayList<ArrayList<Square>> {
    ArrayListMatrix(int height, int width){
        for(int i=0; i<height; i++){
            ArrayList<Square> nLine = new ArrayList<Square>();
            for(int j=0; j<width; j++){
                nLine.add(new Square());
            }
            this.add(nLine);
        }
    }
    ArrayListMatrix(ArrayListMatrix alm){
        for(int i=0; i<alm.getHeight(); i++){
            ArrayList<Square> nLine = new ArrayList<Square>();
            for(int j=0; j<alm.getWidth(); j++){
                nLine.add(new Square(alm.getSquare(i, j).type));
            }
            this.add(nLine);
        }
    }

    private ArrayList<Square> createLine(){
        ArrayList<Square> nLine = new ArrayList<Square>();
        for(int i=0; i<this.get(0).size(); i++){
            nLine.add(new Square());
        }
        return nLine;
    }

    public void delLine(int lNum){
        this.remove(lNum);
        this.add(0, createLine());
    }

    public void setSquare(int y, int x, char type){
        this.get(y).get(x).set(type);
    }
    public Square getSquare(int y, int x){
        return this.get(y).get(x);
    }

    public int getWidth(){
        return this.get(0).size();
    }

    public int getHeight(){
        return this.size();
    }
}
