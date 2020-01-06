package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TerrainClass implements Terrain {
    private String type;
    private int defense;
    private TerrainState terrainState;

    public TerrainClass() {
    }

    public static void create() {
        String content = null;
        try {
            content = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No configuration file found for the Terrain.");
        }
        Gson gson = new Gson();
        JsonObject terrain;
        terrain = gson.fromJson(content, JsonObject.class);
        terrain.get("Type").getAsString();
        terrain.get("Defense").getAsInt();
        terrain.get("Terrain State").getAsString();

        health = max_health; //set the unit's health to its max health
    }

    /*
     * The type of the terrain
     */
    @Override
    public String getType() {
        return type;
    }

    /*
     * The Defense of the tile
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /*
     * Gets the Terrain State of the Terrain, e.g. impassable
     */
    @Override
    public TerrainState getTerrainState() {
        return terrainState;
    }

}
