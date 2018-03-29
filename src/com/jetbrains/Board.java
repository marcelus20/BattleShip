package com.jetbrains;

import sun.plugin2.message.TextEventMessage;

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



    /**
     * CONSTRUCTOR:
     */
    public Board(final Integer rows, final Integer cols){
        this.rows = rows;
        this.cols = cols;
        this.boardStates = initBoardStates();
    }

    private Board(final Integer rows, final Integer cols, final BoardStates[][] boardState){
        this.rows = rows;
        this.cols = cols;
        this.boardStates = boardState;
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
        return tempBoardStates;
    }


    /**
     * This method is for updating one coordenate of the board. it will modify the element of the 2D Array
     * in the boardStates attribute and the toString method will respond to this change.
     */
    public Board updateBoard(final Integer[] coordenate){

        this.boardStates[Integer.parseInt(coordenate[0].toString())]
                        [Integer.parseInt(coordenate[1].toString())] = BoardStates.REVEALED_BUT_NO_SHIP;


        return new Board(this.rows, this.cols, this.boardStates);
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
                    item = " X |";
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




