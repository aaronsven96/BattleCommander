package com.mygdx.game.models;


import lombok.Getter;


/**
 * Represents a command.
 */
@Getter
public class Command {

    /* Type of action the command represents */
    private final String actionType;

    /* Acting unit's ID */
    private final int unitId;

    /* The start and targeted positions of the action */
    private final Position startPosition;
    private final Position targetPosition;

    public Command(String actionType, int unitId, Position startPosition,
                   Position targetPosition) {
        this.actionType = actionType;
        this.unitId = unitId;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }
}
