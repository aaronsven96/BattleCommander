package com.mygdx.game.models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Validation tests for the HexBoard.
 */
public class HexBoardTest {
    static HexBoard<String> h1, h2;
    static Position tl, tr, m, bl, br, oob;

    @BeforeClass
    public static void setUpBeforeClass() {
        h1 = new HexBoard<>(null, 10, 10);
        h2 = new HexBoard<>(null, 7, 9);

        tl = new Position(0, 0); // top left
        tr = new Position(0, 9); // top right
        m = new Position(5, 5); // middle
        bl = new Position(9, 0); // bottom left
        br = new Position(9, 9); // bottom right
        oob = new Position(10, 0); // out of bounds

        // Set up the board with numbered Strings
        for (int i = 0; i < h1.getNumRows(); i++) {
            for (int j = 0; j < h1.getNumColumns(); j++) {
                Position pos = new Position(i, j);
                h1.setHex(pos, "" + i + j);
            }
        }
    }

    @Test
    public void getHex() {
        assertEquals("should be Optional[00]", Optional.of("00"), h1.getHex(tl));
        assertEquals("should be Optional[09]", Optional.of("09"), h1.getHex(tr));
        assertEquals("should be Optional[55]", Optional.of("55"), h1.getHex(m));
        assertEquals("should be Optional[90]", Optional.of("90"), h1.getHex(bl));
        assertEquals("should be Optional[99]", Optional.of("99"), h1.getHex(br));

        assertEquals("should be Optional.empty", Optional.empty(), h1.getHex(oob));
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
        assertEquals("should be Optional[TL]", Optional.of("TL"), h1.getHex(tl));
        assertTrue("should be true", h1.setHex(tr, "TR"));
        assertEquals("should be Optional[TR]", Optional.of("TR"), h1.getHex(tr));
        assertTrue("should be true", h1.setHex(m, "MI"));
        assertEquals("should be Optional[MI]", Optional.of("MI"), h1.getHex(m));
        assertTrue("should be true", h1.setHex(bl, "BL"));
        assertEquals("should be Optional[BL]", Optional.of("BL"), h1.getHex(bl));
        assertTrue("should be true", h1.setHex(br, "BR"));
        assertEquals("should be Optional[BR]", Optional.of("BR"), h1.getHex(br));

        // Valid positions with a null hex Object
        assertTrue("should be true", h1.setHex(tl, null));
        assertEquals("should be null", null, h1.getHex(tl));
//        assertTrue("should be true", h1.setHex(br, null));
//        assertNull("should be null", h1.getHex(br));

        // Invalid positions
        assertFalse("should be false", h1.setHex(oob, "OB"));
        assertFalse("should be false", h1.setHex(oob, null));
    }

    @Test
    public void getBoardDimensions() {
        assertEquals("should be 10", 10, h1.getNumRows());
        assertEquals("should be 10", 10, h1.getNumColumns());

        assertEquals("should be 7", 7, h2.getNumRows());
        assertEquals("should be 9", 9, h2.getNumColumns());
    }

}