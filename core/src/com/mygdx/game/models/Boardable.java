package com.mygdx.game.models;

/**
 * An interface that denotes that an object can be loaded from a configuration file.
 */
public interface Boardable {

    /**
     * A method that allows you to get an object from Configuration file
     */
    public Boardable getObjectFromConfiguration(String filename);
}
