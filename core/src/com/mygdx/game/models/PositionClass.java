package com.mygdx.game.models;

/**
 * A class that represents an x,y position on the board.
 */
public class PositionClass implements Position {
    private int x;
    private int y;

    public PositionClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-position.
     *
     * @return the x-position
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Returns the y-position.
     *
     * @return the y-position
     */
    @Override
    public int getY() {
        return y;
    }

}