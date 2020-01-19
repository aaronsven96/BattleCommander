package com.mygdx.game.models;

public class SupportAction implements UnitAction {
    @Override
    public String getActionName() {
        return null;
    }

    @Override
    public String getActionTexture() {
        return null;
    }

    @Override
    public boolean isValidAction(Position startPosition, Position endPosition, HexMap map) {
        return false;
    }

    @Override
    public boolean applyAction(Position startPosition, Position endPosition, HexMap map) {
        return false;
    }
}
