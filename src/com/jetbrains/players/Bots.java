package com.jetbrains.players;

import com.jetbrains.tools.SystemTools;

import static com.sun.xml.internal.ws.util.StringUtils.capitalize;

public class Bots extends Player{


    /**
     * The bot class will have exactly the same method as the Player one, the only difference is its toString, that won't
     * show age or email and the way it is initialised, which is needed just a name.
     * @param name
     */
    public Bots(final String name) {
        super(name, null, null);
    }

    private Bots(final String name, final Integer hits, final Integer miss, final Integer attempts){
        super(name, null, null, hits, miss, attempts);

    }


    /**
     * MODIFYING THE HITS ATTRIBUTE
     * THIS METHOD WILL RETURN ANOTHER PLAYER OBJECT WITH THE VALUE OF HITS CHANGED.
     */
    @Override
    public Player incrementHits(){
        return new Bots(this.name, this.hits+1, this.miss, this.attempts+1);
    }

    /**
     * MODIFYING THE MISS ATTRIBUTE
     * THIS METHOD WILL RETURN ANOTHER PLAYER OBJECT WITH THE VALUE OF HITS CHANGED.
     */
    @Override
    public Player incrementMiss(){
        return new Bots(this.name, this.hits, this.miss+1, this.attempts+1);
    }

    @Override
    public String toString() {
        return "***Bot***" + " \n"+
                "name: " + capitalize(name) + "\n" +
                "hits: " + hits + "\n"+
                "miss: " + miss + "\n"+
                "attempts: " + attempts;
    }
}
