package com.mygdx.game.models;

public class BasicUnit {
    int health; //unit health
    int strength; //base strength
    int ranged_strength; //base ranged strength
    int speed; //movement speed
    String unitType; //the type of unit


    public BasicUnit() {
        health=0;
        strength=0;
        ranged_strength=0;
        speed=1;
    }

    public BasicUnit(String unitType) {
        String content = new Scanner(new File("filename")).useDelimiter("\Z").next();
        Gson gson = new Gson();
        gson.fromjson(content);
    }
}
