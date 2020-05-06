package com.mygdx.game.models;


import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Optional;
import java.util.HashSet;


/**
 * An intermediary between two HexMaps, which actions are applied to. Resolves into a HexMap.
 */
@Setter
@Getter
public class IntermediateBoard {
    private HexBoard<Terrain> terrain;
    private Set<Integer> playerIds;
    private HexBoard<Map<Integer, List<BasicUnit>>> units;
    private HexBoard<Battle> battles;
    private final int rows;
    private final int columns;
    private HexBoard<Boolean> mapShape;
    private Map<Integer, Position> idToPosition; // {ID=Position, ...}
    private HexMap originalBoard;

    public IntermediateBoard(HexMap board) {
        originalBoard = new HexMap(board);
        terrain = board.getTerrain();
        playerIds = board.getPlayerIds();
        rows = board.getRows();
        columns = board.getColumns();
        mapShape = board.getMapShape();
        idToPosition = board.getIdToPosition();
        units = new HexBoard<>(rows, columns);
        battles = new HexBoard<>(rows, columns);

        for (BasicUnit unit : board.getUnits()) {
            if (mapShape.getHex(board.getUnits().getCursor()).get()) {
                if (board.getUnit(board.getUnits().getCursor()).isPresent()) {
                    List<BasicUnit> unitsList = new ArrayList<>();
                    Map<Integer, List<BasicUnit>> unitsMap = new HashMap<>();
                    unitsList.add(unit);
                    unitsMap.put(unit.getPid(), unitsList);
                    units.setHex(board.getUnits().getCursor(), unitsMap);
                }
                battles.setHex(units.getCursor(), new Battle());
            }
        }
    }

    /**
     * Resolves the IntermediateBoard into a HexMap.
     *
     * @return a HexMap from the IntermediateMap
     */
    public HexMap toHexMap() {
        for (Map<Integer, List<BasicUnit>> hex : units) {
            BasicUnit newHex = null;
            for (Map.Entry<Integer, List<BasicUnit>> entry : hex.entrySet()) {
                Integer playerId = entry.getKey();
                List<BasicUnit> value = entry.getValue();
                for (BasicUnit unit : value) {
                    if (unit != null) {
                         if (newHex != null) {
                             System.out.println("There's more than one unit on this hex, this isn't good.");
                         }
                         newHex = unit;
                    }
                }
            }
            originalBoard.getUnits().setHex(units.getCursor(), newHex);
        }
        return originalBoard;
    }

    /**
     * Resolves the IntermediateBoard's battles.
     */
    public void resolveBattles() {
        for (Battle battle : battles) {
            battle.resolveBattle();
        }
    }

    /**
     * Returns the Battle at a position.
     *
     * @param position the position of the battle
     * @return the Battle at position
     */
    public Optional<Battle> getBattle(Position position) {
        return battles.getHex(position);
    }

    /**
     * Returns the Terrain at a position.
     *
     * @param position the position of the battle
     * @return the Terrain at position
     */
    public Optional<Terrain> getHexTerrain(Position position) {
        return terrain.getHex(position);
    }

    /**
     * Returns the BasicUnit that matches an id.
     *
     * @param id the id of the unit you are searching for
     * @return the BasicUnit that matches id
     */
    public Optional<BasicUnit> getUnitFromId(int id) {
        for (Map<Integer, List<BasicUnit>> unitMap : units) {
            for (Map.Entry<Integer, List<BasicUnit>> entry : unitMap.entrySet()) {
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

    /**
     * Returns a set of the ids of the units a player controls.
     *
     * @param playerId the id of the player
     * @return a set of the ids of the units the player controls
     */
    public Set<Integer> getPlayerUnitIds(int playerId) {
        Set<Integer> playerUnitIds = new HashSet<>();
        for (Map<Integer, List<BasicUnit>> hex : units) {
            if (hex.get(playerId) != null) {
                hex.get(playerId).forEach(unit -> playerUnitIds.add(unit.getId()));
            }
        }
        return playerUnitIds;
    }

    /**
     * Checks if a position is valid.
     *
     * @param position the position to check
     * @return true if it is valid
     */
    public boolean isValidPosition(Position position) {
        if (mapShape.getHex(position).isPresent()) {
            return mapShape.getHex(position).get();
        }
        return false;
    }

    /**
     * Checks if a unit is valid.
     *
     * @param id the id of the unit to check
     * @return true if it is a valid unit
     */
    public boolean isValidUnit(int id) {
        for (Map<Integer, List<BasicUnit>> unitMap : units) {
            for (Map.Entry<Integer, List<BasicUnit>> entry : unitMap.entrySet()) {
                List<BasicUnit> unitList = entry.getValue();
                for (BasicUnit unit : unitList) {
                    if (unit.getId() == id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the IDs are in proximity, false otherwise
     *
     * @param id1   the first ID
     * @param id2   the second ID
     * @param range the range
     * @return true if the IDs are in proximity, false otherwise
     */
    public boolean isInProximity(int id1, int id2, int range) {
        if (idToPosition.containsKey(id1) && idToPosition.containsKey(id2)) {
            return units.isInProximity(idToPosition.get(id1), idToPosition.get(id2), range);
        }
        return false;
    }
}
