package com.jetbrains;

import com.jetbrains.components.Board;

import java.util.Arrays;

public class Main {

    //final Player[] players;


    Main(){
        /*
        players = new Player[4];

        for(int i = 0; i < 4; i++){
            players[i] = new Player("", 0, "");

        }

        for(int i = 0; i < 4; i++){
            players[i] = players[i].incrementHits().incrementMiss();


        }
        for(int i = 0; i < 4; i++){
            System.out.println(players[0]);

        }
        */
        Board board = new Board(10,10);
        System.out.println(board);
        for(int i =0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                board.updateBoard(new Integer[]{i,j});
            }
        }

        System.out.println(board);
        System.out.println(Arrays.deepToString(board.ships));

    }


    public static void main(String[] args) {
	// write your code here

        new Main();
    }
}
