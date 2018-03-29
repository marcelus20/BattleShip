package com.jetbrains;

public enum BoardStates {
    /**
     * This is an enumerator that will define the three possible states that the board will have.
     * The board coordenate can be either not revealed, revealed but no ship found and revealed with a ship found.
     * This enumerator will be used in the toString method of the Board class to define what to show in the coordenate.
     */
    NOT_REVEALED, REVEALED_BUT_NO_SHIP, REVEALED_AND_HAS_SHIP
}
