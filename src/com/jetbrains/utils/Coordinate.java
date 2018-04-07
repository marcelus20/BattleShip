package com.jetbrains.utils;

public class Coordinate {


    /**
     * This class is going to simulate a coordinate that it will be used replace the 2D Integer array.
     * This class has two attributes, the x and the y.
     * The x will be at the place of the x Axis of the coordinate and the y will represent the Y axis.
     *
     * This class is built to fit the need of using a tuple. Because of the fact that java is not a functional language
     * there is no thing such a tuple. Therefore this class proposes a solution for this java lack of tuples.
     */

    public final Integer x;
    public final Integer y;


    /**
     * this constructor is private case the static method bellow it will be instatiating it.
     * @param x
     * @param y
     */
    private Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }


    /**
     * This calling of the constructor will be carried out by an static method. The return is the private constructor.
     * @param x
     * @param y
     * @return
     */
    public static Coordinate coordinate(Integer x, Integer y){
        return new Coordinate(x, y);
    }

    /**
     * As stated in Ships:
     * Given the following object, this method will compare whether the outer (passed as a parameter) object
     * and the the inner object is the same. this will preventing of creating two coordinates equals by comparing,
     * returning a boolean value.
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate tuple = (Coordinate) o;

        if (!x.equals(tuple.x)) return false;
        return y.equals(tuple.y);
    }

    /**
     * As stated in Ships
     * This method creates a UNIQUE value for the actual instance created from this class.
     * This will help with the creating of the array of Ships in the Board class making sure they will not be
     * spawned over each other
     * @return
     */
    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    /**
     * the toString method will return the entire coordinate format (x, y)
     * @return
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
