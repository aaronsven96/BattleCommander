package com.mygdx.game.models;

import java.util.List;
import java.util.ArrayList;

public class IntermediateBoard {
    private HexBoard<List<BasicUnit>> units;
    private HexBoard<ArrayList> strengths;
    private HexMap newBoard;

    public IntermediateBoard(HexMap board, HexBoard<List<BasicUnit>> units) {
        newBoard = new HexMap(board);
        this.units = units;
        this.strengths = new HexBoard<ArrayList>(board.getCols(), board.getRows());
    }

    public void applyToBoard(IntermediateBoard intermediateBoard) {

    }

    public IntermediateBoard resolveCombat(IntermediateBoard board) {
        return null;
    }

    public HexBoard<List<BasicUnit>> getUnits() {
        return units;
    }
}
