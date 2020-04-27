package com.mygdx.game.models;


import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class IntermediateBoard {
    @Getter
    private HexBoard<Terrain> terrain;
    @Getter
    private Set<Integer> playerIds;
    @Getter
    private HexBoard<Map<Integer, List<BasicUnit>>> units;
    @Getter
    private Map<Integer, List<BasicUnit>> playerUnits;
    @Getter
    private HexBoard<Battle> battles;
    @Getter
    private final int rows;
    @Getter
    private final int columns;
    private HexMap newBoard;
    public IntermediateBoard(HexMap board) {
        newBoard = new HexMap(board);
        terrain = board.getTerrain();
        playerIds = board.getPlayerIds();
        units = new HexBoard<>(board.getNumRows(), board.getNumColumns());
        rows = board.getNumRows();
        columns = board.getNumColumns();
        battles = new HexBoard<>(board.getNumColumns(), board.getNumRows());

        for (int i : board.getPlayerIds()) {
            List<BasicUnit> playerUnitList = new ArrayList<>();
            for (int y = 0; y < board.getNumColumns(); y++) {
                for (int x = 0; x < board.getNumRows(); x++) {
                    if (board.getMapShape().getHex(new Position(y, x)).get() && board.getUnit(new Position(y, x)).isPresent() && board.getUnit(new Position(y, x)).get().getPid() == i) {
                        List<BasicUnit> unitsSet = new ArrayList<>();
                        Map<Integer, List<BasicUnit>> unitsMap = new HashMap<>();
                        if (board.getUnit(new Position(y, x)).isPresent()) {
                            unitsSet.add(board.getUnit(new Position(y, x)).get());
                            unitsMap.put(i, unitsSet);
                            playerUnitList.add(board.getUnit(new Position(y, x)).get());
                        }
                        units.setHex(new Position(y, x), unitsMap);
                    }
                }
            }
            this.playerUnits.put(i, playerUnitList);
        }

        for (Battle battle : battles) {
            battles.setHex(new Position(y, x), new Battle());
        }
    }

    /**
     * Converts the IntermediateBoard into a HexMap.
     *
     * @return a HexMap from the IntermediateMap
     */
    public HexMap applyToBoard() {
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                if (units.getHex(new Position(y, x)).isPresent()) {
                   newBoard.getUnits().setHex(new Position(y, x), (BasicUnit) units.getHex(new Position(y, x)).get().values().toArray()[0]);
                }
            }
        }
        return newBoard;
    }

    /**
     * Resolves the IntermediateBoard's battles.
     */
    public void resolveBattles() {
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                if (battles.getHex(new Position(y, x)).isPresent()) {
                    battles.getHex(new Position(y, x)).get().resolveBattle();
                }
            }
        }
    }

    /**
     * Returns a HexBoard of all Battles.
     *
     * @return a HexBoard of all Battles
     */
    public Optional<Battle> getBattle(Position position) {
        return battles.getHex(position);
    }

    /**
     * Returns a HexBoard of Terrain.
     *
     * @return a HexBoard of Terrain
     */
    public Optional<Terrain> getHexTerrain(Position position) {
        return terrain.getHex(position);
    }

    /**
     * Returns a BasicUnit from an id.
     *
     * @param id the id of the unit you are searching for
     * @return the BasicUnit that matches the id
     */
    public Optional<BasicUnit> getUnitFromId(int id) {
        for (Map<Integer, List<BasicUnit>> unitMap : units) {
            for (Map.Entry<Integer, List<BasicUnit>> entry : unitMap.entrySet()) {
                Integer playerId = entry.getKey();
                List<BasicUnit> unitList = entry.getValue();
                for (BasicUnit unit : unitList) {
                    if (unit.getId() == id) {
                        return Optional.of(unit);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
