package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicUnitTest {
    static BasicUnit soldier, archer;

    private static String test ="{\"type\": \"Soldier\",\"description\": \"An example configuration file for a soldier\",\"maxHealth\": 25,\"strength\": 10,\"rangedStrength\":" +
            "0,\"range\": 1,\"speed\": 1,\"areaOfEffect\": \"Skewer\",\"attributes\": []}";

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
