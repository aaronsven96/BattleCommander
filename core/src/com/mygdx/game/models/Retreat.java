package com.mygdx.game.models;


import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Retreat {
    private Map<BasicUnit, Position> startPositions;
    private final Position position;
    private final int winner;
    @Setter
    private BasicUnit defender;

    public Retreat(Position position, int winner) {
        startPositions = new HashMap<>();
        this.position = position;
        this.winner = winner;

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
        HexBoard<List<BasicUnit>> newUnits = units;
        for (Position position : startPositions.values()) {

        }
    }
}
