package com.mygdx.game.models;


import lombok.Getter;
import java.util.List;
import java.util.Map;


@Getter
public class SupportAction implements UnitAction {
    private final String name;
    private final String texture;
    private final int speed;
    private final int[] strength;

    public SupportAction(String name, String texture, int speed, int[] strength) {
        this.name = name;
        this.texture = texture;
        this.speed = speed;
        this.strength = strength;
    }

    /**
     * Checks if a Command is valid in the context of the board and this action.
     *
     * @param action the command to be checked
     * @param board the current board
     * @return true if action is valid
     */
    @Override
    public boolean isValid(Command action, IntermediateBoard board) {
        return (action.getStartPosition().getX() != action.getTargetPosition().getX() || action.getStartPosition().getY() != action.getTargetPosition().getY()) && board.isHex(action.getStartPosition()) && board.isHex(action.getTargetPosition()) && board.isValidUnit(action.getUnitId()) && board.getTerrain().checkLineOfSight(board.getTerrain(), action.getStartPosition(), action.getTargetPosition()) && board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).isPresent() && getRangedStrength(board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).get()) > 0;
    }

    /**
     * Checks if a Command is valid, then adds strength to the target hex's battle.
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    @Override
    public boolean apply(Command action, IntermediateBoard board) {
        if (isValid(action, board)) {
            board.getBattle(action.getTargetPosition()).get().addStrength(board.getUnitFromId(action.getUnitId()).get().getPid(), getRangedStrength(board.getBattles().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).get()));
        }
        return false;
    }

    /**
     * Returns the strength of a support at a specific distances.
     *
     * @param distance the distance between the supporting unit and the battle it's supporting
     * @return the strength of the support
     */
    private int getRangedStrength(int distance) {
        if (distance <= strength.length) {
            return strength[distance - 1];
        } else {
            return 0;
        }
    }
}
