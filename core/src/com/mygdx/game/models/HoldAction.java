package com.mygdx.game.models;

import lombok.Getter;

public class HoldAction implements UnitAction{
    @Getter
    private String actionName;
    @Getter
    private String actionTexture;
    @Getter
    private int strength;

    public HoldAction(String actionName, String actionTexture, int strength) {
        this.actionName = actionName;
        this.actionTexture = actionTexture;
        this.strength = strength;
    }

    @Override
    public boolean isValidAction(Command action, IntermediateBoard board) {
        return board.getBattles().getHex(action.getStartPosition()).isPresent() && board.getBattles().getHex(action.getTargetPosition()).isPresent();
    }

    @Override
    public boolean applyAction(Command action, IntermediateBoard board) {
        if (isValidAction(action, board)) {
            board.getBattles().getHex(action.getTargetPosition()).get().addUnit(board.getUnitFromId(action.getUnitId()).get());

        }
        return false;
    }
}
