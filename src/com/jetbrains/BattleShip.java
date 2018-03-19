package com.jetbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class BattleShip {

    public final Board board;
    private final Ship[] ships;
    private final Integer[][] shipsReservedCoordenates;


    public  BattleShip(){
        this.board = new Board(10,10);
        this.ships = new Ship[]{
                new Ship(board.row, board.cols), new Ship(board.row, board.cols), new Ship(board.row, board.cols)
        };
        this.shipsReservedCoordenates = initShipsReservedCoordnates();
        //this.shipsReservedCoordenates =new Integer[][]{{1,3},{1,4},{1,5}};

    }

    public Boolean checkIfHitsShip(final Integer row, final Integer col){
        for(Integer[] coordenates : this.shipsReservedCoordenates){
            if(coordenates[0] == row && coordenates[1]== col){
                return true;
            }
        }
        return false;
    }

    private Integer[][] initShipsReservedCoordnates(){
        Integer[][] arrPositions = new Integer[this.ships.length*this.ships[0].position.length][2];
        Integer row = 0;

        for(Ship ship : this.ships){
            for(Integer[] element : ship.position){
                //System.out.println(element[0] + " "+ element[1]);
                arrPositions[row] = element;
                row++;
            }
        }
        //System.out.println(Arrays.deepToString(arrPositions));
        return arrPositions;

    }






    private BattleShip(Board board, Ship[] ships){
        this.board = board;
        this.ships = ships;
        this.shipsReservedCoordenates = initShipsReservedCoordnates();
    }
}
