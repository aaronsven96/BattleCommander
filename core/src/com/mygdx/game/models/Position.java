package com.mygdx.game.models;

/**
 * A class that represents an x,y position on the board.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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
        return "x: " + getX() + "y: " + getY();
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