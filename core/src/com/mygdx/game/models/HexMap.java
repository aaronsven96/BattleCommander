package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import lombok.Getter;

/**
 * A class that represents all the HexBoard layers.
 */

@Getter
public class HexMap implements Serializable {
    private static int turnGenerator; // used to update turn number
    private final int rows; // number of rows
    private final int columns; // number of columns

    private HexBoard<BasicUnit> units;
    private HexBoard<Terrain> terrain;
    private HexBoard<Boolean> mapShape; // HexBoard of active tiles
    private List<HexBoard<String>> textures; // contains HexBoards of texture filenames for BasicUnits (zeroth position) and Terrain (first position)

    private Set<Integer> ids; // contains all BasicUnit and Terrain IDs in the Map
    private Map<Integer, Position> idToPosition; // {ID=Position, ...}

    private int turn; // turn number

    private HexMap(HexBoard<BasicUnit> units, HexBoard<Terrain> terrain, HexBoard<Boolean> mapShape, List<HexBoard<String>> textures, int rows, int columns, Set<Integer> ids, Map<Integer, Position> idToPosition) {
        this.units = units;
        this.rows = rows;
        this.columns = columns;
        this.terrain = terrain;
        this.mapShape = mapShape;
        this.textures = textures;
        this.ids = ids;
        this.idToPosition = idToPosition;
        this.turn = turnGenerator;
        turnGenerator++;
    }

    // Copy constructor
    public HexMap(HexMap original) {
        rows = original.rows;
        columns = original.columns;
        units = original.units;
        terrain = original.terrain;
        mapShape = original.mapShape;
        textures = original.textures;
        ids = original.ids;
        turn = original.turn;
        idToPosition = original.idToPosition;
    }

