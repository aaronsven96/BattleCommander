package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * A singleton class that produces configuration files
 */
public class ConfigurationFactory {

    //Singleton instance
    public static ConfigurationFactory instance = new ConfigurationFactory();

    private ConfigurationFactory() {
    }

    private static final HashMap<String, Terrain> terrainHashMap = new HashMap<>();
    private static final HashMap<String, HexMap> hexMapHashMap = new HashMap<>();
    private static final HashMap<String, Unit> unitsHashMap = new HashMap<>();

    private static final Gson GSON = new Gson();

    /**
     * Returns the Terrain Object from the config file.
     *
     * @param config the pathname to the config file to create the Terrain from
     * @return the Terrain Object from the config file
     */
    public Terrain makeTerrainFromConfig(String config, String texture, int id) {
//        if (!terrainHashMap.containsKey(config)) {
//            terrainHashMap.put(config, Terrain.getTerrainFromConfig(ConfigurationGetter.getConfiguration(config)));
//        }
//        return terrainHashMap.get(config);
        return Terrain.getTerrainFromConfig(ConfigurationGetter.getConfiguration(config), texture, id);
    }

    /**
     * Returns the Unit Object from the config file.
     *
     * @param config the pathname to the config file to create the Unit from
     * @return the Unit Object from the config file
     */
    public Unit makeUnitFromConfig(String config, int id, int pid) {
//        if (!unitsHashMap.containsKey(config)) {
//            unitsHashMap.put(config, BasicUnit.getUnitFromConfig(ConfigurationGetter.getConfiguration(config)));
//        }
//        return unitsHashMap.get(config);
        return BasicUnit.getUnitFromConfig(ConfigurationGetter.getConfiguration(config), id, pid);
    }

    /**
     * Returns the Hex Map Object from the config file.
     *
     * @param config the pathname to the config file to create the Hex Map from
     * @return the Hex Map Object from the config file
     */
    public HexMap makeHexMapFromConfig(String config) {
        if (!hexMapHashMap.containsKey(config)) {
            hexMapHashMap.put(config, HexMap.getHexMapFromConfig(ConfigurationGetter.getConfiguration(config)));
        }
        return hexMapHashMap.get(config);
    }

    public UnitAction getActionFromConfig(String configFile) {
        String config = ConfigurationGetter.getConfiguration(configFile);
        JsonObject object = GSON.fromJson(config, JsonObject.class);
        Action action = Action.valueOf(object.get("actionType").getAsString());
        switch (action) {
            case SUPPORT:
            case MOVE:
            case ATTACK:
        }

        return null;
    }
}
