package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class BasicUnit implements Unit {
    private String type; //unit name
    private String description; //unit description
    private int maxHealth; //unit maximum health
    private int health; //unit current health
    private int strength; //base strength
    private int rangedStrength; //base ranged strength
    private int range; //unit's range of attack (if ranged unit)
    private int speed; //movement speed
    private UnitState state; //unit's current state
    private int id; //ID
    private int pid; //player ID
    private String texture; //unit texture

    private BasicUnit(String type, String description, int maxHealth, int strength, int rangedStrength, int range, int speed, int id, int pid, String texture) {
        this.type = type;
        this.description = description;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.speed = speed;
        this.health = maxHealth; //set the unit's health to its max health
        this.state = UnitState.normal; //set the unit's state to normal
        this.id = id;
        this.pid = pid;
        this.texture = texture;
    }

    //Copy constructor
    public BasicUnit(BasicUnit original) {
        type = original.type;
        description = original.description;
        maxHealth = original.maxHealth;
        strength = original.strength;
        rangedStrength = original.rangedStrength;
        range = original.range;
        speed = original.speed;
        health = original.health;
        state = original.state;
        id = original.id;
        pid = original.pid;
        texture = original.texture;
    }

    /**
     * Returns the Unit from the configuration file.
     *
     * @param config the file path
     * @return the Unit from the configuration file
     */
    public static BasicUnit getUnitFromConfig(String config, int id, int pid, String texture) {

        Gson gson = new Gson();
        JsonObject unit = gson.fromJson(config, JsonObject.class);

        String type = unit.get("type").getAsString();
        String description = unit.get("description").getAsString();
        int maxHealth = unit.get("maxHealth").getAsInt();
        int strength = unit.get("strength").getAsInt();
        int rangedStrength = unit.get("rangedStrength").getAsInt();
        int range = unit.get("range").getAsInt();
        int speed = unit.get("speed").getAsInt();

        return new BasicUnit(type, description, maxHealth, strength, rangedStrength, range, speed, id, pid, texture);
    }

    public List<UnitAction> getActions() {
        return null;
    }

    public boolean isCommandValid(Command command) {
        return true;
    }

    /**
     * Returns the state of the unit
     */
    public UnitState getUnitState() {
        return state;
    }

    /**
     * Sets the state of the unit to desired state
     */
    public void setUnitState(UnitState unitState) {
        state = unitState;
    }

    /**
     * Returns unit's type
     */
    public String getType() {
        return type;
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
    public int getMaxHealth() {
        return maxHealth;
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
    public int getRangedStrength() {
        return rangedStrength;
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

    public int getId(){
        return id;
    }

    /**
     * Returns unit's state
     */
    public UnitState getState() {
        return this.state;
    }

    /**
     * Changes the unit's health
     */
    public void setHealth(int new_health) {
        health = new_health;
    }

    /**
     * Changes the unit's max health
     */
    public void setMaxHealth(int new_max) {
        maxHealth = new_max;
    }

    /**
     * Changes the unit's strength
     */
    public void setStrength(int new_strength) {
        strength = new_strength;
    }

    /**
     * Changes the unit's ranged strength
     */
    public void setRangedStrength(int new_rangedStrength) {
        rangedStrength = new_rangedStrength;
    }

    /**
     * Changes the unit's range
     */
    public void setRange(int new_range) {
        range = new_range;
    }

    /**
     * Changes the unit's speed
     */
    public void setSpeed(int new_speed) {
        speed = new_speed;
    }

    /**
     * Changes the unit's state
     */
    public void setState(UnitState new_state) {
        state = new_state;
    }

    /**
     * Returns the unit's player ID
     */
    public int getPid() {
        return pid;
    }

    /**
     * Returns the unit's texture
     */
    public String getTexture(){
        return texture;
    }
}
