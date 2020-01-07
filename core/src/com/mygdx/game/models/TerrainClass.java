package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        try {
            FileHandle file = Gdx.files.local(filename);
            String content = file.readString();

            Gson gson = new Gson();
            JsonObject terrain = gson.fromJson(content, JsonObject.class);

            String type = terrain.get("Type").getAsString();
            int defense = terrain.get("Defense").getAsInt();
            TerrainState terrainState = TerrainState.valueOf(terrain.get("Terrain State").getAsString());

            return new TerrainClass(type, defense, terrainState);
        } catch (Exception e) {
            System.out.println("The Terrain configuration file is missing or invalid.");
            e.printStackTrace();
        }
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
