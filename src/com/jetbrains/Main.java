package com.jetbrains;

import java.util.Arrays;

public class Main {

    final Player[] players;


    Main(){
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


    }


    public static void main(String[] args) {
	// write your code here

        new Main();
    }
}
