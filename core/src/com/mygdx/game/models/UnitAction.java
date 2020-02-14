package com.mygdx.game.models;

public interface UnitAction {

    String getActionName();

    String getActionTexture();

    boolean isValidAction(Command action);

    boolean applyAction();
}
