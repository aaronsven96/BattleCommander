package com.mygdx.game.models;

import java.util.List;

public interface HexMap {
    List<Unit> getUnits(Position p);
    Interaction getInteraction(Position p);
    Terrain getTerrain(Position p);
}
