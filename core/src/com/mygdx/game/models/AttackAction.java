package com.mygdx.game.models;

public class AttackAction implements UnitAction {

    private String name;
    private String texture;
    private int distance;
    private int maxRange;
    private int damage;
    private boolean hasSight;
    boolean isValidAction;

    public AttackAction(String name, String texture, Position startPosition, Position endPosition, int maxRange,
                        int[] damage) {
        this.name = name;
        this.texture = texture;
        distance = calculateDistance(startPosition, endPosition);
        this.maxRange = maxRange;
        this.damage = calculateRangedDamage(distance, damage);
        hasSight = checkHasSight(startPosition, endPosition);
    }

    private static int calculateDistance(Position startPosition, Position endPosition) {
        return 0;
    }

    private static boolean checkHasSight(Position startPosition, Position endPosition) {
        return false;
    }

    private static int calculateRangedDamage(int distance, int[] damage) {
        if (distance <= damage.length) {
            return damage[distance-1];
        } else {
            return 0;
        }
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
    public boolean isValidAction() {

        if (damage <= 0){
            return false;
        }
        return false;
    }

    @Override
    public boolean applyAction() {
        return false;
    }
}
