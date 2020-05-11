package com.mygdx.game.models;


import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private HexBoard<List<BasicUnit>> units;
    private HexBoard<Battle> battles;
    private final int rows;
    private final int columns;
    private HexBoard<Boolean> mapShape;
    private HexMap originalBoard;

    public IntermediateBoard(HexMap board) {
        originalBoard = new HexMap(board);
        terrain = board.getTerrain();
        playerIds = board.getPlayerIds();
        rows = board.getRows();
        columns = board.getColumns();
        mapShape = board.getMapShape();
        units = new HexBoard<>(rows, columns);
        battles = new HexBoard<>(rows, columns);

        for (BasicUnit unit : board.getUnits()) {
            if (isHex(board.getUnits().getCursor())) {
                List<BasicUnit> unitsList = new ArrayList<>();
                if (board.getUnit(board.getUnits().getCursor()).isPresent()) {
                    unitsList.add(unit);
                    units.setHex(board.getUnits().getCursor(), unitsList);
                }
                battles.setHex(units.getCursor(), new Battle(units.getCursor()));
            }
        }
    }

    /**
     * Resolves the IntermediateBoard into a HexMap.
     *
     * @return a HexMap from the IntermediateMap
     */
    public HexMap toHexMap() throws Exception {
        for (List<BasicUnit> hex : units) {
            BasicUnit newHex = null;
            for (BasicUnit unit : hex) {
                if (unit != null) {
                     if (newHex != null) {
                         throw new Exception("too many units on a hex");
                     }
                     newHex = unit;
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
        HexBoard<Retreat> retreats = new HexBoard<>(rows, columns);
        for (Battle battle : battles) {
             if (isHex(battles.getCursor())) {
                 retreats.setHex(battles.getCursor(), battle.resolve());
             }
        }
        for (Retreat retreat : retreats) {
            if (isHex(retreats.getCursor())) {
                units = retreat.resolve(units);
            }
        }
        for (List<BasicUnit> hex : units) {
            if (hex.size() > 1) {
                hex.clear();
            }
        }
    }

    /**
     * Returns the Battle at a position.
     *
     * @param position the position to get
     * @return the Battle at position
     */
    public Optional<Battle> getBattle(Position position) {
        return battles.getHex(position);
    }

    /**
     * Sets the battle at a position.
     *
     * @param position the position to set
     * @param battle the new battle
     */
    public void setBattle(Position position, Battle battle) {
        battles.setHex(position, battle);
    }

    /**
     * Returns the Terrain at a position.
     *
     * @param position the position to get
     * @return the Terrain at position
     */
    public Optional<Terrain> getHexTerrain(Position position) {
        return terrain.getHex(position);
    }

    /**
     * Sets the terrain at a position.
     *
     * @param position the position to set
     * @param terrain the new terrain
     */
    public void setTerrain(Position position, Terrain terrain) {
        this.terrain.setHex(position, terrain);
    }

    /**
     * Returns the BasicUnit that matches an id.
     *
     * @param id the id of the unit you are searching for
     * @return the BasicUnit that matches id
     */
    public Optional<BasicUnit> getUnitFromId(int id) {
        for (List<BasicUnit> hex : units) {
            for (BasicUnit unit : hex) {
                if (unit.getId() == id) {
                    return Optional.of(unit);
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
        for (List<BasicUnit> hex : units) {
            for (BasicUnit unit : hex) {
                if (unit.getPid() == playerId) {
                    playerUnitIds.add(unit.getId());
                }
            }
        }
        return playerUnitIds;
    }

    /**
     * Checks if a position is usable on the board.
     *
     * @param position the position to check
     * @return true if it is usable
     */
    public boolean isHex(Position position) {
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
        for (List<BasicUnit> hex : units) {
            for (BasicUnit unit : hex) {
                if (unit.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
