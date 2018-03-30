package com.jetbrains;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Board {
    /**
     * The board class will have the following attributes:
     * Rows, Columns, board possible states (refers to the enum class BoardStates).
     * They will be all immutable as well, just like players, so no need to keep them private
     *
     * The constructor will demand just the number of Rows and Columns as parameters and the rest will
     * be initialised as a default value in the constructor when Board object is created.
     */

    /**
     * LIST OF ATTRIBUTES:
     */

    public final Integer rows;
    public final Integer cols;
    public final BoardStates[][] boardStates;
    public final Ship[] ships;



    /**
     * CONSTRUCTOR:
     */
    public Board(final Integer rows, final Integer cols){
        this.rows = rows;
        this.cols = cols;
        this.boardStates = initBoardStates();
        this.ships =  initShips();
    }

    private Board(final Integer rows, final Integer cols, final BoardStates[][] boardState, final Ship[] ships){
        this.rows = rows;
        this.cols = cols;
        this.boardStates = boardState;
        this.ships = ships;
    }


    /**
     * method for initialising boardStates attribute
     * IT WILL BE ASSIGNED ALL NOT_REVEALED.
     * @return
     */
    private BoardStates[][] initBoardStates(){
        BoardStates[][] tempBoardStates = new BoardStates[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                /**
                 * it will be assigned all values of not revealed
                 */
                tempBoardStates[i][j] = BoardStates.NOT_REVEALED;

            }
        }
        ;
        return tempBoardStates;
    }

    /**
     * Initialising the instances of Ship in the attribute ships
     *
     */
    private Ship[] initShips(){
        Ship[] tempShip = new Ship[new Random().nextInt(4)+1];// THE BOARD CAN HAVE A MAXIMUN OF 5 SHIPS

        String existingCoordinates = "";

        // LOOP FOR CREATING INSTANCES IN THE SHIP ARRAY

        for(int i = 0; i < tempShip.length; i++){
            //boolean valid = false;

            tempShip[i] = new Ship(this.rows, this.cols);
            existingCoordinates += tempShip[i];

            while(existingCoordinates.contains(Arrays.deepToString(tempShip))){
                tempShip[i] = new Ship(this.rows, this.cols);

            }


        }
        System.out.println(existingCoordinates);

        return tempShip;
    }


    /**
     * This method is for updating one coordinate of the board. it will modify the element of the 2D Array
     * in the boardStates attribute and the toString method will respond to this change.
     */
    public void updateBoard(final Integer[] coordinate){

        this.boardStates[Integer.parseInt(coordinate[0].toString())]
                        [Integer.parseInt(coordinate[1].toString())] = checkCoordinate(coordinate);


    }


    /**
     * Thats the toString method of a Board object.
     * It will simply print the board as if it was a table of rows and columns and the contents in the board
     * will be shown according to the state of the coordenates, if it is either not revealeed, revealed with ship or
     * reveealed without ship.
     * @return
     */
    @Override
    public String toString() {
        String board = "   | ";
        String item = "";

        for(int i = 0; i < this.cols; i++){
            /**
             * this if statement is just for keeping the pipe align with the numbers with less digits.
             * just for esthetics porpuses.
             */
            if(i<=9){
                board += i+ " | ";
            }else{
                board += i+"| ";
            }


        }
        board+="\n";


        for(int i = 0; i < this.rows; i++){
            /**
             * this if statement is just for keeping the spaces align with the numbers with less digits.
             * if number is less than 9, add two spaces to align, if more, add just one space.
             * just for esthetics porpuses as the above if statement.
             */
            if(i<=9){
                board +=i+"  ";
            }else{
                board +=i+" ";
            }

            board+="|";

            /**
             * THIS LOOPING IS FOR FETCHING THE CONTENT OF THE BOARD BASED ON THE BOARD STATES
             */
            for(int j = 0; j < this.cols; j++){
                if(this.boardStates[i][j] == BoardStates.NOT_REVEALED){
                    item = " _ |";
                }else if(this.boardStates[i][j] == BoardStates.REVEALED_BUT_NO_SHIP){
                    item = " * |";
                }else{
                    item = " H |";
                }
                board +=item;
            }
            board += "\n";
        }

        return board;
    }


    /**
     * This method will help the update board board to assign BoardStates constants to the coordenate.
     * It will make use of the Boolean isShipCoordenate method to verify if coordenate passed as a parameter
     * belongs to the ships coordenates, if so, the REVEALED_AND_HAS_SHIP will be assigned, otherwise, the REVEALED_
     * BUT_NO_SHiP will be assgned and the board will be updated accordingly.
     * @param coordinate
     * @return
     */
    private BoardStates checkCoordinate(Integer[] coordinate){
        if(isShipCoordinate(coordinate)){
            return BoardStates.REVEALED_AND_HAS_SHIP;
        }else{
            return BoardStates.REVEALED_BUT_NO_SHIP;
        }
    }

    /**
     * Boolean method to check if ships coordinates contains cordenate given as a parameters.
     * To keep things simple, it was used ToString of both arrays and checking if one string contains other.
     * @param coordinate
     * @return
     */


    private Boolean isShipCoordinate(Integer[] coordinate){
        String cordinateStr = Arrays.deepToString(coordinate);
        String shipsCoordenates = Arrays.deepToString(this.ships);
        if(shipsCoordenates.contains(cordinateStr)){
            return true;
        }else{
            return false;
        }
    }
}




