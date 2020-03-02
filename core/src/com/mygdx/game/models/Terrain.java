package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * A class that represents a tile in the game.
 */
@Getter
@Setter
public class Terrain {
    private String type; // fixed type (e.g., "Desert", "Ocean", "Rock", "Snow")
    private int defense; // defense attribute
    private TerrainState terrainState; // current state (e.g., "IMPASSABLE")
    private String texture; // texture filename
    private int id; // unique ID

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
     * @param config the config
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
}
