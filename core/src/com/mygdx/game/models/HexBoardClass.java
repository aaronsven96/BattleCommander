package com.mygdx.game.models;

import jdk.internal.jline.internal.Nullable;

import java.util.*;

/*
 * The hex board of Type T that we will use as the map of the game. T will be a terrain
 */
public class HexBoardClass<T> implements HexBoard {
    private T[][] board;
    private int numRows, numColumns;

    public HexBoardClass(T o, int numRows, int numColumns) {
        board = (T[][]) new Object[numRows][numColumns];
        this.numRows = numRows; // Number of rows
        this.numColumns = numColumns; // Number of columns
    }

    /*
     * Returns the object at position x,y
     */
    @Nullable
    @Override
    public T getHex(Position p) {
        // Returns null if the position is outside the Array bounds
        return p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1 ? board[p.getX()][p.getY()] : null;
    }

    /*
     * Returns a list of neighbors to a hex at x,y
     */
    @Override
    public List getHexNeighbors(Position p) {
        List<T> neighbors = new ArrayList<>();

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

    /*
     * Sets a hex at position x,y
     */
    @Nullable
    @Override
    public boolean setHex(Position p, Object hex) {
        if (p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1) {
            board[p.getX()][p.getY()] = (T) hex;
            return true;
        }
        return false; // Returns false if the position is outside the Array bounds or if a null Object is passed in
    }

    /*
     * Returns the board
     */
    public T[][] getBoard() {
        return board;
    }

}