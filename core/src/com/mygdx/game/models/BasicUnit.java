package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicUnit implements Unit {
    private String type; //unit name
    private String description; //unit description
    private int maxHealth; //unit maximum health
    private int health; //unit current health
    private int strength; //base strength
    private int rangedStrength; //base ranged strength
    private int range; //unit's range of attack (if ranged unit)
    private int speed; //movement speed
    private UnitState unitState; //unit's current state
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
        this.unitState = UnitState.normal; //set the unit's state to normal
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
        unitState = original.unitState;
        id = original.id;
        pid = original.pid;
        texture = original.texture;
    }

    /**
     * Returns the Unit from the configuration file.
     *
     * @param config the config
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
     * Damages the unit and returns true if it is dead after.
     */
    public boolean damage(int damage) {
        health -= damage;
        return isDead();
    }

    /**
     *
     * @return true if the unit is dead
     */
    private boolean isDead() {
        return health <= 0;
    }
}
