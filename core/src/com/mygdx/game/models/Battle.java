package com.mygdx.game.models;


import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Comparator;
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
    private final Position position;
    private final Set<Integer> playerIds;
    private final Map<Integer, List<BasicUnit>> units;
    private final Map<Integer, Integer> strengths;
    private Map<BasicUnit, Position> startPositions;
    @Setter
    private BasicUnit defender;

    public Battle(Position position) {
        this.position = position;
        units = new HashMap<>();
        playerIds = new HashSet<>();
        strengths = new HashMap<>();
    }
    
    /**
     * Resolves the battle.
     *
     * @return a retreat for the hex
     */
    public Retreat resolve() {
        int winner = defender.getPid();
        for (int playerId : playerIds) {
            if (strengths.get(playerId) > strengths.get(winner)) {
                winner = playerId;
            }
        }
        for (int playerId : playerIds) {
            if (strengths.get(playerId).equals(strengths.get(winner)) && winner != defender.getPid()) {
                 winner = -1;
            }
        }
        for (int attacker : playerIds) {
            List<BasicUnit> enemies = new ArrayList<>();
            for (int defender : playerIds) {
                if (defender != attacker) {
                    enemies.addAll(units.get(defender));
                }
            }
            int totalHealth = 0;
            for (BasicUnit unit : enemies) {
                totalHealth += unit.getHealth();
            }
            int totalHealthAfter = totalHealth - strengths.get(attacker);
            int healthPerUnit = (int) Math.ceil((double) totalHealthAfter / enemies.size());
            enemies.removeIf(unit -> {
                strengths.put(attacker, strengths.get(attacker) - unit.getHealth() - healthPerUnit);
                return unit.damage(unit.getHealth() - healthPerUnit);
            });
            enemies.sort(Comparator.comparingInt(BasicUnit::getId));
            enemies.sort(Comparator.comparingInt(BasicUnit::getMaxHealth));
            enemies.removeIf(unit -> {
                if (strengths.get(attacker) > 0) {
                    return unit.damage(1);
                }
                return false;
            });
        }
        Retreat retreat = new Retreat(position, winner, defender);
        for (List<BasicUnit> hex : units.values()) {
            for (BasicUnit unit : hex) {
                retreat.addUnit(unit, startPositions.get(unit));
            }
        }
        return retreat;
    }

    /**
     * Adds a unit and its strength to the battle.
     * 
     * @param unit the unit to be added
     * @param strength the strength to add to the unit's player
     */
    public void addUnit(BasicUnit unit, int strength, Position startPosition) {
        if (!playerIds.contains(unit.getPid())) {
            playerIds.add(unit.getPid());
            strengths.put(unit.getPid(), 0);
        }
        List<BasicUnit> newUnits = units.get(unit.getPid());
        newUnits.add(unit);
        units.put(unit.getPid(), newUnits);
        addStrength(unit.getPid(), strength);
        startPositions.put(unit, startPosition);
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
