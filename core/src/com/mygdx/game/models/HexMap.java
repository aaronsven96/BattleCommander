package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
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
        JsonObject hexMap = gson.fromJson(content, JsonObject.class);

        HexBoard<List<Unit>> units = new HexBoard<>(null, 2, 2);
        HexBoard<Terrain> terrain = null;
        HexBoard<Boolean> mapShape = null;
        List<HexBoard<String>> textures = null;

        ConfigurationFactory cf = ConfigurationFactory.instance;
        JsonArray units1 = hexMap.get("units").getAsJsonArray();
        int count1 = 0;
        int count2 = 0;
        for (JsonElement j : units1) {
            JsonArray units2 = j.getAsJsonArray();
            for (JsonElement k : units2) {
                String unit3 = k.getAsString();
                Unit unit4 = cf.makeUnitFromConfig(unit3);
                List<Unit> unitsList = new ArrayList<>();
                unitsList.add(unit4);
                Position p = new Position(count1, count2);
                units.setHex(p, unitsList);
                count2++;
            }
            count1++;
        }

        String type = hexMap.get("type").getAsString();
        int defense = hexMap.get("defense").getAsInt();
//        TerrainState terrainState = TerrainState.valueOf(terrain.get("terrainState").getAsString());
        String texture = hexMap.get("texturePath").getAsString();

        return new HexMap(units, terrain, mapShape, textures);
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
