package com.mygdx.game.models;

/**
 * A class that represents a tile in the game.
 */
public interface Terrain {

    /**
     * Returns the type of the terrain.
     */
    public String getType();

    /**
     * Returns the defense of the tile.
     */
    public int getDefense();

    /**
     * Gets the Terrain State of the Terrain (e.g., impassable).
     */
    public TerrainState getTerrainState();

}
