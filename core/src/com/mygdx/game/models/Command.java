package com.mygdx.game.models;

import com.mygdx.game.UtilityMethods;

/**
 * Class to represent a command
 */
public class Command {

    /** Type of action the command represents */
    private String actionType;

    /** Acting unit's ID */
    private int unitId;

    /** The start and targeted positions of the action. */
    private Position startPosition;
    private Position targetPosition;

    /** The distance between the start and end positions. */
    private int distance;

    /** Whether there is line of sight between the start and end positions */
    private boolean hasLineOfSight;

    public Command(String actionType, int unitId, Position startPosition, Position targetPosition) {
        this.actionType = actionType;
        this.unitId = unitId;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        distance = UtilityMethods.calculateDistance(startPosition, targetPosition);
    }

    public int getUnitId() {
        return unitId;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public int getDistance() {
        return distance;
    }

    public boolean hasLineOfSight() {
        return hasLineOfSight;
    }
}
