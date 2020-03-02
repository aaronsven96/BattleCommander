package com.mygdx.game.models;

import java.util.HashMap;
import java.util.Map;

public class OrderGatherer {

    private Map<Integer, Orders> unitOrders = new HashMap<>();

    private final HexMap map;

    OrderGatherer(HexMap map){
        this.map = map;
    }

    public boolean submitOrders(Orders orders){
        if(checkOrderValidity(orders)){
            unitOrders.put(orders.getUnitId(), orders);
            return true;
        }
        return false;
    }

    public boolean allOrders(){
        return unitOrders.keySet().containsAll(map.getTextures());
    }

    private boolean checkOrderValidity(Orders orders){
        return true;
    }
}
