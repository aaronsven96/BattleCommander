package com.mygdx.game.models;

import java.util.HashMap;

/**
 * A singleton class that produces configuration files
 */
public class ConfigurationFactory {

    //Singleton instance
    public static ConfigurationFactory instance = new ConfigurationFactory();

    private ConfigurationFactory(){}

    private HashMap<String, TerrainClass> terrainHashMap;

    /**
     *
     * @param Config the pathname to the config file to create the terrain from
     * @return the Terrain Object
     */
    public TerrainClass makeTerrainFromConfig(String config) {
        return terrainHashMap.containsKey(config) ? terrainHashMap.get(config) : TerrainClass.getTerrainFromConfig(config);
    }

    /**
     *
     * @param Config the pathname to the config file to create the terrain from
     * @return the Unit Object
     */
    public Unit makeUnitFromConfig(String Config){
        return null;
    }
}
