package com.jetbrains;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BattleShip {

    public Board board;
    private final Ship[] ships;
    private final Integer[][] shipsReservedCoordenates;
    private final Player[] players;
    private Boolean isFinished;


    public  BattleShip(){
        this.board = new Board(10,10);
        this.ships = new Ship[]{
                new Ship(board.row, board.cols), new Ship(board.row, board.cols), new Ship(board.row, board.cols)
        };
        this.shipsReservedCoordenates = initShipsReservedCoordnates();


        this.players = new Player[]{
                new Player("Felipe",
                        25,
                        "marcelus20felipe@gmail.com"),
                new Player("Sara",
                        26,
                        "sarasimionirock@gmail.com")

        };
        isFinished = false;

        System.out.println(this.board);
        while(!this.isFinished){


            for(Player currentPlayer : this.players){

                System.out.println(this.board);
                Integer row;
                Integer col;
                do{
                    row = new Random().nextInt(10);
                    col = new Random().nextInt(10);
                }while(checkIfCordenateIsRevealed(row, col));


                Integer[] coordenate = currentPlayer.choosesCoordenate(row, col);
                if(checkIfHitsShip(coordenate)){
                    System.out.println("Wow, how amazing, that was a Hit!");

                    currentPlayer.incrementHitCounter();
                }else{
                    System.out.println("What a shame, that was a miss :(");
                    currentPlayer.incrementMissCounter();
                }


                /*
                *CHECKING IF GAME IS FINISHED, IT WILL BE FINISHED IF ALL THE SHIPS RESERVED COORDENATES HAVE BEEN
                * CHOSEN BY ONE OR MORE PLAYERS.
                * */

                Integer shipHitsCounter = 0;
                for(int i = 0; i < this.board.fetchContent.length; i++){
                    for(int j = 0; j < this.board.fetchContent[0].length; j++){
                        //COUNT HOW MANY SHIPS COORDENATE HAVE BEEN DISCOVERED:
                        if(this.board.fetchContent[i][j] == BoardStates.revealedAndThereIsShip){
                            shipHitsCounter++;
                        }
                    }
                }
                if(shipHitsCounter == this.ships.length*this.ships[0].shipLen){
                    isFinished = true;
                }


            }
        }
        System.out.println(this.board);
        showRank();


        /*
        System.out.println(this.board);
        for(int i = 0; i < this.board.row; i++){
            for(int j = 0; j < this.board.cols; j++){
                checkIfHitsShip(i,j);
            }
        }
        System.out.println(this.board);

        */
    }


    public Boolean checkIfHitsShip(final Integer row, final Integer col){
        for(Integer[] coordenates : this.shipsReservedCoordenates){
            if(coordenates[0] == row && coordenates[1]== col){



                this.board = this.board.fetchesCoordenate(row, col, BoardStates.revealedAndThereIsShip);
                //System.out.println(this.board);
                return true;
            }
        }
        this.board = this.board.fetchesCoordenate(row, col, BoardStates.revealedButNoShip);
        return false;
    }

    public Boolean checkIfHitsShip(final Integer[] rowAndColumn) {
        Integer row = rowAndColumn[0];
        Integer col = rowAndColumn[1];

        for(Integer[] coordenates : this.shipsReservedCoordenates){
            if(coordenates[0] == row && coordenates[1]== col){

                this.board = this.board.fetchesCoordenate(row, col, BoardStates.revealedAndThereIsShip);
                //System.out.println(this.board);
                return true;
            }
        }

        this.board = this.board.fetchesCoordenate(row, col, BoardStates.revealedButNoShip);
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


    private boolean checkIfCordenateIsRevealed(Integer row, Integer col){
        if(this.board.fetchContent[row][col]!= BoardStates.notRevealed){
            return true;
        }else{
            return false;
        }
    }

    private void showRank() {
        for(Player player: this.players){
            System.out.print("Name: ");
            System.out.println(player.name);
            System.out.print("Age:");
            System.out.println(player.age);
            System.out.print("e_mail: ");
            System.out.println(player.e_mail);
            System.out.print("Total attempts:");
            System.out.println(player.getAttempts());
            System.out.print("Total Hits:");
            System.out.println(player.getHitCounter());
            System.out.print("Total Misses:");
            System.out.println(player.getMissCounter());
            System.out.println();
            System.out.println();
        }
    }








    private BattleShip(Board board, Ship[] ships){
        this.board = board;
        this.ships = ships;
        this.shipsReservedCoordenates = initShipsReservedCoordnates();
        this.players = null;
    }
}
