package com.mygdx.game.models;

import java.util.List;

/*
 * The hex board of Type T that we will use as the map of the game. T will be a terrain
 */
public interface HexBoard<T> {

    /*
     * Returns the object at position x,y
     */
    public T getHex(Position p);

    /*
     * Returns a list of neighbors to a hex at x,y
     */
    public List<T> getHexNeighbors(Position p);

    /*
     * Sets a hex at position x,y
     */
    public boolean setHex(Position p, T hex);

    /*
     * Returns the board
     */
    public T[][] getBoard();

}
