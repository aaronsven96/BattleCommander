package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * A class that represents all the HexBoard layers.
 */
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

        List<HexBoard<String>> textures = new ArrayList<>();
        HexBoard<String> unitTextures = new HexBoard<>(rows, columns);
        HexBoard<String> terrainTextures = new HexBoard<>(rows, columns);

        ConfigurationFactory cf = ConfigurationFactory.instance;
        JsonArray jsonArrayUnits = hexMap.get("units").getAsJsonArray();
        JsonArray jsonArrayTerrain = hexMap.get("terrain").getAsJsonArray();
        JsonArray jsonArrayMapShape = hexMap.get("mapShape").getAsJsonArray();

        // Set up units
        for (int i = 0; i < jsonArrayUnits.size(); i++) {
            JsonArray row = jsonArrayUnits.get(i).getAsJsonArray();
            for (int j = 0; j < row.size(); j++) {
                JsonObject object = row.get(j).getAsJsonObject();

                String config = object.get("config").getAsString(); // "archer.json", "null", etc.
                int id = object.get("id").getAsInt();
                int pid = object.get("pid").getAsInt();
                String texture = object.get("texture").getAsString();

                Position p = new Position(i, j);

                BasicUnit newUnit = config.equals("null") ? null : cf.makeUnitFromConfig(config, id, pid, texture); // make the Basic Unit
                units.setHex(p, newUnit); // add BasicUnit to HexBoard<BasicUnit>

                unitTextures.setHex(p, texture); // add texture to HexBoard<String>
            }
        }

        // Set up terrain
        for (int i = 0; i < jsonArrayTerrain.size(); i++) {
            JsonArray row = jsonArrayTerrain.get(i).getAsJsonArray();
            for (int j = 0; j < row.size(); j++) {
                JsonObject object = row.get(j).getAsJsonObject();

                String config = object.get("config").getAsString(); // "swamp.json", "desert.json", etc.
                int id = object.get("id").getAsInt();
                String texture = object.get("texture").getAsString();

                Position p = new Position(i, j);

                Terrain newTerrain = config.equals("null") ? null : cf.makeTerrainFromConfig(config, texture, id); // make the Terrain
                terrain.setHex(p, newTerrain); // add Terrain to HexBoard<Terrain>

                terrainTextures.setHex(p, texture); // add texture to HexBoard<String>
            }
        }

        // Set up mapShape
        for (int i = 0; i < jsonArrayMapShape.size(); i++) {
            JsonArray row = jsonArrayMapShape.get(i).getAsJsonArray();
            for (int j = 0; j < row.size(); j++) {
                String text = row.get(j).getAsString(); // "true", "false"
                mapShape.setHex(new Position(i, j), Boolean.parseBoolean(text)); // add Boolean to position on the HexBoard
            }
        }

        // Set up textures
        textures.add(unitTextures);
        textures.add(terrainTextures);

        return new HexMap(units, terrain, mapShape, textures);
    }

    // TODO: add interactions to the game
    // Interaction getInteraction(Position p);

    /**
     * Returns a HexMap of Booleans which describes which tiles are in the map.
     *
     * @return a HexMap of Booleans which describes which tiles are in the map.
     */
    public HexBoard<Boolean> getMapShape() {
        return mapShape;
    }

    /**
     * Returns the Terrain at a position.
     *
     * @param p the position
     * @return the Terrain at a position
     */
    public Optional<Terrain> getTerrain(Position p) {
        return terrain.getHex(p);
    }

    /**
     * Returns a List of Strings which refer to the file location of the textures in each layer of the HexMap
     *
     * @return a List of Strings which refer to the file location of the textures in each layer of the HexMap
     */
    public List<HexBoard<String>> getTextures() {
        return textures;
    }

    /**
     * Returns the BasicUnits at a position.
     *
     * @param p the position
     * @return the BasicUnits at a position
     */
    public Optional<BasicUnit> getUnit(Position p) {
        return units.getHex(p);
    }

    /**
     * Returns all units for a specific player id.
     *
     * @param pid the player id
     * @return all units for a specific player id
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
     * Saves the HexMap as a file on the disk.
     *
     * @param filename the filename
     */
    public void save(String filename) {
        Gson gson = new Gson();

        FileHandle file = Gdx.files.local(filename);

        String json = gson.toJson(this);

        file.writeString(json, false);
    }

    public HexBoard<BasicUnit> getUnits() {
        return units;
    }

    public HexBoard<Terrain> getTerrain() {
        return terrain;
    }
}