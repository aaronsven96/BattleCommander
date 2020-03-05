package com.mygdx.game.models;

/**
 * A class that represents a position (y,x) on the board.
 */
public class Position {
    private int y;
    private int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Returns the x-position.
     *
     * @return the x-position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-position.
     *
     * @return the y-position
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "y: " + getY() + "x: " + getX();
    }

    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(Position.class)){
            throw new IllegalArgumentException();
        }
        Position position = (Position) o;
        return position.x == this.x && position.y == this.y;
    }
}