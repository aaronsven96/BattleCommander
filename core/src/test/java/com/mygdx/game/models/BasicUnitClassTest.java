package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicUnitClassTest {
    static BasicUnit soldier, archer;

    @BeforeClass
    public static <T> void UnitSetup(){

        soldier = BasicUnit.getUnitFromConfig("configuration/soldier.json");


        //archer = new BasicUnit("archer.json");
    }

    @Test
    public void getState(){
        assertEquals("state should be normal", UnitState.normal, soldier.getState());
        assertEquals("state should be normal", UnitState.normal, archer.getState());
    }

}
