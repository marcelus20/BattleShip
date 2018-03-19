package com.jetbrains;

public class Board {


    public final Integer row;
    public final Integer cols;
    public final BoardStates [][] fetchContent;



    public Board(Integer row, Integer cols) {
        this.row = row;
        this.cols = cols;
        this.fetchContent = initFetchContent();
    }

    /**
     * THIS IS A PRIVATE CONSTRUCTOR, THIS WILL BE ACTIVATED WHEN USED THE FUNCTION FETCHCOORDENATE
     * THAT RETURNS A NEW BOARD OBJECT DUE TO THE IMMUTABILITY.
     * @param fetchContent
     */
    private Board(BoardStates[][] fetchContent){
        this.fetchContent = fetchContent;
        this.row = this.fetchContent.length;
        this.cols = this.fetchContent[0].length;
    }

    private BoardStates[][] initFetchContent(){
        final BoardStates[][] tempFetch = new BoardStates[this.row][this.cols];
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.cols; j++){
                tempFetch[i][j] = BoardStates.notRevealed;
            }
        }

        return tempFetch;
    }

    public Board fetchesCoordenate(final Integer row, final Integer cols){
        BoardStates[][] content = new BoardStates[this.row][this.cols];
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.cols; j++){
                if(i == row && j == cols){
                    content[i][j] = BoardStates.revealedButNoShip;
                }else{
                    content[i][j] = BoardStates.notRevealed;
                }
            }
        }
        return new Board(content);
    }

    @Override
    public String toString() {
        String board = "";
        String item = "";

        for(int i = 0; i < this.row; i++){
            board+="|";
            for(int j = 0; j < this.row; j++){
                if(this.fetchContent[i][j] == BoardStates.notRevealed){
                    item = " _ |";
                }else if(this.fetchContent[i][j] == BoardStates.revealedButNoShip){
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
