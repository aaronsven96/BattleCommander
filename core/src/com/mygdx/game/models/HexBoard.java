package com.mygdx.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the hex board of Type T that we will use as the map of the game. T will be a terrain.
 */
public class HexBoard<T> {
    private T[][] board;
    private int numRows, numColumns;

    public HexBoard(T obj, int numRows, int numColumns) {
        if (numRows < 1 || numColumns < 1) throw new IllegalArgumentException("number of rows/columns must be positive");

        board = (T[][]) new Object[numRows][numColumns];
        this.numRows = numRows; // number of rows
        this.numColumns = numColumns; // number of columns
    }

    /**
     * Returns the object at position x,y.
     *
     * @param p the position
     * @return the object at position x,y
     */
    public T getHex(Position p) {
        // Returns null if the position is outside the Array bounds
        // TODO: use Optional class?
        return p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1 ? board[p.getX()][p.getY()] : null;
    }

    /**
     * Returns a List of neighbors to a hex at x,y.
     *
     * @param p the position
     * @return a List of neighbors to a hex at x,y
     */
    public List<T> getHexNeighbors(Position p) {
        List<T> neighbors = new ArrayList<>();

        // Invalid position check
        if (!(p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1)) return neighbors;

        // Top left neighbor
        if (p.getX() >= 1) neighbors.add(board[p.getX() - 1][p.getY()]);

        // Top right neighbor
        if (p.getX() >= 1 && p.getY() <= numColumns - 2) neighbors.add(board[p.getX() - 1][p.getY() + 1]);

        // Left neighbor
        if (p.getY() >= 1) neighbors.add(board[p.getX()][p.getY() - 1]);

        // Right neighbor
        if (p.getY() <= numColumns - 2) neighbors.add(board[p.getX()][p.getY() + 1]);

        // Bottom left neighbor
        if (p.getX() <= numRows - 2 && p.getY() >= 1) neighbors.add(board[p.getX() + 1][p.getY() - 1]);

        // Bottom right neighbor
        if (p.getX() <= numRows - 2) neighbors.add(board[p.getX() + 1][p.getY()]);

        return neighbors;
    }

    /**
     * Sets a hex at position x,y.
     *
     * @param p   the position
     * @param hex the hex
     * @return true if the position is set, false otherwise
     */
    public boolean setHex(Position p, T hex) {
        // Null values for the hex Object can be passed in and set if the position is valid (i.e., they work as expected)
        if (p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1) {
            board[p.getX()][p.getY()] = hex;
            return true;
        }
        return false; // returns false if the position is outside the Array bounds
    }

    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows in the board
     */
    protected int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in the board.
     *
     * @return the number of columns in the board
     */
    protected int getNumColumns() {
        return numColumns;
    }

}