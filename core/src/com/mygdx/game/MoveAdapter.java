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

    private String currentAction;

    private HexMap currentMap;


    private int playerId;

    private Integer selectedUnit;

    private Map<Integer, ArrayList<Command>> tempOrders = new HashMap<>();

    public MoveAdapter(int playerId, HexMap currentMap){
        this.playerId = playerId;
        this.currentMap = currentMap;
    }

    public void rightClickHex(Position p){
        selectedUnit = null;
    }

    public void leftClickHex(Position p){
        System.out.println("left");
        // If theres no selected unit then select a unit if possible
        if(selectedUnit == null){
            //Todo add hexmap support
            /*Optional<BasicUnit> unit = currentMap.getUnit(p);
            if(unit.isPresent() && unit.get().getPid() == playerId){
                selectedUnit = unit.get().getPid();
            }*/
            if(p.getY() == 1 && p.getX() == 1){
                selectedUnit = 1;
                System.out.println("selected Unit");
            }
        }
        else{
            Command newCommand = new Command(currentAction, selectedUnit, null, p);
            //Todo Add validity check
            tempOrders.putIfAbsent(selectedUnit, new ArrayList<>());
            tempOrders.get(selectedUnit).add(newCommand);
            /*ArrayList<Command> commands = tempOrders.get(selectedUnit);
            // Revert orders back to this square
            if(commands.stream().anyMatch(command -> command.getTargetPosition() == p)){
                while(commands.get(0).getTargetPosition() != p){
                    commands.remove(0);
                }
            }
            else{
                tempOrders.get(selectedUnit).add(new Command("move", selectedUnit, p));
            }*/
        }
    }

    public void cancelOrder() {
        if (selectedUnit != null) {
            tempOrders.clear();
        }
    }

    public void selectAction(String action){
        currentAction = action;
    }

    public void cancelCommand(){
        tempOrders.clear();
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
