package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * A singleton class that produces configuration files.
 */
public class ConfigurationFactory {
    // Singleton instance
    private static ConfigurationFactory instance;

    private static final String PATH_TO_UNIT_CONFIGURATION = "configuration/units/";
    private static final String PATH_TO_TERRAIN_CONFIGURATION = "configuration/terrain/";
    private static final String PATH_TO_MAP_CONFIGURATION = "configuration/maps/";
    private static final String PATH_TO_UNIT_TEXTURE = "assets/units/";
    private static final String PATH_TO_TERRAIN_TEXTURE = "assets/terrain/";
    private static final String PATH_TO_SAVE_FILES = "saves/";

    private static final HashMap<String, HexMap> hexMapHashMap = new HashMap<>();
    private static final HashMap<String, TriggerProximity> triggerHashMap = new HashMap<>();
    private static final Gson GSON = new Gson();

    // Private constructor
    private ConfigurationFactory() {
    }

    // Singleton accessor method
    public static ConfigurationFactory getInstance() {
        if (instance == null) {
            synchronized (ConfigurationFactory.class) {
                if (instance == null) {
                    instance = new ConfigurationFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Returns the Terrain Object from the config file.
     *
     * @param config the pathname to the config file to create the Terrain from
     * @return the Terrain Object from the config file
     */
    public Terrain makeTerrainFromConfig(String config, String texture, int id) {
        return Terrain.getTerrainFromConfig(ConfigurationGetter.getConfiguration(PATH_TO_TERRAIN_CONFIGURATION + config), PATH_TO_TERRAIN_TEXTURE + texture, id);
    }

    public TriggerProximity makeTriggerFromConfig(String config) {
        if (!triggerHashMap.containsKey(config)) {
            triggerHashMap.put(config, TriggerProximity.getTriggerFromConfig(ConfigurationGetter.getConfiguration(config)));
        }
        return triggerHashMap.get(config);
    }

    /**
     * Returns the Unit Object from the config file.
     *
     * @param config the pathname to the config file to create the Unit from
     * @return the Unit Object from the config file
     */
    public BasicUnit makeUnitFromConfig(String config, int id, int pid, String texture) {
        return BasicUnit.getUnitFromConfig(ConfigurationGetter.getConfiguration(PATH_TO_UNIT_CONFIGURATION + config), id, pid, PATH_TO_UNIT_TEXTURE + texture);
    }

    /**
     * Returns the Hex Map Object from the config file.
     *
     * @param config the pathname to the config file to create the Hex Map from
     * @return the Hex Map Object from the config file
     */
    public HexMap makeHexMapFromConfig(String config) {
        if (!hexMapHashMap.containsKey(config)) {
            hexMapHashMap.put(config, HexMap.getHexMapFromConfig(ConfigurationGetter.getConfiguration(PATH_TO_MAP_CONFIGURATION + config)));
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

    /**
     * Returns the relative path to the save files.
     *
     * @return relative path to the save files
     */
    public String getPathToSaveFiles() {
        return PATH_TO_SAVE_FILES;
    }
}
