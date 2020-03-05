package com.mygdx.game.models;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class IntermediateBoard {
    private HexBoard<Map<Integer, List<BasicUnit>>> units;
    private HexBoard<Map<Integer, Integer>> strengths;
    private Map<Integer, List<BasicUnit>> unitsByPlayer;
    private HexMap newBoard;
    private Set<Integer> playerIds;

    public IntermediateBoard(HexMap board) {
        newBoard = board;
        units = new HexBoard<>(board.getNumRows(), board.getNumColumns());
        strengths = new HexBoard<>(board.getNumColumns(), board.getNumRows());
        playerIds = board.getPlayerIds();

        for (int n: playerIds) {
            for (int y = 0; y < board.getNumColumns(); y++) {
                for (int x = 0; x < board.getNumRows(); x++) {
                    if (board.getUnits().getHex(new Position(y, x)).isPresent() && board.getUnits().getHex(new Position(y, x)).get().getPid() == n) {
                        Map<Integer, List<BasicUnit>> newMap = new HashMap<>();
                        List<BasicUnit> newList = new ArrayList<>();
                        if (board.getUnit(new Position(y, x)).isPresent()) {
                            newList.add(board.getUnit(new Position(y, x)).get());
                        }
                        newMap.put(n, newList);
                        units.setHex(new Position(y, x), newMap);
                    }
                }
            }
        }
        for (int y = 0; y < board.getNumColumns(); y++) {
            for (int x = 0; x < board.getNumRows(); x++) {
                for (int n: playerIds) {
                    Map<Integer, Integer> newMap = new HashMap<>();
                    newMap.put(n, 0);
                    strengths.setHex(new Position(y, x), newMap);
                }
            }
        }
        for (int n: board.getPlayerIds()) {
            List<BasicUnit> playerUnits = new ArrayList<>();
            for (int y = 0; y < board.getNumColumns(); y++) {
                for (int x = 0; x < board.getNumRows(); x++) {
                    if (board.getUnits().getHex(new Position(y, x)).isPresent() && board.getUnits().getHex(new Position(y, x)).get().getId() == n) {
                        playerUnits.add(board.getUnits().getHex(new Position(y, x)).get());
                    }
                }
            }
            unitsByPlayer.put(n, playerUnits);
        }
    }

    public void applyToBoard(IntermediateBoard intermediateBoard) {

    }

    public IntermediateBoard resolveCombat(IntermediateBoard board) {
        return null;
    }

    public HexBoard<Map<Integer, List<BasicUnit>>> getUnits() {
        return units;
    }

    public HexBoard<Map<Integer, Integer>> getStrengths() {
        return strengths;
    }

    public Set<Integer> getPlayerIds() {
        return playerIds;
    }

    public Optional<Integer> getUnitFromId(int unitId, Map<Integer, List<BasicUnit>> unitsByPlayer, Set<Integer> playerIds) {
        Map<List<BasicUnit>, Integer> reverseMap = new HashMap<>();
        for (int n: unitsByPlayer.keySet()) {
            reverseMap.put(unitsByPlayer.get(n), n);
        }

        for (int n: ) {
            if (reverseMap.get) {
                return Optional.of(n);
            }
        }
        return Optional.empty();
    }
}
