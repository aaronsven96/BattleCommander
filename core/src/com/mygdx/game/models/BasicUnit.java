package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicUnit {
    String name; //unit name
    String unitType; //the type of unit
    String description; //unit description
    int max_health; //unit maximum health
    int health; //unit current health
    int strength; //base strength
    int ranged_strength; //base ranged strength
    int range; //unit's range of attack (if ranged unit)
    int speed; //movement speed
    UnitState state; //unit's current state

    public BasicUnit() {
        max_health=0;
        health=0;
        strength=0;
        ranged_strength=0;
        range=1;
        speed=1;
        state=UnitState.normal;

    }

    public BasicUnit(String configuration) {
        String content = null;
        try {
            content = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No configuration file found for the Unit.");
        }
        Gson gson = new Gson();
        JsonObject unit;
        unit = gson.fromJson(content, JsonObject.class);
        unit.get("Speed").getAsInt();

        health=max_health; //set the unit's health to its max health

    }

    /**
     * Returns unit's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns unit's type
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Returns unit's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns unit's current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns unit's current maximum health
     */
    public int getMax_health() {
        return max_health;
    }

    /**
     * Returns unit's strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Returns unit's ranged strength
     */
    public int getRanged_strength() {
        return ranged_strength;
    }

    /**
     * Returns unit's range
     */
    public int getRange() {
        return range;
    }

    /**
     * Returns unit's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Returns unit's state
     */
    public UnitState getState() {
        return state;
    }

    /**
     * Changes the unit's health
     */
    public void setHealth( int new_health ) {
        health = new_health;
    }

    /**
     * Changes the unit's max health
     */
    public void setMax_health( int new_max ) {
        max_health = new_max;
    }

    /**
     * Changes the unit's strength
     */
    public void setStrength( int new_strength ) {
        strength = new_strength;
    }

    /**
     * Changes the unit's ranged strength
     */
    public void setRanged_strength( int new_rangedStrength ) {
        ranged_strength = new_rangedStrength;
    }

    /**
     * Changes the unit's range
     */
    public void setRange( int new_range ) {
        range = new_range;
    }

    /**
     * Changes the unit's speed
     */
    public void setSpeed( int new_speed ) {
        speed = new_speed;
    }

    /**
     * Changes the unit's state
     */
    public void setState ( int new_state_ ) {
        state = new_state;
    }
}
