package com.mygdx.game.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Validation tests for the HexBoard.
 */
public class HexBoardTest {
    static HexBoard<String> h1, h2;
    static Position tl, tr, m, bl, br, oob;
    static Position p02, p13, p32, p34, p40, p43, p44, p45, p49, p52, p53, p54, p55, p62, p63, p64, p65, p72, p73, p78, p87, p92, p97, p99;

    @Before
    public void setUpBefore() {
        h1 = new HexBoard<>(10, 10);
        h2 = new HexBoard<>(7, 9);

        tl = new Position(0, 0); // top left
        tr = new Position(0, 9); // top right
        m = new Position(5, 5); // middle
        bl = new Position(9, 0); // bottom left
        br = new Position(9, 9); // bottom right
        oob = new Position(10, 0); // out of bounds

        p02 = new Position(0, 2);
        p13 = new Position(1, 3);
        p32 = new Position(3, 2);
        p34 = new Position(3, 4);
        p40 = new Position(4, 0);
        p43 = new Position(4, 3);
        p44 = new Position(4, 4);
        p45 = new Position(4, 5);
        p49 = new Position(4, 9);
        p52 = new Position(5, 2);
        p53 = new Position(5, 3);
        p54 = new Position(5, 4);
        p55 = new Position(5, 5);
        p62 = new Position(6, 2);
        p63 = new Position(6, 3);
        p64 = new Position(6, 4);
        p65 = new Position(6, 5);
        p72 = new Position(7, 2);
        p73 = new Position(7, 3);
        p78 = new Position(7, 8);
        p87 = new Position(8, 7);
        p92 = new Position(9, 2);
        p97 = new Position(9, 7);
        p99 = new Position(9, 9);

        // Set up the board with numbered Strings
        for (int i = 0; i < h1.getNumRows(); i++) {
            for (int j = 0; j < h1.getNumColumns(); j++) {
                Position pos = new Position(i, j);
                h1.setHex(pos, "" + i + j);
            }
        }
    }

    @Test
    public void testGetBoardDimensions() {
        assertEquals("should be 10", 10, h1.getNumRows());
        assertEquals("should be 10", 10, h1.getNumColumns());

        assertEquals("should be 7", 7, h2.getNumRows());
        assertEquals("should be 9", 9, h2.getNumColumns());
    }

