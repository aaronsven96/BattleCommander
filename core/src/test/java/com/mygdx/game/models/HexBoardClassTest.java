package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Validation tests for the HexBoardClass
 */
public class HexBoardClassTest {
    static Object o;
    static HexBoardClass<Object> h;
    static PositionClass p1, p2, p3;

    @BeforeClass
    public static <T> void setUpBeforeAll() {
        o = new Object();
        h = new HexBoardClass<>(o, 10, 10);
        p1 = new PositionClass(0, 0);
        p2 = new PositionClass(9, 9);
        p3 = new PositionClass(10, 0);

        for (int i = 0; i < h.getBoard()[0].length; i++) {
            for (int j = 0; j < h.getBoard().length; j++) {
                h.getBoard()[i][j] = (T) ("" + i + j);
            }
        }
    }

    @Test
    public void getHex() {
        assertEquals("Should be 00", String.format("%02d", 0), h.getHex(p1));
        assertEquals("Should be 99", "99", h.getHex(p2));
        assertEquals("Should be null", null, h.getHex(p3));
    }

    @Test
    public void getHexNeighbors() {
        assertEquals("Should be 01", String.format("%02d", 1), h.getHexNeighbors(p1).get(0));
        assertEquals("Should be 01", "10", h.getHexNeighbors(p1).get(1));
        assertEquals("Should be 98", "89", h.getHexNeighbors(p2).get(0));
        assertEquals("Should be 98", "98", h.getHexNeighbors(p2).get(1));
    }

    @Test
    public void setHex() {
        // Valid positions
        assertTrue("Should be true", h.setHex(p1, "P1"));
        assertEquals("Should be P1", "P1", h.getHex(p1));
        assertTrue("Should be true", h.setHex(p2, "P2"));
        assertEquals("Should be P2", "P2", h.getHex(p2));

        // Valid positions with null hex Object
        assertTrue("Should be false", h.setHex(p1, null));
        assertNull("Should be P1", h.getHex(p1));
        assertTrue("Should be false", h.setHex(p2, null));
        assertNull("Should be P1", h.getHex(p2));

        // Invalid positions
        assertFalse("Should be false", h.setHex(p3, "P3"));
        assertFalse("Should be false", h.setHex(p3, null));
    }

    @Test
    public void getBoard() {
        assertEquals("Should be 10", 10, h.getBoard().length);
        assertEquals("Should be 10", 10, h.getBoard()[0].length);
    }

}