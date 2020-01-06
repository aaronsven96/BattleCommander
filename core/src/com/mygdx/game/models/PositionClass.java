package com.mygdx.game.models;

/*
 * Represents an x,y position on the board
 */
public class PositionClass implements Position {
    private int x;
    private int y;

    public PositionClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     * Gets the x-position
     */
    @Override
    public int getX() {
        return x;
    }

    /*
     * Gets the y-position
     */
    @Override
    public int getY() {
        return y;
    }

}