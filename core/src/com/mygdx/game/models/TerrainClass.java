package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that represents a tile in the game.
 */
public class TerrainClass implements Terrain {
    private String type;
    private int defense;
    private TerrainState terrainState;

    public TerrainClass(String type, int defense, TerrainState terrainState) {
        this.type = type;
        this.defense = defense;
        this.terrainState = terrainState;
    }

    /**
     * Returns the Terrain from the configuration file.
     *
     * @param filename the filename
     * @return the Terrain from the configuration file
     */
    public static TerrainClass getTerrainFromConfig(String filename) {
        String content;
        try {
            content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No configuration file found for the Terrain.");
        }

        Gson gson = new Gson();
        JsonObject terrain;
        terrain = gson.fromJson(content, JsonObject.class);

        String type = terrain.get("Type").getAsString();
        int defense = terrain.get("Defense").getAsInt();
        TerrainState terrainState = terrain.get("Terrain State").getAsString();

        return new TerrainClass(type, defense, terrainState);
    }

    /**
     * Returns the type of the Terrain.
     *
     * @return the type of the Terrain
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Returns the defense of the tile.
     *
     * @return the defense of the tile
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the Terrain State of the Terrain (e.g., impassable).
     *
     * @return the Terrain State of the Terrain (e.g., impassable)
     */
    @Override
    public TerrainState getTerrainState() {
        return terrainState;
    }

}
