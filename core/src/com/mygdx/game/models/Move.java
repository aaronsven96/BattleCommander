package com.mygdx.game.models;


import java.util.List;

/**
 *
 */
public interface Move {
    // The unit that will be moved by this move
    int getUnitId();

    /**
     * The number of turns this move will take
     */
    int duration();

    /**
     * The current move
     */
    Command currentMove();

    /**
     * Iterates to the next move
     */
    Command nextMove();

    /**
     * A list of all the Directions a unit will move with this move
     */
    List<Command> getMovement();
}
