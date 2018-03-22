package com.jetbrains.components;



import com.jetbrains.BoardStates;

public class Board {


    public final Integer row;
    public final Integer cols;
    public final BoardStates[][] fetchContent;




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

        //System.out.println(Arrays.deepToString(this.fetchContent));

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


    /**
     *
     * @param row
     * @param cols
     * @return new Board with the fetchContent array modified.
     *
     */
    public Board fetchesCoordenate(final Integer row, final Integer cols, BoardStates state){
        BoardStates[][] content = new BoardStates[this.row][this.cols];
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.cols; j++){
                if(i == row && j == cols){
                    content[i][j] = state;
                }else{
                    content[i][j] = this.fetchContent[i][j];
                }
            }
        }
        //System.out.println(Arrays.deepToString(content));

        return new Board(content);
    }

    @Override
    public String toString() {
        String board = "   | ";
        String item = "";

        for(int i = 0; i < this.cols; i++){
            if(i<=9){
                board += i+ " | ";
            }else{
                board += i+"| ";
            }


        }
        board+="\n";

        for(int i = 0; i < this.row; i++){
            if(i<=9){
                board +=i+"  ";
            }else{
                board +=i+" ";
            }

            board+="|";
            for(int j = 0; j < this.cols; j++){
                if(this.fetchContent[i][j] == BoardStates.notRevealed){
                    item = " _ |";
                }else if(this.fetchContent[i][j] == BoardStates.revealedButNoShip){
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
