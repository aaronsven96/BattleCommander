package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicUnit implements Unit {
    private String name; //unit name
    private String description; //unit description
    private int max_health; //unit maximum health
    private int health; //unit current health
    private int strength; //base strength
    private int ranged_strength; //base ranged strength
    private int range; //unit's range of attack (if ranged unit)
    private int speed; //movement speed
    private UnitState state; //unit's current state

    private BasicUnit(String name, String description, int max_health, int strength, int ranged_strength, int range, int speed) {
        this.name=name;
        this.description=description;
        this.max_health=max_health;
        this.strength=strength;
        this.ranged_strength=ranged_strength;
        this.range=range;
        this.speed=speed;

        this.health=max_health; //set the unit's health to its max health
        this.state=UnitState.normal; //set the unit's state to normal
    }

    //Copy constructor
    public BasicUnit(BasicUnit original) {
        name=original.name;
        description=original.description;
        max_health=original.max_health;
        strength=original.strength;
        ranged_strength=original.ranged_strength;
        range=original.range;
        speed=original.speed;
        health=original.health;
        state=original.state;
    }

    /**
     * Returns the Unit from the configuration file.
     *
     * @param filename the filename
     * @return the Unit from the configuration file
     */
    public static BasicUnit getUnitFromConfig(String filename) {
        FileHandle file = Gdx.files.internal(filename);
        String content = file.readString();

        Gson gson = new Gson();
        JsonObject unit = gson.fromJson(content, JsonObject.class);

        String name = unit.get("Name").getAsString();
        String description = unit.get("Description").getAsString();
        int max_health = unit.get("Max Health").getAsInt();
        int strength = unit.get("Strength").getAsInt();
        int ranged_strength = unit.get("Ranged Strength").getAsInt();
        int range = unit.get("Range").getAsInt();
        int speed = unit.get("Speed").getAsInt();

        return new BasicUnit(name, description, max_health, strength, ranged_strength, range, speed);
    }

    /**
     * Returns the state of the unit
     */
    public UnitState getUnitState(){
        return state;
    };

    /**
     * Sets the state of the unit to desired state
     */
    public void setUnitState(UnitState unitState){
        state = unitState;
    };

    /**
     * Returns unit's name
     */
    public String getName() {
        return name;
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
    public void setState ( UnitState new_state ) {
        state = new_state;
    }
}
