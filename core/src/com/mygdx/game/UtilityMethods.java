package com.mygdx.game;

import com.mygdx.game.models.HexBoard;
import com.mygdx.game.models.HexMap;
import com.mygdx.game.models.Position;
import com.mygdx.game.models.Terrain;

public abstract class UtilityMethods {
    /**
     * Dummy methods.
     * TODO: Replace these with a real methods.
     */

    /** Calculates the distance between the start and end positions. */
    public static int calculateDistance(Position startPosition, Position endPosition) {
        return 1;
    }

    /** Calculates whether the start position can see the end position. */
    public static boolean calculateLineOfSight(HexBoard<Terrain> board, Position startPosition, Position endPosition) {
        return true;
    }

    /** Dummy methods end here. */
}
