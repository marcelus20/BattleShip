package com.jetbrains;

import com.jetbrains.components.Board;
import com.jetbrains.players.Bots;
import com.jetbrains.players.Player;
import com.jetbrains.tools.SystemTools;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends SystemTools{// THIS INHERITANCE IS JUST FOR MAKING SIMPLE THE CALLING OF SYSTEMTOOLS CLASS METHODS.

    /**
     * This is the Game class. All the other classes will be connected, instantiated and executed here.
     * This class has a set of maximun of 4 players (Array of players: Player[] players), just 1 board and
     * 1 boolean "isFinished" to indicate when the game is finished, its default value will be set as false when game starts
     *
     * The array of players attribute may store also Bots instances objects.
     *
     */

    /**
     * List of its attributes:
     */
    private final ArrayList<Player> players;
    private final Board board;
    private Boolean isFinished;


    /**
     * Constructor
     */
    Main(){



        this.isFinished = false; // ASSIGNING FALSE TO IS FINISHED.
        this.board = setUpBoard();
        this.players = setUpPlayers();
        for(Player p : players){
            System.out.println(p);
        }
    }


    /**
     * method for initialising the board attribute. Player will input the value of rows and cols to set up the board
     * dimensions
     * @return new Board(rows, cols)
     */
    private Board setUpBoard(){

        /**
         * ASKING USER TO INPUT THE AMOUNT OF ROWS AND COLS
         */
        final Integer row = Integer.parseInt(
                getInput("Type the number of rows", "[1][0-9]|20", "Type numbers between 10 and 20!")
        );

        final Integer cols = Integer.parseInt(
                getInput("Type the number of rows", "[1][0-9]|20", "Type numbers between 10 and 20!")
        );

        //RETURNING THE BOARD WITH THE DIMENSIONS ABOVE INPUTED BY THE USER.
        return new Board(row, cols);
    }

    /**
     * Method for initialising the arrays of players.
     * @return new ArrayList<Player>.
     */
    private ArrayList<Player> setUpPlayers(){
        ArrayList<Player> tempPlayers= new ArrayList<Player>();

        final Integer amountOfPlayers = Integer.parseInt(
                getInput("How many players will participate?", "[1-4]", "Type numbers between 1 and 4")
        );

        /**
         * If amount of players is less than 4, game will ask if user wants to insert bots to the game:
         *
         */
        final Integer botsAmount;
        printTabledArray("Do you want to insert bots to the game?");
        String answer = getInput("Y/N", "[ynYN]", "type just Y for yes or N for no");
        if(answer.toLowerCase().equals("y")){
            answer = "";
            printTabledArray("How many bots would you like to insert?");

            final Integer rangeStop;
            switch (amountOfPlayers){
                case 1: rangeStop = 3; break;
                case 2: rangeStop = 2; break;
                case 3: rangeStop = 1; break;
                default: rangeStop = 1;
            }
            answer = getInput("MAX "+rangeStop+ "can be added", "[1-"+rangeStop+"]", "Type numbers between 1 and "+rangeStop);
            botsAmount = Integer.parseInt(answer);

            /**
             * ADDING BOTS TO THE LIST
             */
            for (int i = 0; i < botsAmount; i++){
                tempPlayers.add(new Bots(genRandomName()));
                System.out.println("Added BOT player: "+ tempPlayers.get(i).name);
            }
        }


        /**
         * Working on the Human Player:
         */

        for(int i = 0; i < amountOfPlayers; i++){
            System.out.println("Setting up player "+String.valueOf(i+1));
            final String name = getInput("What's your name?", "[a-zA-Z ]+", "Type just alphabet characters");
            final Integer age = getAge();
            final String eMail = getInput("your Email: ", "[a-zA-Z.0-9]+@[a-z]+.[a-z]+([a-z])?", "Invalid email.");
            tempPlayers.add(new Player(name, age, eMail));
        }

        return tempPlayers;
    }

    /**
     * Method for validating player age:
     * @return age - integer between range 12 and 99.
     */
    private Integer getAge(){
        Integer age;

        do{
            age = Integer.parseInt(getInput("How old are you?", "[0-9]+", "Just numbers!"));
            if(age < 12 || age > 100){
                System.out.println("Age should be over 12 and under 100");
            }
        }while(age < 12 || age > 100);

        return age;
    }



    public static void main(String[] args) {
	// write your code here

        new Main();
    }
}
