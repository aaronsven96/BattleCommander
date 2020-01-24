package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        textures = original.textures;
    }

    public static HexMap getHexMapFromConfig(String content) {
        Gson gson = new Gson();
        JsonObject hexMap = gson.fromJson(content, JsonObject.class);

        HexBoard<List<Unit>> units = new HexBoard<>(null, 2, 2); // TODO: hardcoded???
        HexBoard<Terrain> terrain = null;
        HexBoard<Boolean> mapShape = null;
        List<HexBoard<String>> textures = null;

        ConfigurationFactory cf = ConfigurationFactory.instance;
        JsonArray jsonArray1 = hexMap.get("units").getAsJsonArray();
        int row = 0;
        int column = 0;

        for (JsonElement j : jsonArray1) { // loop through outer Array
            List<Unit> unitsList = new ArrayList<>();
            JsonArray jsonArray2 = j.getAsJsonArray();
            for (JsonElement k : jsonArray2) { // loop through inner Array
                String text = k.getAsString(); // "archer.json", "soldier.json", "null", etc.
                Unit unit = cf.makeUnitFromConfig(text);
                unitsList.add(unit);
                units.setHex(new Position(row, column), unitsList); // set up the HexBoard
                column++;
            }
            row++;
        }

        return new HexMap(units, terrain, mapShape, textures);
    }

    /**
     * Get the units at a position P
     */
    public Optional<List<Unit>> getUnits(Position p) {
        return units.getHex(p);
    }

    /**
     *
     */
    //Todo add interactions to the game
    //Interaction getInteraction(Position p);

    /**
     * Get the terrain at a position P
     */
    public Optional<Terrain> getTerrain(Position p) {
        return terrain.getHex(p);
    }

    /**
     * Gets all units for a player with id
     */
    public List<Unit> getUnitsForPlayer(int playerNum) {
        return null; // TODO
    }

    /**
     * Returns a List of Strings which refer to the file location of the textures which relate to each layer of the map
     */
    public List<HexBoard<String>> getTextures() {
        return textures;
    }

    /**
     * Returns a HexMap of Booleans which describes which tiles are in the map
     */
    public HexBoard<Boolean> getMapShape() {
        return mapShape;
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
