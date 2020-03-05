package com.mygdx.game.models;

import java.util.List;
import java.util.Map;


public class SupportAction implements UnitAction {
    private int maxRange;
    private int strength;

    public SupportAction(int maxRange, int strength) {
        this.maxRange = maxRange;
        this.strength = strength;
    }

    @Override
    public String getActionName() {
        return null;
    }

    @Override
    public String getActionTexture() {
        return null;
    }

    @Override
    public boolean isValidAction(Command action) {
        return action.getDistance() <= maxRange && action.hasLineOfSight();
    }

    @Override
    public IntermediateBoard applyAction(Command action, IntermediateBoard board) {
        HexBoard<List<Integer>> newBoard = new HexBoard<>(board.getUnits().getNumRows(), board.getUnits().getNumColumns());
        Map<Integer, Integer> newTargetHex = board.getStrengths().getHex(action.getTargetPosition()).get();
        if () {
            newTargetHex.get(board.getUnitOwnerId().get()) += strength;
        }
        newBoard.setHex(action.getTargetPosition(), newTargetHex);
    }
}
