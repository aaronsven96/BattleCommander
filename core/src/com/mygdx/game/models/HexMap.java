package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

/**
 * A class that represents all the HexBoard layers.
 */
public class HexMap {
    private HexBoard<BasicUnit> units;
    private HexBoard<Terrain> terrain;
    private HexBoard<Boolean> mapShape;
    private List<HexBoard<String>> textures;
    private final int rows;
    private final int columns;
    private Set<Integer> randomIds = new HashSet<>();

    private HexMap(HexBoard<BasicUnit> units, HexBoard<Terrain> terrain, HexBoard<Boolean> mapShape, List<HexBoard<String>> textures, int rows, int columns) {
        this.units = units;
        this.rows = rows;
        this.columns = columns;
        this.terrain = terrain;
        this.mapShape = mapShape;
        this.textures = textures;
    }

    // Copy constructor
    public HexMap(HexMap original) {
        this.rows = original.rows;
        this.columns = original.columns;
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

        ConfigurationFactory cf = ConfigurationFactory.getInstance();
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
                String unitTexture = newUnit == null ? null : newUnit.getTexture(); // make the Basic Unit

                units.setHex(p, newUnit); // add BasicUnit to HexBoard<BasicUnit>

                unitTextures.setHex(p, unitTexture); // add texture to HexBoard<String>
            }
        }

        // Set up terrain
        for (int i = 0; i < jsonArrayTerrain.size(); i++) {
            JsonArray row = jsonArrayTerrain.get(i).getAsJsonArray();
            for (int j = 0; j < row.size(); j++) {
                JsonObject object = row.get(j).getAsJsonObject();

                String config = object.get("config").getAsString(); // "rock.json", "desert.json", etc.
                int id = object.get("id").getAsInt();
                String texture = object.get("texture").getAsString();

                Position p = new Position(i, j);

                Terrain newTerrain = config.equals("null") ? null : cf.makeTerrainFromConfig(config, texture, id); // make the Terrain
                String unitTexture = newTerrain == null ? null : newTerrain.getTexture(); // make the Basic Unit

                terrain.setHex(p, newTerrain); // add Terrain to HexBoard<Terrain>

                terrainTextures.setHex(p, unitTexture); // add texture to HexBoard<String>
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
        textures.add(terrainTextures);
        textures.add(unitTextures);


        return new HexMap(units, terrain, mapShape, textures, rows, columns);
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
     * Returns the number of columns in the map.
     *
     * @return the number of columns in the map
     */
    public int getNumColumns() {
        return columns;
    }

    /**
     * Returns the number of rows in the map.
     *
     * @return the number of rows in the map
     */
    public int getNumRows() {
        return rows;
    }

    /**
     * Returns the Terrain HexBoard.
     *
     * @return the Terrain HexBoard
     */
    public HexBoard<Terrain> getTerrain() {
        return terrain;
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
     * Returns the BasicUnits HexBoard.
     *
     * @return the BasicUnits HexBoard
     */
    public HexBoard<BasicUnit> getUnits() {
        return units;
    }

    /**
     * Returns the BasicUnit at a position.
     *
     * @param p the position
     * @return the BasicUnits at a position
     */
    public Optional<BasicUnit> getUnit(Position p) {
        return units.getHex(p);
    }

    /**
     * Returns all BasicUnits for a specific player ID.
     *
     * @param pid the player ID
     * @return all units for a specific player ID
     */
    public List<BasicUnit> getUnitsForPlayer(int pid) {  // TODO: testing!
        BasicUnit unitAtHex;
        List<BasicUnit> unitsForPlayer = new ArrayList<>();

        for (int i = 0; i < units.getNumRows(); i++) {
            for (int j = 0; j < units.getNumColumns(); j++) {
                Optional<BasicUnit> optional = units.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    unitAtHex = optional.get();
                    if (unitAtHex.getPid() == pid) {
                        unitsForPlayer.add(unitAtHex);
                    }
                }
            }
        }
        return unitsForPlayer;
    }

    /**
     * Returns true if the IDs are in proximity, false otherwise
     *
     * @param id1   the first ID
     * @param id2   the second ID
     * @param range the range
     * @return true if the IDs are in proximity, false otherwise
     */
    public boolean isInProximity(int id1, int id2, int range) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < getUnits().getNumRows(); i++) {
            for (int j = 0; j < getUnits().getNumColumns(); j++) {
                Position p = new Position(i, j);
                Optional<BasicUnit> optional = getUnits().getHex(p);
                if (optional.isPresent()) {
                    BasicUnit bu = optional.get();
                    if (bu.getId() == id1) {
                        positions.add(p);
                    }
                    if (bu.getId() == id2) {
                        positions.add(p);
                    }
                }
            }
            if (positions.size() == 2) {
                return getUnits().isInProximity(positions.get(0), positions.get(1), range);
            }
        }

        for (int i = 0; i < getTerrain().getNumRows(); i++) {
            for (int j = 0; j < getTerrain().getNumColumns(); j++) {
                Position p = new Position(i, j);
                Optional<Terrain> optional = getTerrain().getHex(p);
                if (optional.isPresent()) {
                    Terrain t = optional.get();
                    if (t.getId() == id1) {
                        positions.add(p);
                    }
                    if (t.getId() == id2) {
                        positions.add(p);
                    }
                }
            }
            if (positions.size() == 2) {
                return getTerrain().isInProximity(positions.get(0), positions.get(1), range);
            }
        }

        return false;
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     */
    public void save(boolean randomizeIds) {
        String filename = new SimpleDateFormat("yyyyMMdd_HHmm_ssSS'.json'").format(new Date()); // e.g., 20200215_1723_30397.json
        save(filename, randomizeIds, 1000000000);
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     */
    public void save(String filename, boolean randomizeIds) {
        save(filename, randomizeIds, 1000000000);
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     */
    public void save(String filename, boolean randomizeIds, int upperBound) {
        Map<String, Object> hexMap = new LinkedHashMap<>(); // Map -> JSON String -> JSON file
        Set<Integer> randomIds = new HashSet<>();
        hexMap.put("rows", getNumRows());
        hexMap.put("columns", getNumColumns());

        Random r = new Random();
        int newId;

        // Construct "units" Array
        Map[][] buArr = new Map[getNumRows()][getNumColumns()];
        BasicUnit unitAtHex;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                Optional<BasicUnit> optional = units.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    unitAtHex = optional.get();
                    Map<String, Object> newUnit = new LinkedHashMap<>();

                    String config = unitAtHex.getType().toLowerCase() + ".json";
                    int index = unitAtHex.getTexture().lastIndexOf("/");
                    String unitTexture = unitAtHex.getTexture().substring(index + 1);

                    newUnit.put("config", config);
                    if (randomizeIds) {
                        do {
                            newId = r.nextInt(upperBound);
                        } while (randomIds.contains(newId));
                        randomIds.add(newId);
                        newUnit.put("id", newId);
                    } else {
                        newUnit.put("id", unitAtHex.getId());
                    }
                    newUnit.put("pid", unitAtHex.getPid());
                    newUnit.put("texture", unitTexture);

                    buArr[i][j] = newUnit;
                }
            }
        }
        hexMap.put("units", buArr); // add "units" Array to Map

        // Construct "terrain" Array
        Map[][] terrainArr = new Map[getNumRows()][getNumColumns()];
        Terrain terrainAtHex;
        for (
                int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                Optional<Terrain> optional = terrain.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    terrainAtHex = optional.get();
                    Map<String, Object> newTerrain = new LinkedHashMap<>();

                    String config = terrainAtHex.getType().toLowerCase() + ".json";
                    int index = terrainAtHex.getTexture().lastIndexOf("/");
                    String terrainTexture = terrainAtHex.getTexture().substring(index + 1);

                    newTerrain.put("config", config);
                    if (randomizeIds) {
                        do {
                            newId = r.nextInt(upperBound);
                        } while (randomIds.contains(newId));
                        randomIds.add(newId);
                        newTerrain.put("id", newId);
                    } else {
                        newTerrain.put("id", terrainAtHex.getId());
                    }
                    newTerrain.put("texture", terrainTexture);

                    terrainArr[i][j] = newTerrain;
                }
            }
        }
        hexMap.put("terrain", terrainArr); // add "terrain" Array to Map

        // Construct "mapShape" Array
        Boolean[][] mapShapeArr = new Boolean[getNumRows()][getNumColumns()];
        Boolean mapShapeAtHex;
        for (
                int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                Optional<Boolean> optional = mapShape.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    mapShapeAtHex = optional.get();
                    mapShapeArr[i][j] = mapShapeAtHex;
                }
            }
        }
        hexMap.put("mapShape", mapShapeArr); // add "mapShape" Array to Map

        String location = "configuration/saves/"; // TODO: get path from Configuration Factory?
        FileHandle file = Gdx.files.local(location + filename);

        Gson gson = new Gson();
        String json = gson.toJson(hexMap); // convert Map to JSON String

        file.writeString(json, false); // write String to JSON file
    }
}