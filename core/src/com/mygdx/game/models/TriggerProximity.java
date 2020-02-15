package com.mygdx.game.models;

public class TriggerProximity implements Trigger {

    public TriggerProximity(Unit unit1, Unit unit2, int distance) {

    }

    @Override
    public boolean isTriggered(HexMap map) {
        return false;
    }
}
