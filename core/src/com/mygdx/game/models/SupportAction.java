package com.mygdx.game.models;

public class SupportAction implements UnitAction {
    private int maxRange;
    private boolean flight;

    public SupportAction(int maxRange, boolean flight) {
        this.maxRange = maxRange;
        this.flight = flight;
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
        return null;
    }
}
