package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicUnitTest {
    static BasicUnit soldier, archer;

    private static String test ="{\"Type\": \"Soldier\",\"Description\": \"An example configuration file for a soldier\",\"Max Health\": 25,\"Strength\": 10,\"Ranged Strength\":" +
            "0,\"Range\": 1,\"Speed\": 1,\"Area of Effect\": \"Skewer\",\"Attributes\": []}";

    @BeforeClass
    public static void UnitSetup() {
        soldier = BasicUnit.getUnitFromConfig(test, 0, 0, "");

        //archer = new BasicUnit("archer.json");
    }

@Test
    public void getState(){
        assertEquals("state should be normal", UnitState.normal, soldier.getState());
        //assertEquals("state should be normal", UnitState.normal, archer.getState());
    }

}