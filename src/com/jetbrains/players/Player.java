package com.jetbrains.players;

/**
 * This class implements Comparable interface cause it has an Override method called ComparedTo
 * that will help build the rank method at Main class.
 * Basically this Comparable interface will make possible to
 * sort the Array of players by their attributes this.hits.
 */

public class Player implements Comparable<Player>{//--> implementing and Interface to sort arrays of Players
    /**
     * THIS CLASS IS A BLUEPRINT OF WHAT THE BATTLESHIP PLAYER WILL BE ABLE TO DO.
     *
     * The player has 6 attributes: name, age, email, hits, miss and attempts.
     * They will all be immutable ("final" will be declared), therefore, no need to keep them private or make getter and setters.
     * The only way of modifying hits, miss and attempts is by creating a new instance of player
     * with the new value.
     *
     * When the object is first created, the contructor parameters will ask just for three variables, name, age and email.
     * The other attributes will begin with 0 (hits, misses and attempts)
     *
     * For modifying the other immutable attributes, a private constructor will be called to create the player with the
     * different values for hits, misses and attempts. This will be done by using Player methods.
     *
     *
     * THIS CLASS HAS NO GETTER AND SETTERS BECAUSE IT HAS GOT IMMUTABLE AND PUBLIC ATTRIBUTES.
     * The setter method is kind what the incrementHits and IncrementMiss methods do.
     */


    /**
     * PLAYER ATTRIBUTES
     */
    public final String name;
    public final Integer age;
    public final String eMail;
    public final Integer hits;
    public final Integer miss;
    public final Integer attempts;

    /**
     * DEFAULT CONSTRUCTOR FOR INITIALIZING ALL THIS ATTRIBUTES:
     *
     */
    public Player(final String name, final Integer age, final String eMail){
        this.name = name;
        this.age = age;
        this.eMail = eMail;

        /**
         * ITINIALIZING HITS MISS AND ATTEMPTS WITH 0
         */
        this.hits = 0;
        this.miss = 0;
        this.attempts = 0;
    }

    /**
     * SPECIAL CONSTRUCTOR FOR CHANGING THE OBJECT BY CREATING A NEW INSTANCE WITH NEW VALUES FOR
     * HITS MISS AND ATTEMPTS. THIS IS TO BE CALLED JUST IN THIS CLASS INSIDE THE CHANGER METHODS.
     */
    protected Player(
            final String name, final Integer age, final String eMail,
            final Integer hits, final Integer miss, final Integer attempts
    ){
        this.name = name;
        this.age = age;
        this.eMail = eMail;
        this.hits = hits;
        this.miss = miss;
        this.attempts = attempts;

    }

    /**
     * MODIFYING THE HITS ATTRIBUTE
     * THIS METHOD WILL RETURN ANOTHER PLAYER OBJECT WITH THE VALUE OF HITS CHANGED.
     */
    public Player incrementHits(){
        return new Player(this.name, this.age, this.eMail, this.hits+1, this.miss, this.attempts+1);
    }

    /**
     * MODIFYING THE MISS ATTRIBUTE
     * THIS METHOD WILL RETURN ANOTHER PLAYER OBJECT WITH THE VALUE OF HITS CHANGED.
     */
    public Player incrementMiss(){
        return new Player(this.name, this.age, this.eMail, this.hits, this.miss+1, this.attempts+1);
    }


    /**
     * This method is for the player to choose the coordenate he/she will choose to guess if
     * Ship will be revealed. it will return an Array of integers that represents the chosen coordenate
     * @param row: the line in the board
     * @param col: the col in the line
     * @return the coordenate as an Array
     */
    public Integer[] choosesRowAndColumn(final Integer row, final Integer col){
        //RETURNING THE COORDENATE (ARRAY OF ROWS AND COLUMNS)
        return new Integer[]{row, col};
    }


    /**
     * This is the toString method. It will just display all the value of the attributes when asked
     * to print the player object.
     * @return PLAYER ATTRIBUTES
     *
     */
    @Override
    public String toString() {
        return "========HumanPlayer" + "\n"+
                "name: " + name + '\n' +
                "age: " + age + '\n' +
                "eMail: " + eMail + '\n' +
                "hits: " + hits + '\n'+
                "miss: " + miss + '\n' +
                "attempts: " + attempts+'\n'
                ;
    }


    /**
     * This method will arrange the array of Players object in descending order.
     * Once again, this is an override from Comparable interface.
     * This is possible only because I have declared this class implements Comparable.
     * @param comparePlayer
     * @return CompareHits - this.hits
     */
    @Override
    public int compareTo(Player comparePlayer) {

        if(this.hits == comparePlayer.hits){
            return 0;
        }else if (this.hits > comparePlayer.hits){
            return -1;
        }else{
            return 1;
        }

    }
}
