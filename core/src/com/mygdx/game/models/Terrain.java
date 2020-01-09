package com.mygdx.game.models;

/**
 * An interface for a tile in the game.
 */
public interface Terrain {

    /**
     * Returns the type of the Terrain.
     *
     * @return the type of the Terrain
     */
    public String getType();

    /**
     * Returns the defense of the tile.
     *
     * @return the defense of the tile
     */
    public int getDefense();

    /**
     * Returns the Terrain State of the Terrain (e.g., impassable).
     *
     * @return the Terrain State of the Terrain (e.g., impassable)
     */
    public TerrainState getTerrainState();

}
