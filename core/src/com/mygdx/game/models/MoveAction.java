package com.mygdx.game.models;


import lombok.Getter;
import java.util.List;


@Getter
public class MoveAction implements UnitAction {
    private final String name;
    private final String texture;
    private final int strength;
    private final int speed;

    public MoveAction(String name, String texture, int strength, int speed) {
        this.name = name;
        this.texture = texture;
        this.strength = strength;
        this.speed = speed;
    }

    /**
     * Checks if a Command is valid, then moves a unit to an adjacent hex and adds it to the hex's battle.
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    @Override
    public boolean apply(Command action, IntermediateBoard board) {
        if (isValid(action, board)) {
            List<BasicUnit> newStart = board.getUnits().getHex(action.getStartPosition()).get();
            List<BasicUnit> newTarget = board.getUnits().getHex(action.getTargetPosition()).get();
            for (BasicUnit unit : newStart) {
                if (unit.getId() == action.getUnitId()) {
                    newTarget.add(unit);
                    board.getBattle(action.getTargetPosition()).get().addUnit(unit, strength, action.getStartPosition());
                    newStart.remove(unit);
                    break;
                }
            }
            board.getUnits().setHex(action.getStartPosition(), newStart);
            board.getUnits().setHex(action.getTargetPosition(), newTarget);
            return true;
        }
        return false;
     }
}
