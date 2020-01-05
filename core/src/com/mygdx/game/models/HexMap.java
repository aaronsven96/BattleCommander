package com.mygdx.game.models;

import java.util.List;

public interface HexMap {

    /**
     * Get the units at a position P
     */
    List<Unit> getUnits(Position p);

    /**
     *
     */
    //Todo add interactions to the game
    //Interaction getInteraction(Position p);

    /**
     * Get the terrain at a position P
     */
    Terrain getTerrain(Position p);

    /**
     * Gets all units for a player with id
     */
    public List<Unit> getUnitsForPlayer(int playerNum);

    /**
     * Returns a List of Strings which refer to the file location of the textures which relate to each layer of the map
     */
    List<HexBoard<String>> getTextures();

    /**
     * Returns a HexMap of Booleans which describes which tiles are in the map
     */
    HexBoard<Boolean> getMapShape();

    /**
     * Get possible moves
     */
    List<Position> getPossibleMoves(Action action, int unitId);

    /**
     * returns a list of potentially blocked commands
     */
    List<Command> getBlockedCommands();

}
