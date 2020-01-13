package com.mygdx.game.models;

import java.util.HashMap;

/**
 * A singleton class that produces configuration files
 */
public class ConfigurationFactory {

    //Singleton instance
    public static ConfigurationFactory instance = new ConfigurationFactory();

    private ConfigurationFactory(){}

    private HashMap<String, Terrain> terrainHashMap;

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

}