    @Test
    public void testGetDistanceBetweenTwoPositions() {
        // Invalid positions
        assertEquals("should be Optional.empty", Optional.empty(), h1.getDistanceBetweenTwoPositions(oob, tl));
        assertEquals("should be Optional.empty", Optional.empty(), h1.getDistanceBetweenTwoPositions(oob, oob));

        // Same position
        assertEquals("should be Optional[0]", Optional.of(0), h1.getDistanceBetweenTwoPositions(p73, p73));

        // Same row
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p43, p44));
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p44, p43));
        assertEquals("should be Optional[5]", Optional.of(5), h1.getDistanceBetweenTwoPositions(p40, p45));
        assertEquals("should be Optional[5]", Optional.of(5), h1.getDistanceBetweenTwoPositions(p40, p45));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(p40, p49));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(p49, p40));

        // Up-right and down-left diagonals
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p63, p53));
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p53, p63));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p32, p92));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p92, p32));

        // Down-right and up-left diagonals
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p63, p52));
        assertEquals("should be Optional[1]", Optional.of(1), h1.getDistanceBetweenTwoPositions(p52, p63));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p34, p78));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p78, p34));

        // Above and below
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p63, p43));
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p43, p63));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p02, p62));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p62, p02));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p34, p97));
        assertEquals("should be Optional[6]", Optional.of(6), h1.getDistanceBetweenTwoPositions(p97, p34));
        assertEquals("should be Optional[8]", Optional.of(8), h1.getDistanceBetweenTwoPositions(p97, p13));
        assertEquals("should be Optional[8]", Optional.of(8), h1.getDistanceBetweenTwoPositions(p13, p97));

        // Valid positions
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p43, p64));
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p64, p43));
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p87, p99));
        assertEquals("should be Optional[2]", Optional.of(2), h1.getDistanceBetweenTwoPositions(p99, p87));
        assertEquals("should be Optional[3]", Optional.of(3), h1.getDistanceBetweenTwoPositions(p32, p55));
        assertEquals("should be Optional[3]", Optional.of(3), h1.getDistanceBetweenTwoPositions(p55, p32));
        assertEquals("should be Optional[3]", Optional.of(3), h1.getDistanceBetweenTwoPositions(p55, p78));
        assertEquals("should be Optional[3]", Optional.of(3), h1.getDistanceBetweenTwoPositions(p55, p78));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p43, p72));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p72, p43));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p65, p99));
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(p99, p65));
        assertEquals("should be Optional[5]", Optional.of(5), h1.getDistanceBetweenTwoPositions(p62, p45));
        assertEquals("should be Optional[5]", Optional.of(5), h1.getDistanceBetweenTwoPositions(p45, p62));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(p02, p97));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(p97, p02));

        // Other valid positions
        assertEquals("should be Optional[4]", Optional.of(4), h1.getDistanceBetweenTwoPositions(m, br));
        assertEquals("should be Optional[5]", Optional.of(5), h1.getDistanceBetweenTwoPositions(tl, m));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(tl, tr));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(tl, bl));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(tr, m));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(tr, br));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(m, bl));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(bl, br));
        assertEquals("should be Optional[9]", Optional.of(9), h1.getDistanceBetweenTwoPositions(tl, br));
        assertEquals("should be Optional[18]", Optional.of(18), h1.getDistanceBetweenTwoPositions(tr, bl));
    }

    @Test
    public void testGetHex() {
        assertEquals("should be Optional[00]", Optional.of("00"), h1.getHex(tl));
        assertEquals("should be Optional[09]", Optional.of("09"), h1.getHex(tr));
        assertEquals("should be Optional[55]", Optional.of("55"), h1.getHex(m));
        assertEquals("should be Optional[90]", Optional.of("90"), h1.getHex(bl));
        assertEquals("should be Optional[99]", Optional.of("99"), h1.getHex(br));

        assertEquals("should be Optional.empty", Optional.empty(), h1.getHex(oob));
    }

    @Test
    public void testGetHexNeighbors() {
        List n1 = h1.getHexNeighbors(tl);
        assertEquals("should be 3", 3, n1.size());
        assertEquals("should be 01", "01", n1.get(0));
        assertEquals("should be 10", "10", n1.get(1));
        assertEquals("should be 11", "11", n1.get(2));

        List n2 = h1.getHexNeighbors(tr);
        assertEquals("should be 2", 2, n2.size());
        assertEquals("should be 08", "08", n2.get(0));
        assertEquals("should be 19", "19", n2.get(1));

        List n3 = h1.getHexNeighbors(m);
        assertEquals("should be 6", 6, n3.size());
        assertEquals("should be 45", "45", n3.get(0));
        assertEquals("should be 54", "54", n3.get(1));
        assertEquals("should be 44", "44", n3.get(2));
        assertEquals("should be 56", "56", n3.get(3));
        assertEquals("should be 65", "65", n3.get(4));
        assertEquals("should be 66", "66", n3.get(5));

        List n4 = h1.getHexNeighbors(bl);
        assertEquals("should be 2", 2, n4.size());
        assertEquals("should be 80", "80", n4.get(0));
        assertEquals("should be 91", "91", n4.get(1));

        List n5 = h1.getHexNeighbors(br);
        assertEquals("should be 3", 3, n5.size());
        assertEquals("should be 89", "89", n5.get(0));
        assertEquals("should be 98", "98", n5.get(1));
        assertEquals("should be 88", "88", n5.get(2));

        assertEquals("should be 0", 0, h1.getHexNeighbors(oob).size());
    }

    @Test
    public void testGetNearestHexNeighbors() {
        assertEquals("should be 6", 6, h1.getNearestHexNeighbors(m, 1).size());
        assertEquals("should be 18", 18, h1.getNearestHexNeighbors(m, 2).size());
        assertEquals("should be 36", 36, h1.getNearestHexNeighbors(m, 3).size());
        assertEquals("should be 60", 60, h1.getNearestHexNeighbors(m, 4).size());
        assertEquals("should be 79", 79, h1.getNearestHexNeighbors(m, 5).size());
        assertEquals("should be 87", 87, h1.getNearestHexNeighbors(m, 6).size());
        assertEquals("should be 93", 93, h1.getNearestHexNeighbors(m, 7).size());
        assertEquals("should be 97", 97, h1.getNearestHexNeighbors(m, 8).size());
        assertEquals("should be 99", 99, h1.getNearestHexNeighbors(m, 9).size());

        assertEquals("should be 3", 3, h1.getNearestHexNeighbors(br, 1).size());
        assertEquals("should be 8", 8, h1.getNearestHexNeighbors(br, 2).size());
        assertEquals("should be 15", 15, h1.getNearestHexNeighbors(br, 3).size());
        assertEquals("should be 24", 24, h1.getNearestHexNeighbors(br, 4).size());
    }

    @Test
    public void testGetNearestPositionNeighbors() {
        assertEquals("should be 6", 6, h1.getNearestPositionNeighbors(m, 1).size());
        assertEquals("should be 18", 18, h1.getNearestPositionNeighbors(m, 2).size());
        assertEquals("should be 36", 36, h1.getNearestPositionNeighbors(m, 3).size());
        assertEquals("should be 60", 60, h1.getNearestPositionNeighbors(m, 4).size());
        assertEquals("should be 79", 79, h1.getNearestPositionNeighbors(m, 5).size());
        assertEquals("should be 87", 87, h1.getNearestPositionNeighbors(m, 6).size());
        assertEquals("should be 93", 93, h1.getNearestPositionNeighbors(m, 7).size());
        assertEquals("should be 97", 97, h1.getNearestPositionNeighbors(m, 8).size());
        assertEquals("should be 99", 99, h1.getNearestPositionNeighbors(m, 9).size());

        assertEquals("should be 3", 3, h1.getNearestPositionNeighbors(br, 1).size());
        assertEquals("should be 8", 8, h1.getNearestPositionNeighbors(br, 2).size());
        assertEquals("should be 15", 15, h1.getNearestPositionNeighbors(br, 3).size());
        assertEquals("should be 24", 24, h1.getNearestPositionNeighbors(br, 4).size());
    }

    @Test
    public void testGetPositionNeighbors() {
        assertEquals("should be 3", 3, h1.getPositionNeighbors(tl).size());
        assertEquals("should be 6", 6, h1.getPositionNeighbors(m).size());
        assertEquals("should be 2", 2, h1.getPositionNeighbors(tr).size());
        assertEquals("should be 2", 2, h1.getPositionNeighbors(bl).size());
        assertEquals("should be 3", 3, h1.getPositionNeighbors(br).size());
        assertEquals("should be 0", 0, h1.getPositionNeighbors(oob).size());
    }

    @Test
    public void testGetRandomShortestPath() {
        assertEquals("should be 1", 1, h1.getRandomShortestPath(p62, p62).size());
        assertEquals("should be 2", 2, h1.getRandomShortestPath(p43, p44).size());

        assertEquals("should be 6", 6, h1.getRandomShortestPath(tl, m).size());
        assertEquals("should be 19", 19, h1.getRandomShortestPath(tr, bl).size());
    }

    @Test
    public void testIsInProximity() {
        // Invalid positions
        assertFalse(h1.isInProximity(oob, tl, 999));
        assertFalse(h1.isInProximity(oob, oob, 999));

        // Same position
        assertTrue(h1.isInProximity(tl, tl, 0));

        // Different positions
        assertFalse(h1.isInProximity(tr, m, 8));
        assertTrue(h1.isInProximity(tr, m, 9));
        assertFalse(h1.isInProximity(m, bl, 8));
        assertTrue(h1.isInProximity(m, bl, 9));
    }

    @Test
    public void testSetHex() {
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
        assertEquals("should be Optional.empty()", Optional.empty(), h1.getHex(tl));
        assertTrue("should be true", h1.setHex(br, null));
        assertEquals("should be Optional.empty()", Optional.empty(), h1.getHex(br));

        // Invalid positions
        assertFalse("should be false", h1.setHex(oob, "OB"));
        assertFalse("should be false", h1.setHex(oob, null));
    }
}