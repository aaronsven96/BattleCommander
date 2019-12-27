package com.mygdx.game.models;

public interface Command {

    /**
     * Get the action associated with the command
     */
    Action getAction();

    /**
     * Get position that the action is directed at
     */
    Position getPosition();
}
