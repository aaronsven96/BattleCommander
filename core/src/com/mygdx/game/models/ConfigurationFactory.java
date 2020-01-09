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
     * @param Config the pathname to the config file to create the terrain from
     * @return the Terrain Object
     */
    public Terrain makeTerrainFromConfig(String Config){
        return null;
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
