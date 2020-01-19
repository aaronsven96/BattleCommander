package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Validation tests for the HexBoard.
 */
public class HexBoardTest {
    static Object obj;
    static HexBoard<Object> h1, h2;
    static Position tl, tr, m, bl, br, oob;

    @BeforeClass
    public static <T> void setUpBeforeAll() {
        obj = new Object();

        h1 = new HexBoard<>(obj, 10, 10);
        h2 = new HexBoard<>(obj, 7, 9);

        tl = new Position(0, 0); // top left
        tr = new Position(0, 9); // top right
        m = new Position(5, 5); // middle
        bl = new Position(9, 0); // bottom left
        br = new Position(9, 9); // bottom right
        oob = new Position(10, 0); // out of bounds

        // Set up the board with dummy Strings
        for (int i = 0; i < h1.getBoard()[0].length; i++) {
            for (int j = 0; j < h1.getBoard().length; j++) {
                h1.getBoard()[i][j] = (T) ("" + i + j);
            }
        }
    }

    @Test
    public void getHex() {
        assertEquals("should be 00", String.format("%02d", 0), h1.getHex(tl));
        assertEquals("should be 00", String.format("%02d", 9), h1.getHex(tr));
        assertEquals("should be 55", "55", h1.getHex(m));
        assertEquals("should be 90", "90", h1.getHex(bl));
        assertEquals("should be 99", "99", h1.getHex(br));

        assertNull("should be null", h1.getHex(oob));
    }

    @Test
    public void getHexNeighbors() {
        List n1 = h1.getHexNeighbors(tl);
        assertEquals("should be 2", 2, n1.size());
        assertEquals("should be 01", "01", n1.get(0));
        assertEquals("should be 10", "10", n1.get(1));

        List n2 = h1.getHexNeighbors(tr);
        assertEquals("should be 3", 3, n2.size());
        assertEquals("should be 08", "08", n2.get(0));
        assertEquals("should be 18", "18", n2.get(1));
        assertEquals("should be 19", "19", n2.get(2));

        List n3 = h1.getHexNeighbors(m);
        assertEquals("should be 6", 6, n3.size());
        assertEquals("should be 45", "45", n3.get(0));
        assertEquals("should be 46", "46", n3.get(1));
        assertEquals("should be 54", "54", n3.get(2));
        assertEquals("should be 56", "56", n3.get(3));
        assertEquals("should be 64", "64", n3.get(4));
        assertEquals("should be 65", "65", n3.get(5));

        List n4 = h1.getHexNeighbors(bl);
        assertEquals("should be 3", 3, n4.size());
        assertEquals("should be 80", "80", n4.get(0));
        assertEquals("should be 81", "81", n4.get(1));
        assertEquals("should be 91", "91", n4.get(2));

        List n5 = h1.getHexNeighbors(br);
        assertEquals("should be 2", 2, n5.size());
        assertEquals("should be 89", "89", n5.get(0));
        assertEquals("should be 98", "98", n5.get(1));

        assertEquals("should be 0", 0, h1.getHexNeighbors(oob).size());
    }

    @Test
    public void setHex() {
        // Valid positions
        assertTrue("should be true", h1.setHex(tl, "TL"));
        assertEquals("should be TL", "TL", h1.getHex(tl));
        assertTrue("should be true", h1.setHex(tr, "TR"));
        assertEquals("should be TR", "TR", h1.getHex(tr));
        assertTrue("should be true", h1.setHex(m, "MI"));
        assertEquals("should be MI", "MI", h1.getHex(m));
        assertTrue("should be true", h1.setHex(bl, "BL"));
        assertEquals("should be BL", "BL", h1.getHex(bl));
        assertTrue("should be true", h1.setHex(br, "BR"));
        assertEquals("should be BR", "BR", h1.getHex(br));

        // Valid positions with a null hex Object
        assertTrue("should be true", h1.setHex(tl, null));
        assertNull("should be null", h1.getHex(tl));
        assertTrue("should be true", h1.setHex(br, null));
        assertNull("should be null", h1.getHex(br));

        // Invalid positions
        assertFalse("should be false", h1.setHex(oob, "OB"));
        assertFalse("should be false", h1.setHex(oob, null));
    }

    @Test
    public void getBoard() {
        assertEquals("should be 10", 10, h1.getBoard().length);
        assertEquals("should be 10", 10, h1.getBoard()[0].length);

        assertEquals("should be 7", 7, h2.getBoard().length);
        assertEquals("should be 9", 9, h2.getBoard()[0].length);
    }

}