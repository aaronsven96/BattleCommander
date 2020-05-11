package com.mygdx.game.models;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a player.
 */
@Getter
public class Player {
    private int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Returns a list of unit ids for a player
     *
     * @param units the HexBoard to check
     * @return a list of unit ids that belong to the player
     */
    public List<Integer> getUnitIds(HexBoard<BasicUnit> units) {
        List<Integer> unitIds = new ArrayList<>();
        for (BasicUnit unit : units) {
            if (unit.getPid() == playerId && units.getHex(units.getCursor()).isPresent()) {
                unitIds.add(units.getHex(units.getCursor()).get().getId());
            }
        }
        return unitIds;
    }
}
