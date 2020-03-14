package com.mygdx.game.models;


import java.util.HashSet;
import java.util.Set;


/**
 * A class that represents a player.
 */
public class Player {
    private int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Returns a Set of player ids.
     *
     * @return A Set of player ids
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Returns a list of unit ids for a player
     *
     * @param units a HexBoard of units
     * @return A list of unit ids
     */
    public Set<Integer> getUnitIds(HexBoard<BasicUnit> units) {
        Set<Integer> unitIds = new HashSet<>();
        for (int y = 0; y < units.getNumColumns(); y++) {
            for (int x = 0; x < units.getNumRows(); x++) {
                if (units.getHex(new Position(y, x)).isPresent()) {
                    unitIds.add(units.getHex(new Position(y, x)).get().getId());
                }
            }
        }
        return unitIds;
    }
}
