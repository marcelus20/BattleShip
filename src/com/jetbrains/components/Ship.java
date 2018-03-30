package com.jetbrains.components;

import java.util.Arrays;
import java.util.Random;

public class Ship {

    /**
     * THIS IS THE SHIP CLASS.
     * Based on the dimensions of the board object, the ship will be created.
     * The length of the ship will be based on the width of the board, therefore, the number of columns
     * that the board object has.
     *
     * The attributes will be the length of the ship, whether the ship is vertical or horizontal and the ship coordenates.
     */

    /**
     * LIST OF ATTRIBUTE OF A SHIP OBJECT - ALL IMMUTABLE, NO NEED TO KEEP PRIVATE
     */
    public final Integer length;
    public final Integer[][] coordenates;
    public final Boolean isVertical;

    /**
     * CONSTRUCTOR
     */
    public Ship(final Integer boardRows, final Integer boardCols ){
        this.length = Math.round(boardCols/3);
        this.isVertical = new Random().nextBoolean();
        this.coordenates = initCord(boardRows, boardCols );
    }

    /**
     * This method will initialise the Ship coordenates.
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
    private Integer[][] initCord(final Integer boardRows, final Integer boardCols ){
        final Integer[][] tempPosition = new Integer[this.length][2];


        for(int i = 0; i < tempPosition.length; i++){
            if(i != 0){
                if(this.isVertical) {
                    tempPosition[i][0] = tempPosition[0][0] + i;
                    tempPosition[i][1] = tempPosition[0][1];
                }else{
                    tempPosition[i][0] = tempPosition[0][0];
                    tempPosition[i][1] = tempPosition[0][1]+i;
                }
            }else{
                //System.out.println(this.isVertical);
                if(this.isVertical){
                    tempPosition[0][0] = new Random().nextInt(boardRows-this.length);
                    tempPosition[0][1] = new Random().nextInt(boardCols);
                }else{
                    tempPosition[0][0] = new Random().nextInt(boardRows);
                    tempPosition[0][1] = new Random().nextInt(boardCols-this.length);
                }


            }


        }
        return tempPosition;
    }


    /**
     * The toString method of the Ship is just for printing  its coordenates.
     * @return
     */
    @Override
    public String toString() {
        return Arrays.deepToString(this.coordenates);
    }
}
