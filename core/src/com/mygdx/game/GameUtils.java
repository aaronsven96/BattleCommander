package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameUtils {
    public static Vector2 getVector2(Vector3 vector3){
        return new Vector2(vector3.x, vector3.y);
    }


}