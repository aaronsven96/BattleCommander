package com.mygdx.game.models;

import java.util.Set;

public class Player {

    private int playerId;
    private Set<Integer> units;

    public Player(int playerId, Set<Integer> units) {
        this.playerId = playerId;
        this.units = units;
    }

    /** Returns the player's id */
    public int getPlayerId() {
        return playerId;
    }

    /** Returns a list of unit ids for a player */
    public Set<Integer> getUnitIds() {
        return units;
    }

//    /**
//     * All moves that the player has made
//     */
//    List<Orders> getPlayerMoves();
//
//    /**
//     * Adds a player move
//     */
//    Orders addPlayerMove();
}
