package com.mygdx.game.models;

import java.util.List;

/**
 * A class that represents all information about the game. Methods should relate to changing the game state
 */
public interface GameState {

    //boolean isGameOver();

    //int winningPlayer();

    /**
     * If the move is possible
     */
    boolean isValidMove(Orders move);

    /**
     * Adds a move for a specific player if possible
     */
    boolean setMove(int playerId, Orders move);

    /**
     * Returns whether a move is possibly blocked
     */
    boolean isMoveBlocked(int playerId, Orders move);

    /**
     * Advances turn if possible
     */
    boolean advanceTurn();

    /**
     * Get Hex Map which holds most of the game information
     */
    HexMap getHexMap();

    /**
     * Gets the current turn state
     */
    TurnState getTurnState();


    /**
     * Generate save file
     * returns if the game was successfully saved
     */
    public boolean generateSaveFile(String filename);

    /**
     * Initilizes the game state with a save file
     * returns if the file was Successfully loaded
     */
    public boolean loadSaveFile(String filename);
    //Todo Define functions which we need t o show to user

}
