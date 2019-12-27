package com.mygdx.game.models;

import java.util.List;

/*
 * The hex board of Type T that we will use as the map of the game. T will be a terrain
 */
public interface HexBoard<T> {

    /*
     * A function that returns the object at position x,y
     */
    public T getHex(int x, int y);

    /*
     * A function that returns a list of neighbors to an hex at x,y
     */
    public List<T> getHexNeighbors(int x, int y);

    /*
     * A function that sets a hex at position x,y
     */
    public boolean setHex(int x, int y, T hex);

    /*
     *
     */


}
