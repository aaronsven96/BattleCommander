package com.mygdx.game.models;

import lombok.Getter;

public class HoldAction implements UnitAction{
    @Getter
    private String actionName;
    @Getter
    private String actionTexture;

    public HoldAction(String actionName, String actionTexture) {
        this.actionName = actionName;
        this.actionTexture = actionTexture;
    }

    public boolean isValidAction(Command action, IntermediateBoard board) {
        return true;
    }

    public IntermediateBoard applyAction(Command action, IntermediateBoard board) {
        return board;
    }
}
