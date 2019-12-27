package com.mygdx.game.models;

public interface Interaction {

    /**
     * Returns a human readable description about the message
     */
    public String getMessage();

    /**
     * Modifies the game state, returns true if successful
     */
    public boolean execute(GameState gameState);
}
