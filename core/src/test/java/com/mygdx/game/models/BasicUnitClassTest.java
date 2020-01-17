package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicUnitClassTest {
    static BasicUnit soldier, archer;

    @BeforeClass
    public static <T> void UnitSetup() throws ClassNotFoundException {
        Class.forName("com.badlogic.gdx.Gdx");
        soldier = BasicUnit.getUnitFromConfig("configuration/soldier.json");


        //archer = new BasicUnit("archer.json");
    }
    private String test ="{\"Type\": \"Soldier\",\"Description\": \"An example configuration file for a soldier\",\"Max Health\": 25,\"Strength\": 10,\"Ranged Strength\":" +
            "0,\"Range\": 1,\"Speed\": 1,\"Area of Effect\": \"Skewer\",\"Attributes\": []}";

@Test
    public void getState(){
        assertEquals("state should be normal", UnitState.normal, soldier.getState());
        assertEquals("state should be normal", UnitState.normal, archer.getState());
    }

}
