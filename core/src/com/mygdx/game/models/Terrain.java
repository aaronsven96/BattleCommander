package com.mygdx.game.models;

import java.util.List;
import java.util.Optional;

/**
 * A class that represents a tile in the game
 */
public interface Terrain {

    /**
     * The type of the terrain
     */
    public String getType();

    /**
     * The Defense of the tile
     */
    public int getDefense();

}