    /**
     * Returns the HexMap from the configuration file.
     *
     * @param content the config
     * @return the HexMap from the configuration file.
     */
    public static HexMap getHexMapFromConfig(String content) {
        Gson gson = new Gson();

        JsonObject hexMap = gson.fromJson(content, JsonObject.class);

        int turn = hexMap.get("turn").getAsInt();
        turnGenerator = turn;
        int rows = hexMap.get("rows").getAsInt();
        int columns = hexMap.get("columns").getAsInt();
        Set<Integer> ids = new HashSet<>();

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
                ids.add(id);
                int pid = object.get("pid").getAsInt();
                String texture = object.get("texture").getAsString();

                Position p = new Position(i, j);

                BasicUnit newUnit = config.equals("null") ? null : cf.makeUnitFromConfig(config, id, pid, texture); // make the Basic Unit
                String unitTexture = newUnit == null ? null : newUnit.getTexture();

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
                ids.add(id);
                String texture = object.get("texture").getAsString();

                Position p = new Position(i, j);

                Terrain newTerrain = config.equals("null") ? null : cf.makeTerrainFromConfig(config, texture, id); // make the Terrain
                String terrainTexture = newTerrain == null ? null : newTerrain.getTexture();

                terrain.setHex(p, newTerrain); // add Terrain to HexBoard<Terrain>

                terrainTextures.setHex(p, terrainTexture); // add texture to HexBoard<String>
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

        // Set up idToPosition, which maps each unique ID to a HexBoard Position
        Map<Integer, Position> idToPosition = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Position p = new Position(i, j);

                Optional<BasicUnit> buOpt = units.getHex(p);
                if (buOpt.isPresent()) {
                    BasicUnit bu = buOpt.get();
                    int buId = bu.getId();
                    idToPosition.put(buId, p);
                }

                Optional<Terrain> tOpt = terrain.getHex(p);
                if (tOpt.isPresent()) {
                    Terrain t = tOpt.get();
                    int tId = t.getId();
                    idToPosition.put(tId, p);
                }
            }
        }

        return new HexMap(units, terrain, mapShape, textures, rows, columns, ids, idToPosition);
    }

    // TODO: add interactions to the game
    // Interaction getInteraction(Position p);

    /**
     * Returns the position (y,x) of a given ID.
     *
     * @param id the ID
     * @return the Position of a given ID
     */
    public Optional<Position> getPositionById(int id) {
        if (idToPosition.containsKey(id)) {
            return Optional.of(idToPosition.get(id));
        }
        return Optional.empty();
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
     * Returns the BasicUnit at a position.
     *
     * @param p the position
     * @return the BasicUnit at a position
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
    public List<BasicUnit> getUnitsForPlayer(int pid) {  // TODO: testing
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
        if (idToPosition.containsKey(id1) && idToPosition.containsKey(id2)) {
            return units.isInProximity(idToPosition.get(id1), idToPosition.get(id2), range);
        }
        return false;
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     */
    public void save() {
        String filename = new SimpleDateFormat("yyyyMMdd_HHmm_ssSS'.json'").format(new Date()); // e.g., 20200215_1723_30397.json
        save(filename, false, Integer.MAX_VALUE);
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     *
     * @param filename the filename
     */
    public void save(String filename) {
        save(filename, false, Integer.MAX_VALUE);
    }

    /**
     * Set a Terrain at a position (y,x) on the board.
     *
     * @param p       the position
     * @param config  the config file
     * @param texture the texture
     */
    public void setTerrain(Position p, String config, String texture) {
        int id = getRandomId();

        ConfigurationFactory cf = ConfigurationFactory.getInstance();
        Terrain newTerrain = config.equals("null") ? null : cf.makeTerrainFromConfig(config, texture, id); // make the Terrain
        String terrainTexture = newTerrain == null ? null : newTerrain.getTexture();

        terrain.setHex(p, newTerrain); // add Terrain to HexBoard<Terrain>

        textures.get(1).setHex(p, terrainTexture); // add terrain texture to HexBoard<String> at first position
    }

    /**
     * Set a Unit at a position (y,x) on the board.
     *
     * @param p       the position
     * @param config  the config file
     * @param texture the texture
     * @param pid     the player ID
     */
    public void setUnit(Position p, String config, int pid, String texture) {
        int id = getRandomId();

        ConfigurationFactory cf = ConfigurationFactory.getInstance();
        BasicUnit newUnit = config.equals("null") ? null : cf.makeUnitFromConfig(config, id, pid, texture); // make the Basic Unit
        String unitTexture = newUnit == null ? null : newUnit.getTexture();

        units.setHex(p, newUnit); // add BasicUnit to HexBoard<BasicUnit>

        textures.get(0).setHex(p, unitTexture); // add unit texture to HexBoard<String> at zeroth position
    }

    /**
     * Returns a random ID from 0 to ‭4,294,967,296 (exclusive)‬.
     *
     * @return a random ID from 0 to ‭4,294,967,296 (exclusive)
     */
    private int getRandomId() {
        Random r = new Random();
        int newId;

        do {
            newId = r.nextInt();
        } while (ids.contains(newId));
        ids.add(newId);

        return newId;
    }

    /**
     * Saves the HexMap as a JSON file on the disk.
     *
     * @param filename     the filename
     * @param randomizeIds if true, randomize all ID numbers in the save file
     * @param upperBound   the upper bound (exclusive) on the number generator
     */
    private void save(String filename, boolean randomizeIds, int upperBound) {
        Map<String, Object> hexMap = new LinkedHashMap<>(); // Map -> JSON String -> JSON file
        hexMap.put("turn", getTurn());
        hexMap.put("rows", getRows());
        hexMap.put("columns", getColumns());

        // Construct "units" Array
        Map[][] buArr = new Map[getRows()][getColumns()];
        BasicUnit unitAtHex;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                Optional<BasicUnit> optional = units.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    unitAtHex = optional.get();
                    Map<String, Object> newUnit = new LinkedHashMap<>();

                    String config = unitAtHex.getType().toLowerCase() + ".json";
                    int index = unitAtHex.getTexture().lastIndexOf("/");
                    String unitTexture = unitAtHex.getTexture().substring(index + 1);

                    newUnit.put("config", config);
                    if (randomizeIds) {
                        int newId = getRandomId();
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
        Map[][] terrainArr = new Map[getRows()][getColumns()];
        Terrain terrainAtHex;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                Optional<Terrain> optional = terrain.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    terrainAtHex = optional.get();
                    Map<String, Object> newTerrain = new LinkedHashMap<>();

                    String config = terrainAtHex.getType().toLowerCase() + ".json";
                    int index = terrainAtHex.getTexture().lastIndexOf("/");
                    String terrainTexture = terrainAtHex.getTexture().substring(index + 1);

                    newTerrain.put("config", config);
                    if (randomizeIds) {
                        int newId = getRandomId();
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
        Boolean[][] mapShapeArr = new Boolean[getRows()][getColumns()];
        Boolean mapShapeAtHex;
        for (
                int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                Optional<Boolean> optional = mapShape.getHex(new Position(i, j));
                if (optional.isPresent()) {
                    mapShapeAtHex = optional.get();
                    mapShapeArr[i][j] = mapShapeAtHex;
                }
            }
        }
        hexMap.put("mapShape", mapShapeArr); // add "mapShape" Array to Map

        String location = ConfigurationFactory.getInstance().getPathToSaveFiles();
        FileHandle file = Gdx.files.local(location + filename);

        Gson gson = new Gson();
        String json = gson.toJson(hexMap); // convert Map to JSON String

        file.writeString(json, false); // write String to JSON file
    }
}