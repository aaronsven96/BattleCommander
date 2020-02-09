package com.mygdx.game.models;

public interface UnitAction {

    String getActionName();

    String getActionTexture();

    boolean isValidAction();

    boolean applyAction();
}
