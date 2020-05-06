package com.mygdx.game.models;


import lombok.Getter;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Represents a battle. Tracks the units and strengths of each involved player.
 */
@Getter
public class Battle {
    private Set<Integer> playerIds;
    private Map<Integer, List<BasicUnit>> units;
    private Map<Integer, Integer> strengths;

    public Battle() {
        units = new HashMap<>();
        playerIds = new HashSet<>();
        strengths = new HashMap<>();
    }
    /**
     * Resolves the battle.
     */
    public void resolveBattle() {
    }

    /**
     * Adds a unit and its strength to the battle.
     * 
     * @param unit the unit to be added
     * @param strength the strength to add to the unit's player
     */
    public void addUnit(BasicUnit unit, int strength) {
        if (!playerIds.contains(unit.getPid())) {
            playerIds.add(unit.getPid());
            strengths.put(unit.getPid(), 0);
        }
        List<BasicUnit> newUnits = units.get(unit.getPid());
        newUnits.add(unit);
        units.put(unit.getPid(), newUnits);
        addStrength(unit.getPid(), strength);
    }

    /**
     * Adds strength to a player in the battle
     *
     * @param playerId the player to add strength to
     * @param strength the amount of strength to be added
     */
    public void addStrength(int playerId, int strength) {
        strengths.put(playerId, strengths.get(playerId) + strength);
    }
}
