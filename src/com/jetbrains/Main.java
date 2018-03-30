package com.jetbrains;

import com.jetbrains.components.Board;
import com.jetbrains.players.Bots;
import com.jetbrains.players.Player;
import com.jetbrains.tools.SystemTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        this.board = setUpBoard(); // HERE IS WHERE THE BOARD OBJECT WILL BE STORED
        this.players = setUpPlayers(); // HERE IS WHERE ALL THE PLAYERS WILL BE STORE AS A SET OF PLAYERS


        /**
         * PRINTING BOARD BEFORE GUESSES SECTION BEGINS
         */
        System.out.println(this.board);

        while(!this.isFinished){
            /**
             * This looping will walk through all players in the array players and give them the chance to play
             */
            for(int i = 0; i < players.size(); i++){
                System.out.println("It's "+players.get(i).name+ "'s turn! ");

                Integer row,col; // HERE ARE THE VARIABLES THAT WILL BE STORED THE VALUE INPUTED BY PLAYER

                Boolean validRoworCol; // THIS BOOLEAN VARIABLE IS USED BELLOW FOR VALIDATING VALUES FOR ROW AND COLLUMN

                /**
                 * RULES FOR ROWS AND COLUMNS
                 * The boolean variable above mentioned will only change to true if the user obeys the following rules:
                 * 1 - The row can't be greater than this.board.rows, it would be a invalid row otherwise.
                 * 2 - The same applies for columns, can't be greater than this.board.cols
                 * 3 - The user has to type coordinates separated by comas. Spaces will be ignored and
                 * valid value will still be stored into rows and cols variables.
                 * 4 - If the coordinate has already been chosen, this apply for Bots too.
                 *
                 */

                String input; // HERE IS WHERE THE STRING INPUT GOES
                do{
                    /**
                     * PLAYERS AND BOTS ARE DIFFERENT IN TERMS OF GUESSING
                     * if the current player, selected by the for loop above is instance of Player,
                     * the guessing is manually added by Human player.
                     * If current player is instance of Bots, means that the computer will generate random
                     * values for rows and cols.
                     */
                    if(!(players.get(i) instanceof Bots)){
                        System.out.println("Enter the coordinate");

                        input = getInput("(x,y)",
                                "[0-9 ]+,[0-9 ]+", "Invalid coordinate");


                        /**
                         * THE CONSTANT BELLOW WILL BE BREAK THE INPUT STRING WHERE THE COMA IS
                         * and assign whatever is on the left to row and right to column
                         */
                        final String[] inputArray = input.split(",");

                        /**
                         * assigning row and columns
                         */
                        row = Integer.parseInt(inputArray[0].trim());
                        col = Integer.parseInt(inputArray[1].trim());

                        if(row >= this.board.rows || col >= this.board.cols){
                            validRoworCol = false;
                        }else{
                            /**
                             * Checking if coordinate has been revealed, if so, the doWhile loop will start over
                             */
                            if(this.board.boardStates[row][col] != BoardStates.NOT_REVEALED){
                                //PRINTING THE ERROR INFORMATION
                                System.out.println("This coordinate has already been fetched, please, choose another one!");
                                validRoworCol = false;//STARTING OVER THE LOOP
                            }else{
                                /**
                                 * If row or column is grater than the board properties, the loop will start over too, as it has been
                                 * explained on the rules topic above described.
                                 */

                                if(row >= this.board.rows || col >= this.board.cols){
                                    System.out.println("The board has "+this.board.rows+" lines and "+this.board.cols+" columns");
                                    System.out.println("pick a number under "+this.board.rows+ "for rows and "+this.board.cols+"for columns");
                                    validRoworCol = false;//STARTING OVER THE LOOP
                                }else{
                                    /**
                                     * If code reaches here, then everything went fine, so the flow is good to proceed
                                     *
                                     */
                                    validRoworCol = true;
                                }
                            }
                        }



                    }else{

                        /**
                         * If code reaches here, means that the current player (player.get(i))
                         * is instance of Bots, then the guessing system is automatic by assigning
                         * random rows and cols for the coordinate
                         */
                        row = new Random().nextInt(this.board.rows);
                        col = new Random().nextInt(this.board.cols);

                        /**
                         * Same if statement to validade if coordinate has been chosen
                         */
                        if(this.board.boardStates[row][col] != BoardStates.NOT_REVEALED){
                            validRoworCol = false;//START OVER DOWHILE LOOP
                        }else{
                            validRoworCol = true;
                        }

                    }

                }while(!validRoworCol);


                System.out.println(players.get(i).name +" chooses coordinate ("+row+","+col+")\n");



                Integer[] chosenCoordinate = players.get(i).choosesRowAndColumn(row, col);

                /**
                 * Updating board with the chosen coordinates
                 */
                this.board.updateBoard(chosenCoordinate);

                /**
                 * This if statement will take care of scoring players based on their misses and hits
                 */
                if(this.board.boardStates[row][col] == BoardStates.REVEALED_AND_HAS_SHIP){
                    /**
                     * If code reaches here, means that user has a hit (found a slice of ship)
                     * So it will be incremented in 1 the hits attribute:
                     * Due to the immutability, it has to be assigned another player object at
                     * the same index players.get(i) with the new value for hits
                     */
                    System.out.println("Good one, you have a hit");
                    players.set(i, players.get(i).incrementHits());
                }else{
                    /**
                     * Same thing happens here, but instead of increasing hits, it will increase miss.
                     */
                    System.out.println("What a shame, you have got a miss. :(");
                    players.set(i, players.get(i).incrementMiss());
                }


                SystemPause();

                /**
                 * printing once more the board
                 */
                System.out.println(this.board);
            }

            /**
             * Checking if game is finished.
             * Game finishes if all ships coordinates were found.
             * The number of ship coordinates is the ship length times number of ships.
             */

            Integer discoveredShips = 0; // THIS VARIABLE WILL STORE THE NUMBER OF COORDINATES THAT HAVE SHIPS IN IT

            for(int i = 0; i < this.board.boardStates.length; i++){
                for(int j = 0; j < this.board.boardStates[0].length; j++){
                    if(this.board.boardStates[i][j] == BoardStates.REVEALED_AND_HAS_SHIP){
                        /**
                         * If the code arrives here, means that that ship was found, then increment discovered ships
                         */
                        discoveredShips++;
                    }
                }
            }

            /**
             * GAME IS FINISHED IF all ships coordinates have been revealed.
             * Example:
             * if a ship has length of 3 and the board has 3 ships, then the total of ships coordenates are 9.
             * Once discoveredShips variables reaches value of 9, gae is finished.
             */
            if(this.board.ships.length*this.board.ships[0].length == discoveredShips){

                isFinished = true;//ONCE THIS IS TRUE, GAME IS FINISHED
            }


        }
        /**
         * printing rank
         */
        rankPrint();
    }


    /**
     * method for printing the rank when game is finished
     */
    private void rankPrint(){
        for(Player p : this.players){
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
                getInput("Type the number of columns", "[1][0-9]|20", "Type numbers between 10 and 20!")
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

            /**
             * The allowed amount of bots will be set accordingly to the amount of Human players in game.
             * The switch statement bellow will define the stop range of bots.
             * if there is 3 Human players, only 1 bot will be added.
             * if 2 Human players, only 2 bots can be added
             * if 3, just 1 can be added.
             */
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
