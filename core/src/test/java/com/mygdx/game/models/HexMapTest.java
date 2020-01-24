package com.mygdx.game.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Validation tests for the HexMap.
 */
public class HexMapTest {
    static HexMap hm;

    @Before
    public static void setUpBefore() {
        hm = HexMap.getHexMapFromConfig("");
    }

    @Test
    public void testGetHexMapFromConfig() {

    }

}