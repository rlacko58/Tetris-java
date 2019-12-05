package gamearea;

import java.util.ArrayList;


/**
 * Extends the default Arraylist<Arraylist<...>> with the appropriate functions to make a Matrix
 */
public class ArrayListMatrix extends ArrayList<ArrayList<Square>> {

    /**
     * Creates the matrix with the given parameters
     *
     * @param height Height of the matrix
     * @param width  Width of the matrix
     */
    ArrayListMatrix(int height, int width) {
        for (int i = 0; i < height; i++) {
            ArrayList<Square> nLine = new ArrayList<Square>();
            for (int j = 0; j < width; j++) {
                nLine.add(new Square());
            }
            this.add(nLine);
        }
    }


    /**
     * Copy constructor
     *
     * @param alm ArrayListMatrix to be copied
     */
    ArrayListMatrix(ArrayListMatrix alm) {
        for (int i = 0; i < alm.getHeight(); i++) {
            ArrayList<Square> nLine = new ArrayList<Square>();
            for (int j = 0; j < alm.getWidth(); j++) {
                nLine.add(new Square(alm.getSquare(i, j).type));
            }
            this.add(nLine);
        }
    }


    /**
     * Creates a row of Square for the matrix
     *
     * @return The new line
     */
    private ArrayList<Square> createLine() {
        ArrayList<Square> nLine = new ArrayList<Square>();
        for (int i = 0; i < this.get(0).size(); i++) {
            nLine.add(new Square());
        }
        return nLine;
    }

    /**
     * deletes the line and adds a new to the top
     *
     * @param lNum Line to be deleted
     */
    public void delLine(int lNum) {
        this.remove(lNum);
        this.add(0, createLine());
    }

    /**
     * Sets a square with the given type and coordinates
     *
     * @param y    Row of the matrix
     * @param x    Column of the matrix
     * @param type the type of Square to make there
     */
    public void setSquare(int y, int x, char type) {
        this.get(y).get(x).set(type);
    }

    /**
     * Returns the square from the given coordinate
     *
     * @param y Row of the matrix
     * @param x Column of the matrix
     * @return Square
     */
    public Square getSquare(int y, int x) {
        return this.get(y).get(x);
    }

    /**
     * Returns the width of the matrix
     *
     * @return Width of the matrix
     */
    public int getWidth() {
        return this.get(0).size();
    }

    /**
     * Returns the height of the matrix
     *
     * @return Height of the matrix
     */
    public int getHeight() {
        return this.size();
    }
}
