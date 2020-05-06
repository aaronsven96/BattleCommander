package com.mygdx.game.models;


import lombok.Getter;
import java.util.List;


@Getter
public class MoveAction implements UnitAction {
    private String name;
    private String texture;
    private int strength;
    private int speed;

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
            List<BasicUnit> newStart = board.getUnits().getHex(action.getStartPosition()).get().get(board.getUnitFromId(action.getUnitId()).get().getPid());
            List<BasicUnit> newTarget = board.getUnits().getHex(action.getStartPosition()).get().get(board.getUnitFromId(action.getUnitId()).get().getPid());
            newStart.forEach(unit -> {if(unit.getId() == action.getUnitId()){newTarget.add(unit);}});
            newStart.forEach(unit -> {if(unit.getId() == action.getUnitId()){board.getBattles().getHex(action.getTargetPosition()).get().addUnit(unit, strength);}});
            newStart.removeIf(unit -> unit.getId() == action.getUnitId());
            return true;
        }
        return false;
     }
}
