package com.mygdx.game.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Retreat {
    private final Map<BasicUnit, Position> startPositions;
    private final Position position;
    private final int winner;
    private final BasicUnit defender;

    public Retreat(Position position, int winner, BasicUnit defender) {
        startPositions = new HashMap<>();
        this.position = position;
        this.winner = winner;
        this.defender = defender;
    }

    /**
     * Adds a unit and their starting position to the retreat,
     */
    public void addUnit(BasicUnit unit, Position startPosition) {
        startPositions.put(unit, startPosition);
    }

    /**
     * Resolves the retreat by moving the involved units.
     *
     * @param units a HexBoard of units to resolve onto
     */
    public HexBoard<List<BasicUnit>> resolve(HexBoard<List<BasicUnit>> units) {
        // TODO: Make this work

//        BasicUnit winUnit = null;
//        for (Map.Entry<BasicUnit, Position> entry : startPositions.entrySet()) {
//            if (entry.getKey().getPid() != winner) {
//                if (entry.getKey().getId() == defender.getId()) {
//
//                } else {
//                    List<BasicUnit> newTarget = units.getHex(entry.getValue()).get();
//                    newTarget.add(entry.getKey());
//                    units.setHex(entry.getValue(), newTarget);
//                    entry.getKey().setUnitState(UnitState.routed);
//                }
//            } else if (winUnit == null) {
//                winUnit = entry.getKey();
//            }
//        }
//        List<BasicUnit> newPosition = new ArrayList<BasicUnit>();
//        newPosition.add(winUnit);
//        units.setHex(position, newPosition);
        return units;
    }
}
