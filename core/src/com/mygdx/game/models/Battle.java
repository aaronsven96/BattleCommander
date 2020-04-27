package com.mygdx.game.models;


import lombok.Getter;
import lombok.Setter;

import java.util.*;


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
        units.forEach((id, units) -> units.forEach((unit) -> strengths.put(id, strengths.get(id) + unit.getStrength())));
        units.forEach((id, units) -> );
    }

    public void addUnit(BasicUnit unit) {
        if (!playerIds.contains(unit.getPid())) {
            playerIds.add(unit.getPid());
            strengths.put(unit.getPid(), 0);
        }
        List<BasicUnit> newUnits = units.get(unit.getPid());
        newUnits.add(unit);
        units.put(unit.getPid(), newUnits);
    }

    public void addStrength(int playerId, int strength) {
        strengths.put(playerId, strengths.get(playerId) + strength);
    }
}
