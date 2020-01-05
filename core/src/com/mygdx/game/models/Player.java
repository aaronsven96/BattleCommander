package com.mygdx.game.models;

import java.util.List;

public interface Player {

    /**
     * The number associated with the Player
     */
    int getPlayerNum();

    /**
     * All moves that the player has made
     */
    List<Move> getPlayerMoves();

    /**
     * Adds a player move
     */
    Move addPlayerMove();

    /**
     * returns a list of units ids for a player
     */
    List<Integer> getPlayerUnits();

}
