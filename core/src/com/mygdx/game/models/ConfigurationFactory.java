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

    private HashMap<String, Terrain> terrainHashMap;
    private HashMap<String, HexMap> hexMapHashMap;
    private HashMap<String, BasicUnit> unitsHashMap;

    private static final Gson GSON = new Gson();

    /**
     * Returns the Terrain Object from the config file.
     *
     * @param config the pathname to the config file to create the Terrain from
     * @return the Terrain Object from the config file
     */
    public Terrain makeTerrainFromConfig(String config) {
        if (!terrainHashMap.containsKey(config)) {
            terrainHashMap.put(config, Terrain.getTerrainFromConfig(config));
        }
        return terrainHashMap.get(config);
    }

    /**
     * Returns the Unit Object from the config file.
     *
     * @param config the pathname to the config file to create the Unit from
     * @return the Unit Object from the config file
     */
    public Unit makeUnitFromConfig(String config) {
        if (!unitsHashMap.containsKey(config)) {
            unitsHashMap.put(config, BasicUnit.getUnitFromConfig(config);
        }
        return unitsHashMap.get(config);
    }

    /**
     * Returns the Hex Map Object from the config file.
     *
     * @param config the pathname to the config file to create the Hex Map from
     * @return the Hex Map Object from the config file
     */
    public HexMap makeHexMapFromConfig(String config) {
        if (!hexMapHashMap.containsKey(config)) {
            hexMapHashMap.put(config, HexMap.getHexMapFromConfig(config));
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
