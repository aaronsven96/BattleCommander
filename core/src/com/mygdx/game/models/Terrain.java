package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * A class that represents a tile in the game.
 */
public class Terrain {
    private String type;
    private int defense;
    private TerrainState terrainState;
    private String texture;
    private int id;

    private Terrain(String type, int defense, TerrainState terrainState, String texture, int id) {
        this.type = type;
        this.defense = defense;
        this.terrainState = terrainState;
        this.texture = texture;
        this.id = id;
    }

    // Copy constructor
    public Terrain(Terrain original) {
        type = original.type;
        defense = original.defense;
        terrainState = original.terrainState;
        texture = original.texture;
        id = original.id;
    }

    /**
     * Returns the Terrain from the configuration file.
     *
     * @param config the content
     * @return the Terrain from the configuration file
     */
    public static Terrain getTerrainFromConfig(String config, String texture, int id) {
        Gson gson = new Gson();
        JsonObject terrain = gson.fromJson(config, JsonObject.class);

        String type = terrain.get("type").getAsString();
        int defense = terrain.get("defense").getAsInt();
        TerrainState terrainState = TerrainState.valueOf(terrain.get("terrainState").getAsString());

        return new Terrain(type, defense, terrainState, texture, id);
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


    /**
     * Returns the Texture of the Terrain.
     *
     * @return the Texture of the Terrain
     */
    public String getTexture() {
        return texture;
    }

    /**
     * Returns the ID of the Terrain.
     *
     * @return the ID of the Terrain
     */
    public int getID() {
        return id;
    }

}
