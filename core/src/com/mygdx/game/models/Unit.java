package com.mygdx.game.models;

public interface Unit {

    public String getType();

    public UnitState getUnitState();

    public void setUnitState(UnitState unitState);

    /**
     * The Id of the unit
     */
    public int getUnitId();

}
