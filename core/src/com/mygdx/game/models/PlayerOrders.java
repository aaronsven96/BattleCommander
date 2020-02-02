package com.mygdx.game.models;

import java.util.HashMap;
import java.util.Map;

public class PlayerOrders {

    private int playerNum;

    private Map<Integer, Orders> unitOrders;

    public PlayerOrders(int playerNum, Map<Integer, Orders> unitOrders){
        this.unitOrders = unitOrders;
        this.playerNum = playerNum;
    }

    public Map<Integer, Orders> getUnitOrders() {
        return unitOrders;
    }

    public int getPlayerNum() {
        return playerNum;
    }
}
