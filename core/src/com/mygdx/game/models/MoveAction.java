package com.mygdx.game.models;

import java.util.List;

public class MoveAction implements UnitAction {

    /** Action's name and texture */
    private String actionName;
    private String actionTexture;

    // TODO: Implement multi-hex movement

    /** Maximum distance the move can move */
    private int maxLength;

    public MoveAction(String actionName, String actionTexture, int maxLength) {
        this.actionName = actionName;
        this.actionTexture = actionTexture;
        this.maxLength = maxLength;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public String getActionTexture() {
        return actionTexture;
    }

    /** Returns true if the move action is valid */
    @Override
    public boolean isValidAction(Command action) {
        return action.getDistance() <= maxLength;
    }

    /** Applies the move action and returns the new intermediate board */
    @Override
    public IntermediateBoard applyAction(Command action, IntermediateBoard board) {
        HexBoard<List<BasicUnit>> newBoard = board.getUnits();
        for (int n = 0; n < newBoard.getHex(action.getStartPosition()).get().size(); n++) {
            if (newBoard.getHex(action.getStartPosition()).get().get(n).getId() == action.getUnitId()) {
                List<BasicUnit> startHex = newBoard.getHex(action.getStartPosition()).get();
                startHex.remove(n);
                newBoard.setHex(action.getStartPosition(), startHex);
                List<BasicUnit> targetHex = newBoard.getHex(action.getTargetPosition()).get();
                targetHex.add(newBoard.getHex(action.getStartPosition()).get().get(n));
                newBoard.setHex(action.getTargetPosition(), targetHex);
                break;
            }
        }
        board.getUnits().setHex(action.getStartPosition(), newBoard.getHex(action.getStartPosition()).get());
        board.getUnits().setHex(action.getTargetPosition(), newBoard.getHex(action.getTargetPosition()).get());
        return board;
    }
}
