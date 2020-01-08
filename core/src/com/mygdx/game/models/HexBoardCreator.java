package com.mygdx.game.models;

public interface HexBoardCreator {

    /**
     * Creates a HexBoard from a Boardable object
     */
    HexBoard<Boardable> createHexBoard(String configurationFile);
}
