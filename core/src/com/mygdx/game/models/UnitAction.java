package com.mygdx.game.models;

public interface UnitAction {

    String getActionName();

    String getActionTexture();

    boolean isValidAction(Position startPosition, Position endPosition, HexMap map);

    boolean applyAction(Position startPosition, Position endPosition, HexMap map);
}
