package com.mygdx.game;

import com.mygdx.game.models.Command;
import com.mygdx.game.models.HexMap;
import com.mygdx.game.models.Orders;
import com.mygdx.game.models.PlayerOrders;
import com.mygdx.game.models.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoveAdapter {



    private Orders orders;

    private Position currentPos;

    private int currentUnit;

    private HexMap currentMap;

    private int playerId;

    private Integer selectedUnit;

    private Map<Integer, ArrayList<Command>> tempOrders = new HashMap<>();

    private MoveAdapter(int playerId, HexMap currentMap){
        this.playerId = playerId;
    }

    public void rightClickHex(Position p){
        if(selectedUnit == null){
            if(H)
        }
        else{

        }
    }

    public void leftClickHex(Position p){
        selectedUnit = null;
    }

    public void cancelOrder(){

    }

    public void selectAction(String string){

    }

    public void cancelCommand(){

    }

    public PlayerOrders getCurrentOrders(){
        return convertToPlayerOrders();
    }

    private PlayerOrders convertToPlayerOrders(){
        Map<Integer, Orders> playerOrders = new HashMap<>();
        for(Integer unitId: tempOrders.keySet()){
            playerOrders.put(unitId, new Orders(unitId, tempOrders.get(unitId), new Position(1,1)));
        }
        return new PlayerOrders(playerId, playerOrders);
    }
}
