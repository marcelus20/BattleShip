package com.jetbrains;

public class Board {
    /**
     * The board class will have the following attributes:
     * Rows, Columns, A set of Ships and Ship reserved Coordenates.
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


    /**
     * CONSTRUCTOR:
     */
    public Board(final Integer rows, final Integer cols){
        this.rows = rows;
        this.cols = cols;
    }



}
