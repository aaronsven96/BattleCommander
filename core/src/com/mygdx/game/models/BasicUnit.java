package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicUnit implements Unit {
    private String name; //unit name
    private String unitType; //the type of unit
    private String description; //unit description
    private int max_health; //unit maximum health
    private int health; //unit current health
    private int strength; //base strength
    private int ranged_strength; //base ranged strength
    private int range; //unit's range of attack (if ranged unit)
    private int speed; //movement speed
    private UnitState state; //unit's current state

    private BasicUnit() {
        name="";
        unitType="";
        description="";
        max_health=0;
        health=0;
        strength=0;
        ranged_strength=0;
        range=1;
        speed=1;
        state=UnitState.normal;
    }

    private BasicUnit(String configuration) {
        String content = null;
        try {
            content = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No configuration file found for the Unit.");
        }
        Gson gson = new Gson();
        JsonObject unit;
        unit = gson.fromJson(content, JsonObject.class);

        name = unit.get("Name").getAsString();
        description = unit.get("Description").getAsString();
        max_health = unit.get("Max Health").getAsInt();
        strength = unit.get("Strength").getAsInt();
        ranged_strength = unit.get("Ranged Strength").getAsInt();
        range = unit.get("Range").getAsInt();
        speed = unit.get("Speed").getAsInt();

        health=max_health; //set the unit's health to its max health
        state=UnitState.normal; //set the unit's state to normal

    }

    /**
     * Returns the type of the unit
     */
    public String getType(){
        return unitType;
    };

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
     * Returns the Id of the unit
     */
    public int getUnitId(){
        return 0;
    };

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
