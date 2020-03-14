package com.mygdx.game.models;


import java.util.List;
import java.util.Map;
import java.util.Set;


public class Battle {
    Set<Integer> playerIds;
    Map<Integer, List<BasicUnit>> units;
    private Map<Integer, Integer> strengths;

    public Battle(Map<Integer, List<BasicUnit>> units) {

    }

    /**
     * Resolves the battle.
     *
     * @return a Map of player ids to their participating units post-resolution of combat
     */
    public Map<Integer, List<BasicUnit>> resolveBattle() {

    }
}
