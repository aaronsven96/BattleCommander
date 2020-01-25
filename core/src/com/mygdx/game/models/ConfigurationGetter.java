package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;

import java.util.Scanner;

public class ConfigurationGetter {

    public ConfigurationGetter(){}

    public static String getConfiguration(String path){
        return Gdx.files.internal(path).readString();
    }
}
