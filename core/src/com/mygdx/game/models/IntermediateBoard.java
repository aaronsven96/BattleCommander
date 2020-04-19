package com.mygdx.game.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class IntermediateBoard {
    private HexBoard<Terrain> terrain;
    private Set<Integer> playerIds;
    private HexBoard<Map<Integer, List<BasicUnit>>> units;
    private Map<Integer, List<BasicUnit>> playerUnits;
    private HexBoard<Battle> battles;
    private final int rows;
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

        for (int y = 0; y < board.getNumColumns(); y++) {
            for (int x = 0; x < board.getNumRows(); x++) {
                if (units.getHex(new Position(y, x)).isPresent()) {
                    battles.setHex(new Position(y, x), new Battle(units.getHex(new Position(y, x)).get()));
                }
            }
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
                if (getUnits().getHex(new Position(y, x)).isPresent()) {
                   newBoard.getUnits().setHex(new Position(y, x), (BasicUnit) getUnits().getHex(new Position(y, x)).get().values().toArray()[0]);
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
     * Returns a HexBoard of Maps that map player ids to units.
     *
     * @return a HexBoard of Maps that map player ids to units
     */
    public HexBoard<Map<Integer, List<BasicUnit>>> getUnits() {
        return units;
    }

    /**
     * Returns a HexBoard of all Battles.
     *
     * @return a HexBoard of all Battles
     */
    public HexBoard<Battle> getBattles() {
        return battles;
    }

    /**
     * Returns a Set of all player ids.
     *
     * @return a Set of all player ids
     */
    public Set<Integer> getPlayerIds() {
        return playerIds;
    }

    /**
     * Returns a HexBoard of Terrain.
     *
     * @return a HexBoard of Terrain
     */
    public HexBoard<Terrain> getTerrain() {
        return terrain;
    }

    /**
     * Returns the number of columns in the map.
     *
     * @return the number of columns in the map
     */
    public int getNumColumns() {
        return columns;
    }

    /**
     * Returns the number of rows in the map.
     *
     * @return the number of rows in the map
     */
    public int getNumRows() {
        return rows;
    }
}
