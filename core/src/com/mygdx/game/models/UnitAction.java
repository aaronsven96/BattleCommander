package com.mygdx.game.models;


/**
 * A unit's action. Represents not the action itself, but the template which actions are applied from.
 *
 * @see Command
 */
public interface UnitAction {

    /**
     * Returns the action's name.
     *
     * @return the action's name
     */
    String getName();

    /**
     * Returns the path to the action's texture.
     *
     * @return the path to the action's texture
     */
    String getTexture();

    /**
     * Return the speed of the action.
     *
     * @return the action's speed
     */
    int getSpeed();

    /**
     * Checks if a Command is valid in the context of the board and this action.
     *
     * @param action the command to be checked
     * @param board the current board
     * @return true if action is valid
     */
    default boolean isValid(Command action, IntermediateBoard board) {
        return board.isValidPosition(action.getStartPosition()) && board.isValidPosition(action.getTargetPosition()) && board.isValidUnit(action.getUnitId());
    }

    /**
     * Checks if a Command is valid, then resolves the Command onto an IntermediateBoard.
     *
     * @param action the Command to resolve
     * @param board the board to resolve onto
     * @return true if the action is valid
     */
    default boolean apply(Command action, IntermediateBoard board) {
        if (isValid(action, board)) {
            return true;
        }
        return false;
    }
}
