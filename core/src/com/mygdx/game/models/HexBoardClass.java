package com.mygdx.game.models;

import java.util.*;

/*
 * The hex board of Type T that we will use as the map of the game. T will be a terrain
 */
public class HexBoardClass<T> implements HexBoard {
    private T[][] board;
    private int xLength;
    private int yLength;

    public HexBoardClass(T o, int xLength, int yLength) {
        board = (T[][]) new Object[xLength][yLength];
        this.xLength = xLength;
        this.yLength = yLength;
    }

    /*
     * A function that returns the object at position x,y
     */
    @Override
    public T getHex(Position p) {
        return p.getX() >= 0 && p.getX() <= xLength - 1 && p.getY() >= 0 && p.getY() <= yLength - 1 ? board[p.getX()][p.getY()] : null;
    }

    /*
     * A function that returns a list of neighbors to an hex at x,y
     */
    @Override
    public List getHexNeighbors(Position p) {
        List<T> neighbors = new ArrayList<>();

        // Top left neighbor
        if (p.getX() >= 1) neighbors.add(board[p.getX() - 1][p.getY()]);

        // Top right neighbor
        if (p.getX() >= 1 && p.getY() <= yLength - 2) neighbors.add(board[p.getX() - 1][p.getY() + 1]);

        // Left neighbor
        if (p.getY() >= 1) neighbors.add(board[p.getX()][p.getY() - 1]);

        // Right neighbor
        if (p.getY() <= yLength - 2) neighbors.add(board[p.getX()][p.getY() + 1]);

        // Bottom left neighbor
        if (p.getX() <= xLength - 2 && p.getY() >= 1) neighbors.add(board[p.getX() + 1][p.getY() - 1]);

        // Bottom right neighbor
        if (p.getX() <= xLength - 2) neighbors.add(board[p.getX() + 1][p.getY()]);

        return neighbors;
    }

    /*
     * A function that sets a hex at position x,y
     */
    @Override
    public boolean setHex(Position p, Object hex) {
        if (p.getX() >= 0 && p.getX() <= xLength - 1 && p.getY() >= 0 && p.getY() <= yLength - 1) {
            board[p.getX()][p.getY()] = (T) hex;
            return true;
        }
        return false;
    }

    public T[][] getBoard() {
        return board;
    }

    public int getxLength() {
        return xLength;
    }

    public int getyLength() {
        return yLength;
    }

}