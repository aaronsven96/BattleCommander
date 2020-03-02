package com.mygdx.game.models;

import java.util.List;

public class IntermediateBoard {
    private HexBoard<List<BasicUnit>> units;
    private HexBoard<List<Integer>> strengths;
    private List<Integer> playerIds;
    private HexMap newBoard;

    public IntermediateBoard(HexMap board, HexBoard<List<BasicUnit>> units, List<Integer> playerIds) {
        newBoard = new HexMap(board);
        this.units = units;
        this.strengths = new HexBoard<List<Integer>>(board.getNumColumns(), board.getNumRows());
        this.playerIds = playerIds;
    }

    public void applyToBoard(IntermediateBoard intermediateBoard) {

    }

    public IntermediateBoard resolveCombat(IntermediateBoard board) {
        return null;
    }

    public HexBoard<List<BasicUnit>> getUnits() {
        return units;
    }

    public HexBoard<List<Integer>> getStrengths() {
        return strengths;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }
}
