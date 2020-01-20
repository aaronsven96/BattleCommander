package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerrainTest {
    static Terrain swamp;
    static String test = "{\"type\": \"swamp\",\"description\": \"An example configuration file for a swamp terrain\",\"defense\": 10,\"terrainState\": \"IMPASSABLE\",\"texture\": \"\"desktop/build/resources/main/badlogic.jpg\"\"}";

    @BeforeClass
    public static void setUpBeforeClass() {
        swamp = Terrain.getTerrainFromConfig(test);
    }

    @Test
    public void getType() {
        assertEquals("type should be swamp", "swamp", swamp.getType());
    }

    @Test
    public void getDefense() {
        assertEquals("defense should be 10", 10, swamp.getDefense());
    }

    @Test
    public void getTerrainState() {
        assertEquals("state should be IMPASSABLE", TerrainState.IMPASSABLE, swamp.getTerrainState());
    }

    @Test
    public void getTexture() {
        assertEquals("state should be IMPASSABLE", TerrainState.IMPASSABLE, swamp.getTerrainState());
    }


}