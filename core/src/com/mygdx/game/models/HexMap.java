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

        int rows = Integer.parseInt(hexMap.get("rows").getAsString());
        int columns = Integer.parseInt(hexMap.get("columns").getAsString());

        HexBoard<List<Unit>> units = new HexBoard<>(null, rows, columns);
        HexBoard<Terrain> terrain = new HexBoard<>(null, rows, columns);
        HexBoard<Boolean> mapShape = new HexBoard<>(null, rows, columns);
        List<HexBoard<String>> textures = null;

        ConfigurationFactory cf = ConfigurationFactory.instance;
        JsonArray jsonArrayUnits1 = hexMap.get("units").getAsJsonArray();
        JsonArray jsonArrayTerrain1 = hexMap.get("terrain").getAsJsonArray();
        JsonArray jsonArrayMapShape1 = hexMap.get("mapShape").getAsJsonArray();

        // Set up units
        int row = 0;
        int column = 0;
        for (JsonElement j : jsonArrayUnits1) {
            String config = j.getAsJsonObject().get("config").getAsString(); // "archer.json", "soldier.json", "null", etc.
            String texture = j.getAsJsonObject().get("texture").getAsString(); // TODO
            Unit unit0 = config.equals("null") ? null : cf.makeUnitFromConfig(config);
            List<Unit> unitsList = new ArrayList<>();
            unitsList.add(unit0);
            units.setHex(new Position(row, column), unitsList); // set up the HexBoard
            column++;
            if (column == columns) {
                column = 0;
                row++;
            }
        }

        // Set up terrain
        row = 0;
        column = 0;
        for (JsonElement j : jsonArrayTerrain1) {
            String config = j.getAsJsonObject().get("config").getAsString(); // "swamp.json", "desert.json", "ocean.json", "snow.json", etc.
            String texture = j.getAsJsonObject().get("texture").getAsString(); // TODO
            Terrain terrain0 = config.equals("null") ? null : cf.makeTerrainFromConfig(config);
            terrain.setHex(new Position(row, column), terrain0);
            column++;
            if (column == columns) {
                column = 0;
                row++;
            }
        }

        // Set up mapShape
        row = 0;
        column = 0;
        for (JsonElement j : jsonArrayMapShape1) {
            JsonArray jsonArrayMapShape2 = j.getAsJsonArray();
            for (JsonElement k : jsonArrayMapShape2) {
                String text = k.getAsString(); // "true", "false"
                mapShape.setHex(new Position(row, column), Boolean.parseBoolean(text));
                column++;
            }
            row++;
        }

//        return new HexMap(units, terrain, mapShape, textures);
        return new HexMap(null, terrain, mapShape, null);
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

}
