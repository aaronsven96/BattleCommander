package com.mygdx.game.models;


import lombok.Getter;


@Getter
public class HoldAction implements UnitAction {
    private final String name;
    private final String texture;
    private final int strength;
    private final int speed;
    
    public HoldAction(String name, String texture, int strength, int speed) {
        this.name = name;
        this.texture = texture;
        this.strength = strength;
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
        return action.getStartPosition().getX() == action.getTargetPosition().getX() && action.getStartPosition().getY() == action.getTargetPosition().getY() && board.isHex(action.getStartPosition()) && board.isHex(action.getTargetPosition()) && board.isValidUnit(action.getUnitId());
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
            board.getBattles().getHex(action.getTargetPosition()).get().addUnit(board.getUnitFromId(action.getUnitId()).get(), strength, action.getStartPosition());
            board.getBattle(action.getTargetPosition()).get().setDefender(board.getUnitFromId(action.getUnitId()).get());
        }
        return false;
    }
}
