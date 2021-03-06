package com.mygdx.game.input;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.models.Position;

import java.util.Optional;

public class HexBoardAdapter {
    private final Vector3[][] centers;
    int size;

    HexBoardAdapter(Vector3[][] centers, int size){
        this.centers = centers;
        this.size = size;
    }

    public Optional<Position> findClosestHex(Vector3 pos){
        Position hex;
        for(int x =0; x< centers.length; x++){
            for (int y = 0; y < centers.length; y++) {
                Vector3 vec = centers[x][y];
                if (vec.dst(pos) < size) {
                    return Optional.of(new Position(x,y));
                }
            }
        }
        return Optional.empty();
    }
}
