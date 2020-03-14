package com.mygdx.game.models;

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

    public Command(String actionType, int unitId, Position startPosition, Position targetPosition, HexBoard<Terrain> board) {
        this.actionType = actionType;
        this.unitId = unitId;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        if (board.getDistanceBetweenTwoPositions(startPosition, targetPosition).isPresent()) {
            distance = board.getDistanceBetweenTwoPositions(startPosition, targetPosition).get();
        }
        hasLineOfSight = board.checkLineOfSight(board, startPosition, targetPosition);
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
