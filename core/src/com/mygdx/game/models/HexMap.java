package com.mygdx.game.models;

import java.util.List;

public class HexMap  {

    HexBoard<List<Unit>> units;

    HexBoard<Terrain> terrain;

    HexBoard<Boolean> mapShape;

    List<HexBoard<String>> textures;

    /**
     * Get the units at a position P
     */
    List<Unit> getUnits(Position p){
        return null;
    }

    /**
     *
     */
    //Todo add interactions to the game
    //Interaction getInteraction(Position p);

    /**
     * Get the terrain at a position P
     */
    Terrain getTerrain(Position p){
        return null;
    }

    /**
     * Gets all units for a player with id
     */
    public List<Unit> getUnitsForPlayer(int playerNum){
        return null;
    }

    /**
     * Returns a List of Strings which refer to the file location of the textures which relate to each layer of the map
     */
    List<HexBoard<String>> getTextures(){
        return null;
    }

    /**
     * Returns a HexMap of Booleans which describes which tiles are in the map
     */
    HexBoard<Boolean> getMapShape(){
        return null;
    }

    public static HexMap getHexMapFromConfig(){
        return null;
    }

    /**
     * Get possible moves
     */
    /*List<Position> getPossibleMoves(Action action, int unitId){
        return null;
    }*/

    /**
     * returns a list of potentially blocked commands
     */
    /*List<Command> getBlockedCommands(){
        return null;
    }*/

}
