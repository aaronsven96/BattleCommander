package com.mygdx.game.models;

/**
 * Class to represent a command
 */
public class Command {

    /* Type of action the command represents */
    private String actionType;

    /* Acting unit's ID */
    private int unitId;

    /* Target unit's ID. Not necessary, commented out for now. */
//     private int targetUnitId;

    /* Targeted position */
    private Position targetPosition;

    public Command(String actionType, int unitId /*, int targetUnitId */, Position targetPosition) {
        this.actionType = actionType;
        this.unitId = unitId;
        /* this.targetUnitId = targetUnitId; */
        this.targetPosition = targetPosition;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

//    public int getTargetUnitId() {
//        return targetUnitId;
//    }
//
//    public void setTargetUnitId(int targetUnitId) {
//        this.targetUnitId = targetUnitId;
//    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

}
