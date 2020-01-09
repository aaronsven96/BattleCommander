package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;

public class ConfigurationGetter {

    private ConfigurationGetter(){}

    private static String getConfiguration(String path){
        return Gdx.files.internal(path).readString();
    }
}
