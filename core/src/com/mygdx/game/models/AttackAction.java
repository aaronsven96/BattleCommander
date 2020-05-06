package com.mygdx.game.models;


import lombok.Getter;
import java.util.List;
import java.util.Map;


@Getter
public class AttackAction implements UnitAction {
    private String name;
    private String texture;
    private int[] damage;
    private int speed;

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
        return board.isValidPosition(action.getStartPosition()) && board.isValidPosition(action.getTargetPosition()) && board.isValidUnit(action.getUnitId()) && board.getTerrain().checkLineOfSight(board.getTerrain(), action.getStartPosition(), action.getTargetPosition()) && getRangedDamage(board.getUnits().getDistanceBetweenTwoPositions(action.getStartPosition(), action.getTargetPosition()).get()) > 0;
    }

    /**
     * Checks if a Command is valid, then damages all units on target hex.
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    @Override
    public boolean apply(Command action, IntermediateBoard board) {
        if (isValid(action, board)) {
            for (Map.Entry<Integer, List<BasicUnit>> entry : board.getUnits().getHex(action.getTargetPosition()).get().entrySet()) {
                Integer playerId = entry.getKey();
                List<BasicUnit> units = entry.getValue();
                units.removeIf(unit -> unit.damage(getRangedDamage(action.getDistance())));
            }
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
