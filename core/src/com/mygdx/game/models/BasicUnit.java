package com.mygdx.game.models;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicUnit {
    private int health; //unit health
    private int strength; //base strength
    private int ranged_strength; //base ranged strength
    private int speed; //movement speed
    String unitType; //the type of unit


    public BasicUnit() {
        health=0;
        strength=0;
        ranged_strength=0;
        speed=1;
    }

    public BasicUnit(String configuration) {
        String content = null;
        try {
            content = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No configuration file found for the Unit.");
        }
        Gson gson = new Gson();
    }
}
