package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*
 * Validation tests for the HexBoardClass
 */
public class HexBoardClassTest {
    static Object o;
    static HexBoardClass<Object> h1, h2;
    static PositionClass tl, tr, m, bl, br, oob;

    @BeforeClass
    public static <T> void setUpBeforeAll() {
        o = new Object();

        h1 = new HexBoardClass<>(o, 10, 10);
        h2 = new HexBoardClass<>(o, 7, 9);

        tl = new PositionClass(0, 0); // Top left
        tr = new PositionClass(0, 9); // Top right
        m = new PositionClass(5, 5); // Middle
        bl = new PositionClass(9, 0); // Bottom left
        br = new PositionClass(9, 9); // Bottom right
        oob = new PositionClass(10, 0); // Out of bounds

        // Set up the board with dummy Strings
        for (int i = 0; i < h1.getBoard()[0].length; i++) {
            for (int j = 0; j < h1.getBoard().length; j++) {
                h1.getBoard()[i][j] = (T) ("" + i + j);
            }
        }
    }

    @Test
    public void getHex() {
        assertEquals("Should be 00", String.format("%02d", 0), h1.getHex(tl));
        assertEquals("Should be 00", String.format("%02d", 9), h1.getHex(tr));
        assertEquals("Should be 55", "55", h1.getHex(m));
        assertEquals("Should be 90", "90", h1.getHex(bl));
        assertEquals("Should be 99", "99", h1.getHex(br));

        assertNull("Should be null", h1.getHex(oob));
    }

    @Test
    public void getHexNeighbors() {
        List n1 = h1.getHexNeighbors(tl);
        assertEquals("Should be 2", 2, n1.size());
        assertEquals("Should be 01", "01", n1.get(0));
        assertEquals("Should be 10", "10", n1.get(1));

        List n2 = h1.getHexNeighbors(tr);
        assertEquals("Should be 3", 3, n2.size());
        assertEquals("Should be 08", "08", n2.get(0));
        assertEquals("Should be 18", "18", n2.get(1));
        assertEquals("Should be 19", "19", n2.get(2));

        List n3 = h1.getHexNeighbors(m);
        assertEquals("Should be 6", 6, n3.size());
        assertEquals("Should be 45", "45", n3.get(0));
        assertEquals("Should be 46", "46", n3.get(1));
        assertEquals("Should be 54", "54", n3.get(2));
        assertEquals("Should be 56", "56", n3.get(3));
        assertEquals("Should be 64", "64", n3.get(4));
        assertEquals("Should be 65", "65", n3.get(5));

        List n4 = h1.getHexNeighbors(bl);
        assertEquals("Should be 3", 3, n4.size());
        assertEquals("Should be 80", "80", n4.get(0));
        assertEquals("Should be 81", "81", n4.get(1));
        assertEquals("Should be 91", "91", n4.get(2));

        List n5 = h1.getHexNeighbors(br);
        assertEquals("Should be 2", 2, n5.size());
        assertEquals("Should be 89", "89", n5.get(0));
        assertEquals("Should be 98", "98", n5.get(1));

        assertEquals("Should be 0", 0, h1.getHexNeighbors(oob).size());
    }

    @Test
    public void setHex() {
        // Valid positions
        assertTrue("Should be true", h1.setHex(tl, "TL"));
        assertEquals("Should be TL", "TL", h1.getHex(tl));
        assertTrue("Should be true", h1.setHex(tr, "TR"));
        assertEquals("Should be TR", "TR", h1.getHex(tr));
        assertTrue("Should be true", h1.setHex(m, "MI"));
        assertEquals("Should be MI", "MI", h1.getHex(m));
        assertTrue("Should be true", h1.setHex(bl, "BL"));
        assertEquals("Should be BL", "BL", h1.getHex(bl));
        assertTrue("Should be true", h1.setHex(br, "BR"));
        assertEquals("Should be BR", "BR", h1.getHex(br));

        // Valid positions with a null hex Object
        assertTrue("Should be true", h1.setHex(tl, null));
        assertNull("Should be null", h1.getHex(tl));
        assertTrue("Should be true", h1.setHex(br, null));
        assertNull("Should be null", h1.getHex(br));

        // Invalid positions
        assertFalse("Should be false", h1.setHex(oob, "OB"));
        assertFalse("Should be false", h1.setHex(oob, null));
    }

    @Test
    public void getBoard() {
        assertEquals("Should be 10", 10, h1.getBoard().length);
        assertEquals("Should be 10", 10, h1.getBoard()[0].length);

        assertEquals("Should be 7", 7, h2.getBoard().length);
        assertEquals("Should be 9", 9, h2.getBoard()[0].length);
    }

}