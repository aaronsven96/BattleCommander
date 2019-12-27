package com.mygdx.game.models;

import java.util.List;

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
     * Get possibles positions for action
     */
    List<Position> getsAllPossiblePositionsForAnActionTypeAUnitCanMake(int unitID, Action action);

    /**
     * Get Hes bao
     */

    //Todo Define functions which we need t o show to user

}
