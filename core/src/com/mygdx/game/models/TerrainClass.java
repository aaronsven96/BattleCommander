package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * A class that represents a tile in the game.
 */
public class TerrainClass implements Cloneable, Terrain {
    private String type;
    private int defense;
    private TerrainState terrainState;

    private TerrainClass(String type, int defense, TerrainState terrainState) {
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
        FileHandle file = Gdx.files.internal(filename);
        String content = file.readString();

        Gson gson = new Gson();
        JsonObject terrain = gson.fromJson(content, JsonObject.class);

        String type = terrain.get("type").getAsString();
        int defense = terrain.get("defense").getAsInt();
        TerrainState terrainState = TerrainState.valueOf(terrain.get("terrainState").getAsString());

        return new TerrainClass(type, defense, terrainState);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public TerrainState getTerrainState() {
        return terrainState;
    }

    @Override
    public TerrainClass clone() throws CloneNotSupportedException {
        TerrainClass tc = (TerrainClass) super.clone();
        return tc;
    }

}
