package com.jetbrains.components;

import com.jetbrains.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.jetbrains.tools.SystemTools.*;
import static com.jetbrains.utils.Coordinate.*;


public class Ship {

    /**
     * THIS IS THE SHIP CLASS.
     *
     * This class is making use of the Tuple class to simulate a coordinate (x, y).
     *
     *
     * Based on the dimensions of the board object, the ship will be created.
     * The length of the ship will be based on the width of the board, therefore, the number of columns
     * that the board object has.
     *
     * The attributes will be the length of the ship, whether the ship is vertical or horizontal and the ship coordinates.
     */

    /**
     * LIST OF THE ATTRIBUTES
     */

    private  Integer length;
    private  List<Coordinate> coordinates;
    private  Boolean isVertical;

    /**
     * CONSTRUCTOR
     */
    public Ship(final Integer boardRows, final Integer boardCols){

        this.length = Math.round(boardCols/3);
        this.isVertical = new Random().nextBoolean();
        this.coordinates = initCoord(boardRows, boardCols);
    }



    /**
     * This function creates the first ship coordinate. Thats an initCoord function helper.
     *
     * @param isVertical
     * @param length
     * @param boardRows
     * @param boardCols
     * @return
     */
    private Coordinate createFirstCoordinate(
            final Boolean isVertical, final Integer length,
            final Integer boardRows, Integer boardCols
    ){
        Integer x;
       Integer y;
        if(isVertical){


            x = genRandInt(boardRows-length);
            y = genRandInt(boardCols);

            if(x > boardRows - length){
                x = boardRows - length;
            }

        }else{
            x = genRandInt(boardRows);
            y = genRandInt(boardCols-length);
            if(y > boardCols - length){
                y = boardCols - length;
            }
        }
        return coordinate(x, y);
    }


    /**
     * This method is responsible to keep creating the next coordinates based on the first coordinate created
     * at the createFirstCoordinate helper function. It will fill the next gap of the array with a help of a index
     * that will be passed as a parameter.
     * @param isVertical
     * @param startingPoint
     * @param index
     * @return
     */
    private Coordinate createNextCoordinate(
            final Boolean isVertical, Coordinate startingPoint, Integer index
    ){

        /**
         * declaring the coordinate components
         */
        Integer x, y;


        if(isVertical) {

            /**
             * INDEX ADDS UP TO THE X AXIS VALUES
             */

            x = startingPoint.x + index; // ASSIGNING X TO THE LEFT SIDE OF THE TUPLE
            y = startingPoint.y; // ASSIGNING Y TO THE LEFT SIDE OF THE TUPLE

        }else{

            /**
             * INDEX ADDS UP TO THE Y AXIS VALUES
             */
            x = startingPoint.x; // ASSIGNING X TO THE LEFT SIDE OF THE TUPLE
            y = startingPoint.y + index;  // ASSIGNING Y TO THE LEFT SIDE OF THE TUPLE
        }
        return coordinate(x, y);
    }


    /**
     * This method will initialise the Ship coordinates.
     * If the ship is vertical, it means that the y Axis of the coordinate won't change, just the x Axis will increment 1
     * On the other hand, if the ship is vertical, the x Axis won't change, just the y Axis will increment 1.
     *
     * An example of Vertical Ship with length 3:
     *      [(5,4)
     *       (6,4)
     *       (7,4)] : y Axis does not change
     * An Example of a Horizontal Ship with length 5:
     *      [(5,4)
     *       (5,5)
     *       (5,6)
     *       (5,7)
     *       (5,8)] : x Axis do not change.
     *
     * @param boardRows
     * @param boardCols
     * @return tempPosition (the Coordinates of the Ship)
     */
    private List<Coordinate> initCoord(final Integer boardRows, Integer boardCols){
        final List<Coordinate> tempPosition = new ArrayList<>();


        tempPosition.add(createFirstCoordinate(isVertical, length, boardRows, boardCols));

        for(int i = 1; i < length; i++){
            tempPosition.add(createNextCoordinate(isVertical, tempPosition.get(0), i));

        }
        return tempPosition;
   }

    /**
     * getters
     * @return
     */

    public Integer getLength() {
        return length;
    }

    public List<Coordinate> getCoordenates() {
        return coordinates;
    }

    public Boolean getVertical() {
        return isVertical;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void setVertical(Boolean vertical) {
        isVertical = vertical;
    }


    /**
     * Given the following object, this method will compare whether the outer (passed as a parameter) object
     * and the the inner object is the same.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (!length.equals(ship.length)) return false;
        if (!coordinates.equals(ship.coordinates)) return false;
        return isVertical.equals(ship.isVertical);
    }

    /**
     * This method creates a UNIQUE value for the actual instance created from this class.
     * This will help with the creating of the array of Ships in the Board class making sure they will not be
     * spawned over each other
     * @return
     */
    @Override
    public int hashCode() {
        int result = length.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + isVertical.hashCode();
        return result;
    }

    /**
     * The toString method of the Ship is just for printing  its coordinates.
     * @return
     */
    @Override
    public String toString() {
        //return Arrays.deepToString(this.coordinates.toArray());
        return "Ship(" + coordinates + ")";
    }
}
