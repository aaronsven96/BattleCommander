package com.mygdx.game.models;

import java.util.*;

/**
 * The hex board of Type T that we will use as the map of the game. T will be a terrain.
 */
public class HexBoardClass<T> implements HexBoard {
    private T[][] board;
    private int numRows, numColumns;

    public HexBoardClass(T o, int numRows, int numColumns) {
        if (numRows < 1 || numColumns < 1) throw new IllegalArgumentException("Number of rows/columns must be positive");

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
    @Override
    public T getHex(Position p) {
        // Returns null if the position is outside the Array bounds
        return p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1 ? board[p.getX()][p.getY()] : null;
    }

    /**
     * Returns a list of neighbors to a hex at x,y.
     *
     * @param p the position
     * @return a list of neighbors to a hex at x,y
     */
    @Override
    public List getHexNeighbors(Position p) {
        List<T> neighbors = new ArrayList<>();

        // invalid position check
        if (!(p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1)) return neighbors;

        // top left neighbor
        if (p.getX() >= 1) neighbors.add(board[p.getX() - 1][p.getY()]);

        // top right neighbor
        if (p.getX() >= 1 && p.getY() <= numColumns - 2) neighbors.add(board[p.getX() - 1][p.getY() + 1]);

        // left neighbor
        if (p.getY() >= 1) neighbors.add(board[p.getX()][p.getY() - 1]);

        // right neighbor
        if (p.getY() <= numColumns - 2) neighbors.add(board[p.getX()][p.getY() + 1]);

        // bottom left neighbor
        if (p.getX() <= numRows - 2 && p.getY() >= 1) neighbors.add(board[p.getX() + 1][p.getY() - 1]);

        // bottom right neighbor
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
    @Override
    public boolean setHex(Position p, Object hex) {
        // null values for the hex Object can be passed in and set if the position is valid (i.e., they work as expected)
        if (p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1) {
            board[p.getX()][p.getY()] = (T) hex;
            return true;
        }
        return false; // returns false if the position is outside the Array bounds
    }

    /**
     * Returns the board.
     *
     * @return the board
     */
    public T[][] getBoard() {
        return board;
    }

}