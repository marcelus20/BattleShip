package com.jetbrains;


/**
 * This is just an Enumerator class, it has three states that the board may encounter itself,
 * not notRevealed means that player ot bot has not chosen the specific coordenate yet.
 * The other states is when user has chosen the coordenate but they represent whether there is ship or not.
 */
public enum BoardStates {

    notRevealed,
    revealedButNoShip,
    revealedAndThereIsShip,
}
