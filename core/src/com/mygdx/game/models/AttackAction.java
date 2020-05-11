package com.mygdx.game.models;


import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public class AttackAction implements UnitAction {
    private final String name;
    private final String texture;
    private final int[] damage;
    private final int speed;

    public AttackAction(String name, String texture, int[] damage, int speed) {
        this.name = name;
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
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
        return (action.getStartPosition().getX() != action.getTargetPosition().getX() || action.getStartPosition().getY() != action.getTargetPosition().getY()) && board.isHex(action.getStartPosition()) && board.isHex(action.getTargetPosition()) && board.isValidUnit(action.getUnitId()) && board.getTerrain().checkLineOfSight(board.getTerrain(), action.getStartPosition(), action.getTargetPosition()) && board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).isPresent() && getRangedDamage(board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).get()) > 0;
    }

    /**
     * Checks if a Command is valid, then damages all units on target hex.
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    @Override
    public boolean apply(@NotNull Command action, @NotNull IntermediateBoard board) {
        if (isValid(action, board)) {
            board.getUnits().getHex(action.getTargetPosition()).get().removeIf(unit -> unit.damage(getRangedDamage(board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).get())));
            return true;
        }
        return false;
    }

    /**
     * Returns the damage an attack deals at a specific distances.
     *
     * @param distance the distance between the attacking unit and the target
     * @return the damage of the attack
     */
    private int getRangedDamage(int distance) {
        if (distance <= damage.length) {
            return damage[distance - 1];
        } else {
            return 0;
        }
    }
}
