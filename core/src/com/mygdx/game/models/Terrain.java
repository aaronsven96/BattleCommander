package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * A class that represents a tile in the game.
 */
public class Terrain {
    private String type;
    private int defense;
    private TerrainState terrainState;

    private Terrain(String type, int defense, TerrainState terrainState) {
        this.type = type;
        this.defense = defense;
        this.terrainState = terrainState;
    }

    // Copy constructor
    public Terrain(Terrain original) {
        type = original.type;
        defense = original.defense;
        terrainState = original.terrainState;
    }

    /**
     * Returns the Terrain from the configuration file.
     *
     * @param filename the filename
     * @return the Terrain from the configuration file
     */
    public static Terrain getTerrainFromConfig(String filename) {
        FileHandle file = Gdx.files.internal(filename);
        String content = file.readString();

        Gson gson = new Gson();
        JsonObject terrain = gson.fromJson(content, JsonObject.class);

        String type = terrain.get("type").getAsString();
        int defense = terrain.get("defense").getAsInt();
        TerrainState terrainState = TerrainState.valueOf(terrain.get("terrainState").getAsString());

        return new Terrain(type, defense, terrainState);
    }

    /**
     * Returns the type of the Terrain.
     *
     * @return the type of the Terrain
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the defense of the tile.
     *
     * @return the defense of the tile
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the Terrain State of the Terrain (e.g., impassable).
     *
     * @return the Terrain State of the Terrain (e.g., impassable)
     */
    public TerrainState getTerrainState() {
        return terrainState;
    }

}
