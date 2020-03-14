package com.mygdx.game.models;

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
    public boolean isValidAction(Command action) {
        if (action.hasLineOfSight() && action.getDistance() <= maxRange && action.getDistance() > 0 && damage[action.getDistance()] > 0) {
            return true;
        }
        return false;
    }

    @Override
    public IntermediateBoard applyAction(Command action, IntermediateBoard board) {
        return null;
    }

    private int calculateRangedDamage(int distance, int[] damage) {
        if (distance <= damage.length) {
            return damage[distance-1];
        } else {
            return 0;
        }
    }
}
