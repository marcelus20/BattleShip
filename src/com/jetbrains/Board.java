package com.jetbrains;

public class Board {


    public final Integer row;
    public final Integer cols;
    public final Boolean [][] fetchContent;



    public Board(Integer row, Integer cols) {
        this.row = row;
        this.cols = cols;
        this.fetchContent = initFetchContent();
    }

    private Boolean[][] initFetchContent(){
        final Boolean[][] tempFetch = new Boolean[this.row][this.cols];
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.cols; j++){
                tempFetch[i][j] = false;
            }
        }

        return tempFetch;
    }
}
