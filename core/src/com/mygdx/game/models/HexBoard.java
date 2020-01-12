package com.mygdx.game.models;

import java.util.List;

/**
 * An interface for the hex board of Type T that we will use as the map of the game. T will be a terrain.
 */
public interface HexBoard<T> {

    /**
     * Returns the object at position x,y.
     *
     * @param p the position
     * @return the object at position x,y
     */
    public T getHex(Position p);

    /**
     * Returns a List of neighbors to a hex at x,y.
     *
     * @param p the position
     * @return a List of neighbors to a hex at x,y
     */
    public List<T> getHexNeighbors(Position p);

    /**
     * Sets a hex at position x,y.
     *
     * @param p   the position
     * @param hex the hex
     * @return true if the position is set, false otherwise
     */
    public boolean setHex(Position p, T hex);

    /**
     * Returns the board.
     *
     * @return the board
     */
    public T[][] getBoard();

}
