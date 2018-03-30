package com.jetbrains.players;

import com.jetbrains.tools.SystemTools;

public class Bots extends Player{


    /**
     * The bot class will have exactly the same method as the Player one, the only difference is its toString, that won't
     * show age or email and the way it is initialised, which is needed just a name.
     * @param name
     */
    public Bots(final String name) {
        super(name, null, null);
    }

    @Override
    public String toString() {
        return "====Bot======" + " \n"+
                "name: " + name + "\n" +
                "hits: " + hits + "\n"+
                "miss: " + miss + "\n"+
                "attempts: " + attempts;
    }
}
