package com.mygdx.game;

import com.mygdx.game.models.Command;
import com.mygdx.game.models.Position;

import java.util.Deque;
import java.util.Map;

public class MoveAdapter {

    private Position currentPos;

    private int currentUnit;

    private Map<Integer, Deque<Command>> orders;

    public void rightClickHex(Position p){

    }

    public void leftClickHex(Position p){

    }

    public void cancelOrder(){
    }

    public void selectAction(String string){

    }

    private void cancelCommand(){

    }
}
