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

    private ConfigurationFactory(){}

    private HashMap<String, Terrain> terrainHashMap;

    private static final Gson GSON = new Gson();

    /**
     *
     * @param config the pathname to the config file to create the terrain from
     * @return the Terrain Object
     */
    public Terrain makeTerrainFromConfig(String config) {
        if (!terrainHashMap.containsKey(config)) {
            terrainHashMap.put(config, Terrain.getTerrainFromConfig(config));
        }
        return terrainHashMap.get(config);
    }

    /**
     *
     * @param config the pathname to the config file to create the terrain from
     * @return the Unit Object
     */
    public Unit makeUnitFromConfig(String config){
        return null;
    }

    public HexMap makeMapFromConfig(String config){
        return null;
    }

    public UnitAction getActionFromConfig(String configFile){
        String config = ConfigurationGetter.getConfiguration(configFile);
        JsonObject object = GSON.fromJson(config, JsonObject.class);
        Action action = Action.valueOf(object.get("actionType").getAsString());
        switch (action){
            case SUPPORT:
            case MOVE:
            case ATTACK:
        }

        return null;
    }
}
