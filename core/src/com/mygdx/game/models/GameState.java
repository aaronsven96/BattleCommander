package com.mygdx.game.models;

/**
 * A class that represents all information about the game
 */
public interface GameState {

    boolean isGameOver();

    int winningPlayer();

    /**
     * If the move is possible
     */
    boolean isValidMove(Move move);

    /**
     * Adds a move for a specific player if possible
     */
    boolean setMove(int playerId, Move move);

    /**
     * Advances turn if possible
     */
    boolean advanceTurn();

    /**
     *
     */

    //Todo Define functions which we need t o show to user

}
