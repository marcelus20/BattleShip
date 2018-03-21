package com.jetbrains;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BattleShip {

    public Board board;
    private final Ship[] ships;
    private final Integer[][] shipsReservedCoordenates;
    private Player[] players;
    private Boolean isFinished;


    public  BattleShip(){
        this.board = new Board(20,20);
        this.ships = new Ship[]{
                new Ship(board.row, board.cols), new Ship(board.row, board.cols), new Ship(board.row, board.cols),
                new Ship(board.row, board.cols)
        };
        this.shipsReservedCoordenates = initShipsReservedCoordnates();

/*
        this.players = new Player[]{
                new Player("Felipe",
                        25,
                        "marcelus20felipe@gmail.com"),
                new Player("Sara",
                        26,
                        "sarasimionirock@gmail.com"),
                new Player("Leandro",
                        27,
                        "blah@gmail.com"

                )

        };
        */
        isFinished = false;

        gameSetup();
        printBoard();
        while(!this.isFinished){


            for(Player currentPlayer : this.players){

                printBoard();
                Integer row;
                Integer col;
                do{
                    row = new Random().nextInt(this.board.row);
                    col = new Random().nextInt(this.board.cols);
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
        printBoard();
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

    private void printBoard(){
        System.out.println(this.board);
    }

    private void gameSetup(){
        SystemTools sys = new SystemTools();
        welcome();

        System.out.println("Choose the amount of players in this game: MAX 4");
        final Integer player = Integer.parseInt(sys.getInput("No of players",
                "[1-4]", "PLease, type numbers between 1 and 4"));

        if(player< 4){
            System.out.println("Want to add bots to remaining players slots?");
            String ans = sys.getInput("Y/N", "[ynYN]", "Type just Y or N");
            if(ans.toLowerCase().equals("y")){
                System.out.println("How many bots?");
                String[] opt;
                Integer botAmouunt = 0;
                if(player == 1){
                    opt = new String[]{"1 Bot", "2 bots", "3 bots"};
                    sys.printTabledArray(opt);
                    botAmouunt = Integer.parseInt(sys.getInput(
                            "OPTION:", "[1-3]","Just numbers between 1 and 3."));
                    System.out.println("set 3 bots");
                }else if (player == 2){
                    opt = new String[]{"1 bot", "2 bots"};

                    sys.printTabledArray(opt);
                    botAmouunt = Integer.parseInt(sys.getInput(
                            "OPTION:", "[1-3]","Just numbers between 1 and 2."));
                    System.out.println("set 2 bots");
                }else{
                    botAmouunt = 1;
                    System.out.println("Set 1 bot");
                }

            }
        }

        switch(player){
            case 1 : this.players = new Player[1];
            break;
            case 2 : this.players = new Player[2];
            break;
            case 3 : this.players = new Player[3];
            break;
            case 4 : this.players = new Player[4];
            break;
        }

        for(int i = 0; i < this.players.length; i++){

            this.players[i] = new Player(
                    sys.getInput("What's your name?","[a-zA-Z]+","just alphabet characters!"),
                    Integer.parseInt(sys.getInput("Age: ", "[1-9]+", "Just numbers!")),
                    sys.getInput("E-mail: ",
                            "[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}",
                            "Invalid e-mail")
            );
        }



    }


    private void welcome(){
        String[] welcomeMsg = new String[]{
                "Welcome",
                "to the ",
                "Battleship",
                "Game!"
        };
        SystemTools sys = new SystemTools();
        sys.printTabledArray(welcomeMsg);
    }








    private BattleShip(Board board, Ship[] ships){
        this.board = board;
        this.ships = ships;
        this.shipsReservedCoordenates = initShipsReservedCoordnates();
        this.players = null;
    }
}
