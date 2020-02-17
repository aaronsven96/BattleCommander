package com.mygdx.game.models;

public class MoveAction implements UnitAction {
    private int maxLength;
    private boolean flight;

    public MoveAction(int maxLength, boolean flight) {
        this.maxLength = maxLength;
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
        return action.getDistance() <= maxLength && action.hasLineOfSight();
    }

    @Override
    public IntermediateBoard applyAction(Command action, IntermediateBoard board) {
        board.getUnits().setHex(action.getTargetPosition(), board.getUnits().getHex(action.getTargetPosition()));
        return board;
    }
}
