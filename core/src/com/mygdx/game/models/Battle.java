package com.mygdx.game.models;


import java.util.*;

public class Battle {
    private Set<Integer> playerIds;
    private Map<Integer, List<BasicUnit>> units;
    private Map<Integer, Integer> strengths;

    public Battle(Map<Integer, List<BasicUnit>> units) {
        this.units = units;
        playerIds = new HashSet<>();
        playerIds.addAll(units.keySet());
        strengths = new HashMap<>();
        playerIds.forEach((id) -> strengths.put(id, 0));
    }
    /**
     * Resolves the battle.
     */
    public void resolveBattle() {
        units.forEach((id, units) -> units.forEach((unit) -> strengths.put(id, strengths.get(id) + unit.getStrength())));
        units.forEach((id, units) -> );
    }
}
