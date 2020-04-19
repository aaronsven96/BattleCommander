package com.mygdx.game.models;

import java.util.List;
import java.util.Map;

public class MoveAction implements UnitAction {

    /** Action's name and texture */
    private String actionName;
    private String actionTexture;

    public MoveAction(String actionName, String actionTexture) {
        this.actionName = actionName;
        this.actionTexture = actionTexture;
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
    public boolean isValidAction(Command action, IntermediateBoard board) {
        return board.getTerrain().getHex(action.getStartPosition()).isPresent() && board.getTerrain().getHex(action.getTargetPosition()).isPresent();
    }

    /** Applies the move action and returns the new intermediate board */
    @Override
    public void applyAction(Command action, IntermediateBoard board) {
        HexBoard<Map<Integer, List<BasicUnit>>> newBoard = board.getUnits();
        for (int i = 0; i < newBoard.getHex(action.getStartPosition()).get().size(); i++) {
            if (newBoard.getHex(action.getStartPosition()).get().get(i).getId() == action.getUnitId()) {
                List<BasicUnit> startHex = newBoard.getHex(action.getStartPosition()).get();
                startHex.remove(i);
                newBoard.setHex(action.getStartPosition(), startHex);
                List<BasicUnit> targetHex = newBoard.getHex(action.getTargetPosition()).get();
                targetHex.add(newBoard.getHex(action.getStartPosition()).get().get(i));
                newBoard.setHex(action.getTargetPosition(), targetHex);
                break;
            }
        }
        board.getUnits().setHex(action.getStartPosition(), newBoard.getHex(action.getStartPosition()).get());
        board.getUnits().setHex(action.getTargetPosition(), newBoard.getHex(action.getTargetPosition()).get());
     }
}
