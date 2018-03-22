package com.jetbrains;

import com.jetbrains.components.Board;
import com.jetbrains.components.Ship;
import com.jetbrains.players.Bots;
import com.jetbrains.players.Player;
import com.jetbrains.tools.SystemTools;

import java.util.*;

public class BattleShip {

    public Board board;
    private final Ship[] ships;
    private final Integer[][] shipsReservedCoordenates;
    private Player[] players;
    private Bots[] bots;
    private Boolean isFinished;


    public  BattleShip(){
        SystemTools sys = new SystemTools();
        welcome();
        final Integer boardRow = Integer.parseInt(sys.getInput("Enter the number of ROWS this board will have: ",
                "([1][0-9])|([2][0])", "Just numbers between 10 and 20"));
        final Integer boardColumn = Integer.parseInt(sys.getInput("Enter the number of COLUMNS this board will have: ",
                "([1][0-9])|[2][0]", "Just numbers between 10 and 20"));

        this.board = new Board(boardRow,boardColumn);
        this.ships = initShips();
        this.shipsReservedCoordenates = initShipsReservedCoordnates();

        isFinished = false;

        gameSetup();
        printBoard();

        List<Player> botsAndPLayers = new ArrayList<Player>();

        for(Player p : this.players){
            botsAndPLayers.add(p);
        }
        if(this.bots != null){
            for(Bots bot : this.bots){
                botsAndPLayers.add(bot);

            }
        }




        while(!this.isFinished){


            for(Player currentPlayer : botsAndPLayers){

                sys.printTabledArray(new String[]{"That's "+currentPlayer.name+" turn"});

                printBoard();
                System.out.println("AMOUNT OF SHIPS TO BE DISCOVERED: "+this.ships.length);
                Integer row;
                Integer col;


                /*
                * TO ENABLE AUTO GUESS MODE, EVEN FOR PLAYERS, JUST REPLACE
                * THE "currentPlayer instanceof Bots" to "currentPlayer instanceof Player"
                * ON THE LINE BELOW.
                * IF AUTO GUESS MODE IS ON, SO IF THE LINE BELOW IS "currentPlayer instanceof Player",
                * it is suggested to comment the line 128 that says: sys.SystemPause();
                * That being so, there won't be any interruption and the com.jetbrains.components.game will finish quickly enough
                * for you to see the score display straight away.
                * */

                if(currentPlayer instanceof Bots){//if(currentPlayer instanceof Player){
                    do{
                        row = new Random().nextInt(this.board.row);
                        col = new Random().nextInt(this.board.cols);
                    }while(checkIfCordenateIsRevealed(row, col));
                }else{
                    do{
                        do{row = Integer.parseInt(sys.getInput("ROW: ", "[0-9]+", "just numbers"));
                            col = Integer.parseInt(sys.getInput("COLUMN: ", "[0-9]+", "just numbers"));
                            if(row > this.board.row || col > this.board.cols){
                                sys.printTabledArray("insert value from 0 until "+String.valueOf(this.board.row-1)+
                                "for row and values from 0 until "+String.valueOf(this.board.row-1)+"for columns");
                            }

                        }while(row > this.board.row || col > this.board.cols);
                        if(checkIfCordenateIsRevealed(row, col)){
                            sys.printTabledArray("The row "+ row + "and column "+col+" have been fetched already!");
                        }

                    }while(checkIfCordenateIsRevealed(row, col));
                }



                Integer[] coordenate = currentPlayer.choosesCoordenate(row, col);
                System.out.println(currentPlayer.name+" chooses coordenates ("+row+","+col+")");
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

                sys.SystemPause();//COMMENT THIS LINE IF AUTO GUESS MODE IS ON!


            }
        }
        printBoard();
        showRank((ArrayList<Player>) botsAndPLayers);



    }


    private Ship[] initShips (){
        final Integer numberOfShips = new Random().nextInt(5);
        Ship[] tempShip = new Ship[numberOfShips];

        String createdCoordenates = "";

        for(int i = 0; i < numberOfShips; i++){
            Ship ship = new Ship(this.board.row, this.board.cols);
            while(createdCoordenates.contains(Arrays.deepToString(ship.position))){
                ship = new Ship(this.board.row, this.board.cols);
            }
            createdCoordenates = Arrays.deepToString(ship.position);

            tempShip[i] = ship;

        }
        return tempShip;
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

    private void showRank(ArrayList<Player> botsAndPLayers) {


        for(Player player: botsAndPLayers){

            System.out.print("Name: ");
            System.out.println(player.name);
            if(player instanceof Bots){
                System.out.println("****** (BOT)******");
            }

            if(!(player instanceof Bots)){
               System.out.print("Age:");
                System.out.println(player.age);
                System.out.print("e_mail: ");
                System.out.println(player.e_mail);
            }

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


        System.out.println("Choose the amount of players in this com.jetbrains.components.game: MAX 4");
        final Integer player = Integer.parseInt(sys.getInput("No of players",
                "[1-4]", "PLease, type numbers between 1 and 4"));

        Integer botAmouunt = 0;
        if(player< 4){
            System.out.println("Want to add bots to remaining players slots?");
            String ans = sys.getInput("Y/N", "[ynYN]", "Type just Y or N");
            if(ans.toLowerCase().equals("y")){
                System.out.println("How many bots?");
                String[] opt;

                if(player == 1){
                    opt = new String[]{"1 Bot", "2 bots", "3 bots"};
                    sys.printTabledArray(opt);
                    botAmouunt = Integer.parseInt(sys.getInput(
                            "OPTION:", "[1-3]","Just numbers between 1 and 3."));


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

            if(i == 0){
                System.out.println("First Player:");
            }else{
                System.out.println("Next player:");
            }
            this.players[i] = new Player(
                    sys.getInput("What's your name?","[a-zA-Z]+","just alphabet characters!"),
                    Integer.parseInt(sys.getInput("Age: ", "[1-9]+", "Just numbers!")),
                    sys.getInput("E-mail: ",
                            "[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}",
                            "Invalid e-mail")
            );
        }
        switch (botAmouunt){

            case 1 : this.bots = new Bots[1];break;
            case 2 : this.bots = new Bots[2];break;
            case 3 : this.bots = new Bots[3];break;
        }
        //System.out.println(this.bots.length);

        if(botAmouunt!=0){
            String botExistingNames="";
            for(int i = 0; i < this.bots.length; i++){
                String name = sys.genRandomName();
                while(botExistingNames.contains(name)){
                    name = sys.genRandomName();
                }
                botExistingNames+=name+" ";
                this.bots[i] = new Bots(name);
                System.out.println("Added "+this.bots[i].name+"(BOT)");

            }
        }

        sys.SystemPause();





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
