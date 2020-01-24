package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class HexMap {
    HexBoard<List<Unit>> units;
    HexBoard<Terrain> terrain;
    HexBoard<Boolean> mapShape;
    List<HexBoard<String>> textures;

    private HexMap(HexBoard<List<Unit>> units, HexBoard<Terrain> terrain, HexBoard<Boolean> mapShape, List<HexBoard<String>> textures) {
        this.units = units;
        this.terrain = terrain;
        this.mapShape = mapShape;
        this.textures = textures;
    }

    // Copy constructor
    public HexMap(HexMap original) {
        units = original.units;
        terrain = original.terrain;
        mapShape = original.mapShape;
        this.textures = original.textures;
    }

    public static HexMap getHexMapFromConfig(String content) {
        Gson gson = new Gson();
        JsonObject terrain = gson.fromJson(content, JsonObject.class);

        String type = terrain.get("type").getAsString();
        int defense = terrain.get("defense").getAsInt();
        TerrainState terrainState = TerrainState.valueOf(terrain.get("terrainState").getAsString());
        Texture texture = new Texture(Gdx.files.internal(terrain.get("texture").getAsString()));

       // return new Terrain(type, defense, terrainState, texture);
        return null;
    }

    /**
     * Get the units at a position P
     */
    List<Unit> getUnits(Position p) {
        return null;
    }

    /**
     *
     */
    //Todo add interactions to the game
    //Interaction getInteraction(Position p);

    /**
     * Get the terrain at a position P
     */
    Terrain getTerrain(Position p) {
        return null;
    }

    /**
     * Gets all units for a player with id
     */
    public List<Unit> getUnitsForPlayer(int playerNum) {
        return null;
    }

    /**
     * Returns a List of Strings which refer to the file location of the textures which relate to each layer of the map
     */
    List<HexBoard<String>> getTextures() {
        return null;
    }

    /**
     * Returns a HexMap of Booleans which describes which tiles are in the map
     */
    HexBoard<Boolean> getMapShape() {
        return null;
    }

    /**
     * Get possible moves
     */
    /*List<Position> getPossibleMoves(Action action, int unitId){
        return null;
    }*/

    /**
     * returns a list of potentially blocked commands
     */
    /*List<Command> getBlockedCommands(){
        return null;
    }*/

}
