package com.jetbrains.components;

import com.jetbrains.BoardStates;
import com.jetbrains.tools.SystemTools;
import com.jetbrains.utils.Coordinate;


import java.util.ArrayList;
import java.util.List;




public class Board {
    /**
     * The board class will have the following attributes:
     * Rows, Columns, board possible states (refers to the enum class BoardStates).
     * The constructor will demand just the number of Rows and Columns as parameters and the rest will
     * be initialised as a default value in the constructor when Board object is created.
     *
     * * THIS CLASS HAS NO GETTER AND SETTERS BECAUSE IT HAS GOT IMMUTABLE AND PUBLIC ATTRIBUTES.
     *
     */

    /**
     * LIST OF ATTRIBUTES:
     */

    private final static Integer MAXSHIPS = 7;
    public final Integer rows;
    public final Integer cols;
    private final BoardStates[][] boardStates;
    private final List<Ship> ships;



    /**
     * CONSTRUCTOR:
     */
    public Board(final Integer rows, final Integer cols){

        this(rows, cols, initBoardStates(rows, cols), initShips());

    }

    private Board(final Integer rows, final Integer cols, final BoardStates[][] boardStates, final List<Ship> ships) {
        this.rows = rows;
        this.cols = cols;
        this.boardStates = boardStates;
        this.ships = ships;
    }



    /**
     * method for initialising boardStates attribute
     * IT WILL BE ASSIGNED ALL NOT_REVEALED.
     * @return
     */
    private static BoardStates[][] initBoardStates(final Integer rows, final Integer cols){
        BoardStates[][] tempBoardStates = new BoardStates[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
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
     * Initialising the instances of Ship in the attribute ships in Board
     *
     */
    private static List<Ship> initShips(){
        return new ArrayList<>();
    }


    /**
     * When Board has no ships whatsoever, this method will initialize the emptyBoard by adding a Ship into it.
     * So the board will start with a ship into it.
     * @param ship
     * @return
     */
    private Board initEmptyBoard(Ship ship){

        return withShip(ship);
    }


    /**
     * This function will carry out the addition of the a ship into an NON EMPTY array which was the step done
     * by the function above declared (initEmptyBOard). It will also make sure that the ship has no the same coordinates
     * as the ships contained in the Array of ships passed as parameter (ships) have.
     * @param ship
     * @return
     */
    private Board shipAdder(Ship ship, List<Ship> ships){

        for(Ship ship_ : ships){//FOR EACH SHIP IN THE ARRAY SHIP
            for(Coordinate coordinate: ship.getCoordenates()){
                if(ship_.getCoordenates().contains(coordinate)){
                    return this;// RETURN THE SAME OBJECT WITHOUT MODIFYING, CAUSE FOUND AT LEAST ONE EXISTING COORDINATE
                }
            }
        }
        /**
         * When code reaches here means that the Ship has entirely new coordinates, therefore it is good to continue (be returned).
         */
        return withShip(ship);

    }


    /**
     * This method will deal with the outside class. It will create a board by calling initEmptyBoard and passing
     * its first instance of ship as a parameter.
     */
    public Board initBoard(){
        Board tempBoard = initEmptyBoard(new Ship(this.rows, this.cols));// instance of ship into initEmptyBoard

        do{
            /**
             * this doWhile loop will keep running until the ships array size matches the MAXSHIPS constant
             */
            Ship ship = new Ship(tempBoard.rows, tempBoard.cols);
            tempBoard = shipAdder(ship, tempBoard.ships);
        }while(tempBoard.ships.size() < SystemTools.genRandInt(MAXSHIPS) + 1);

        return tempBoard;
    }

    /**
     * This method is for updating one coordinate of the board. it will modify the element of the 2D Array
     * in the boardStates attribute and the toString method will respond to this change.
     */
    public void updateBoardStates(final Coordinate coordinate){

        this.boardStates[coordinate.x][coordinate.y] = checkCoordinate(coordinate);
    }


    /**
     * This method will help the update board board to assign BoardStates constants to the coordenate.
     * It will make use of the Boolean isShipCoordenate method to verify if coordenate passed as a parameter
     * belongs to the ships coordenates, if so, the REVEALED_AND_HAS_SHIP will be assigned, otherwise, the REVEALED_
     * BUT_NO_SHiP will be assgned and the board will be updated accordingly.
     * @param coordinate
     * @return
     */
    private BoardStates checkCoordinate(Coordinate coordinate){
        if(isShipCoordinate(coordinate)){
            return BoardStates.REVEALED_AND_HAS_SHIP;
        }else{
            return BoardStates.REVEALED_BUT_NO_SHIP;
        }
    }

    /**
     * Boolean method to check if ships coordinates contains coordinate given as a parameter.
     *
     * @param coordinate
     * @return
     */
    private Boolean isShipCoordinate(Coordinate coordinate){
        for(Ship ship_ : ships){//for each ship in the array of ships
            if(ship_.getCoordenates().contains(coordinate)){
                //Return true if coordinate was found.
                return true;
            }
        }
        //it is false otherwise.
        return false;
    }

    /**
     * GETTERS OF THE CLASS=====================================================================================|
     * @return                                                                                                //|
     */                                                                                                       //|
    public BoardStates[][] getBoardStates() {                                                                 //|
        return boardStates;                                                                                   //|
    }                                                                                                         //|
                                                                                                              //|
    public List<Ship> getShips() {
        return ships;
    }                                                            //|
                                                                                                              //|
                                                                                                              //|
    /**                                                                                                       //|
     * =========================================================================================================|
      * @param rows
     * @return
     */


    /**
     * IMMUTABLE "SETTER" OF THE CLASS
     * For the reading matter, I prefered to write these setters as immutable cause this way they get much closer
     * to less line code typing, more readable (easy to understand).
     *
     * The only one used here is the withShip and withShips setter, but I thought it would be a good idea to keep the
     * others in case I wanted to extend my code.
     * @param rows
     * @return
     */
    public Board withRows(final Integer rows){
        return new Board(rows, cols, boardStates, ships);
    }

    public Board withCols(final Integer cols){
        return new Board(rows, cols, boardStates, ships);
    }

    public Board withBoardStates(final BoardStates[][] boardStates){
        return new Board(rows, cols, boardStates, ships);
    }


    /**
     * The whithShips function is a primitive function that deals directly with the Board object returning by calling
     * the class constructor.
     * @param ships
     * @return
     */
    public Board withShips(final List<Ship> ships){
        return new Board(rows, cols, boardStates, ships);
    }

    /**
     * The withShip (without the s) is the compost function that will add one ship to the array once at time.
     * The proccess of calling this function many times will be done by the shipAdder Method.
     * @param ship
     * @return
     */

    public Board withShip(final Ship ship){
        ships.add(ship);
        return withShips(ships);
    }


    /**
     * Thats the toString method of a Board object.
     * It will simply print the board as if it was a table of rows and columns and the contents in the board
     * will be shown according to the state of the coordinates, if it is either not revealed, revealed with ship or
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



}




