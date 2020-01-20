package com.mygdx.game.models;

/**
 * Class to represent a command
 */
public class Command {
    /* Type of action the command represents */
    private String actionType;

    /* Acting unit's ID */
    private int unitId;

    /* Target unit's ID */
    private int targetUnitId;

    /* Position action is taken from */
    private Position startPosition;

    /* Targeted position */
    private Position targetPosition;

    /* The number of turns the command has been active */
    private int passedDuration;

    /* How long the command takes */
    private int totalDuration;

    public Command(String actionType, int unitId, int targetUnitId, Position startPosition, Position targetPosition, int passedDuration, int totalDuration) {
        this.actionType = actionType;
        this.unitId = unitId;
        this.targetUnitId = targetUnitId;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        this.passedDuration = passedDuration;
        this.totalDuration = totalDuration;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getTargetUnitId() {
        return targetUnitId;
    }

    public void setTargetUnitId(int targetUnitId) {
        this.targetUnitId = targetUnitId;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public int getPassedDuration() {
        return passedDuration;
    }

    public void setPassedDuration(int passedDuration) {
        this.passedDuration = passedDuration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }
}
