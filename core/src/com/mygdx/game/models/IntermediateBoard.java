package com.mygdx.game.models;

import java.util.List;
import java.util.ArrayList;

public class IntermediateBoard {
    private HexBoard<List<BasicUnit>> units;
    private HexBoard<int[]> strengths;
    private HexMap newBoard;

    public IntermediateBoard(HexMap board, HexBoard<List<BasicUnit>> units) {
        this.units = units;
        this.strengths = strengths;
        newBoard = new HexMap(board);
    }

    public void applyToBoard(IntermediateBoard intermediateBoard) {

    }

    public IntermediateBoard resolveCombat(IntermediateBoard board) {
        return null;
    }

}
