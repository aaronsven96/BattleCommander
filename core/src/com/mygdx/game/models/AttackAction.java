package com.mygdx.game.models;

import java.util.List;
import java.util.Map;

public class AttackAction implements UnitAction {

    private String name;
    private String texture;
    private int[] damage;
    private int maxRange;

    public AttackAction(String name, String texture, int[] damage) {
        this.name = name;
        this.texture = texture;
        this.damage = damage;
        maxRange = damage.length;

    }

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public String getActionTexture() {
        return texture;
    }

    @Override
    public boolean isValidAction(Command action, IntermediateBoard board) {
        return board.getUnits().getHex(action.getTargetPosition()).isPresent() && action.hasLineOfSight() && getRangedDamage(action.getDistance()) > 0;
    }

    @Override
    public boolean applyAction(Command action, IntermediateBoard board) {
        if (isValidAction(action, board)) {
            for (Map.Entry<Integer, List<BasicUnit>> entry : board.getUnits().getHex(action.getTargetPosition()).get().entrySet()) {
                Integer playerId = entry.getKey();
                List<BasicUnit> units = entry.getValue();
                units.removeIf(unit -> unit.damage(getRangedDamage(action.getDistance())));
            }
            return true;
        }
        return false;
    }

    private int getRangedDamage(int distance) {
        if (distance <= damage.length) {
            return damage[distance - 1];
        } else {
            return 0;
        }
    }
}
