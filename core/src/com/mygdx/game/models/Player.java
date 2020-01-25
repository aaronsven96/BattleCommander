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
    List<Orders> getPlayerMoves();

    /**
     * Adds a player move
     */
    Orders addPlayerMove();

    /**
     * returns a list of units ids for a player
     */
    List<Integer> getPlayerUnits();

}
