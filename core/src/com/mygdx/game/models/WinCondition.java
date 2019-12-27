package com.mygdx.game.models;

public interface WinCondition {

    /**
     * The number of the winning player of the game, -1 if there is no such player
     */
    public int winningPlayer(GameState gameState);

    /**
     * If the game if over
     */
    public boolean isGameOver(GameState gameState);
}
