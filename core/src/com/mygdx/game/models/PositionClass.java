package com.mygdx.game.models;

public class PositionClass implements Position {
    private int x;
    private int y;

    public PositionClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}