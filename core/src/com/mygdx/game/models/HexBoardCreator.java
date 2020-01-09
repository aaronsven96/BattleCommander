package com.mygdx.game.models;

import java.util.function.Function;

public interface HexBoardCreator<T> {

    /**
     * Creates a HexBoard from a Boardable object
     */
    HexBoard<T> createHexBoard(String configurationFile, Function<String, T> configurationToObject);
}
