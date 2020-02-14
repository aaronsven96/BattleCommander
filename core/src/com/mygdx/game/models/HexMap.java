package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HexMap {
    private HexBoard<BasicUnit> units;
    private HexBoard<Terrain> terrain;
    private HexBoard<Boolean> mapShape;
    private List<HexBoard<String>> textures;

    private HexMap(HexBoard<BasicUnit> units, HexBoard<Terrain> terrain, HexBoard<Boolean> mapShape, List<HexBoard<String>> textures) {
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

        HexBoard<BasicUnit> units = new HexBoard<>(rows, columns);
        HexBoard<Terrain> terrain = new HexBoard<>(rows, columns);
        HexBoard<Boolean> mapShape = new HexBoard<>(rows, columns);
        List<HexBoard<String>> textures = null;

        ConfigurationFactory cf = ConfigurationFactory.instance;
        JsonArray jsonArrayUnits = hexMap.get("units").getAsJsonArray();
        JsonArray jsonArrayTerrain = hexMap.get("terrain").getAsJsonArray();
        JsonArray jsonArrayMapShape1 = hexMap.get("mapShape").getAsJsonArray();

        // TODO: convert loops from for-each to for?
        // Set up units
        int row = 0;
        int column = 0;
        for (JsonElement j : jsonArrayUnits) {
            String config = j.getAsJsonObject().get("config").getAsString(); // "archer.json", "null", etc.
            int id = j.getAsJsonObject().get("id").getAsInt();
            int pid = j.getAsJsonObject().get("pid").getAsInt();
            String texture = j.getAsJsonObject().get("texture").getAsString();

            BasicUnit newUnit = config.equals("null") ? null : cf.makeUnitFromConfig(config, id, pid, texture); // make the Basic Unit

            units.setHex(new Position(row, column), newUnit); // set up the HexBoard

            column++;
            if (column == columns) {
                column = 0;
                row++;
            }
        }

        // Set up terrain
        row = 0;
        column = 0;
        for (JsonElement j : jsonArrayTerrain) {
            String config = j.getAsJsonObject().get("config").getAsString(); // "swamp.json", "desert.json", etc.
            String texture = j.getAsJsonObject().get("texture").getAsString();
            int id = j.getAsJsonObject().get("id").getAsInt();

            Terrain newTerrain = config.equals("null") ? null : cf.makeTerrainFromConfig(config, texture, id); // make the Terrain

            terrain.setHex(new Position(row, column), newTerrain); // set up the HexBoard

            column++;
            if (column == columns) {
                column = 0;
                row++;
            }
        }

        // Set up mapShape
        row = 0;
        column = 0;
        for (JsonElement j : jsonArrayMapShape1) { // outer Array
            JsonArray jsonArrayMapShape2 = j.getAsJsonArray(); // inner Array
            for (JsonElement k : jsonArrayMapShape2) {
                String text = k.getAsString(); // "true", "false"

                mapShape.setHex(new Position(row, column), Boolean.parseBoolean(text)); // set up the HexBoard

                column++;
            }
            if (column == columns) {
                column = 0;
            }
            row++;
        }

        return new HexMap(units, terrain, mapShape, textures);
    }

    /**
     * Get the units at a position P
     */
    public Optional<BasicUnit> getUnit(Position p) {
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
    public List<BasicUnit> getUnitsForPlayer(int pid) {  // TODO: testing!
        BasicUnit unitsAtHex;
        List<BasicUnit> unitsForPlayer = new ArrayList<>();

        for (int i = 0; i < units.getNumRows(); i++) {
            for (int j = 0; j < units.getNumColumns(); j++) {
                Optional<BasicUnit> optional = units.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    unitsAtHex = optional.get();
                    if (unitsAtHex.getPid() == pid) {
                        unitsForPlayer.add(unitsAtHex);
                    }
                }
            }
        }

        return unitsForPlayer;
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

    public HexBoard<BasicUnit> getUnits() {
        return units;
    }
}