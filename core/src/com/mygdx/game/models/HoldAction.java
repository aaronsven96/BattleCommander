package com.mygdx.game.models;


import lombok.Getter;


@Getter
public class HoldAction implements UnitAction{
    private String name;
    private String texture;
    private int strength;
    private int speed;
    
    public HoldAction(String name, String texture, int strength, int speed) {
        this.name = name;
        this.texture = texture;
        this.strength = strength;
        this.speed = speed;
    }

    /**
     * Checks if a Command is valid, then adds the unit to its hex's battle..
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    @Override
    public boolean apply(Command action, IntermediateBoard board) {
        if (isValid(action, board)) {
            board.getBattles().getHex(action.getTargetPosition()).get().addUnit(board.getUnitFromId(action.getUnitId()).get(), strength);
        }
        return false;
    }
}
