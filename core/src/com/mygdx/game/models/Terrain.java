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
     * Get the units that on on this hex
     */
    public List<Unit> getUnits();

    /**
     * Get the interaction of the tile
     */
    public Optional<Interaction> getInteraction();


    /**
     * The Defense of the tile
     */
    public int getDefense();

}
