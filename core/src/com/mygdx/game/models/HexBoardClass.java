package com.mygdx.game.models;

import java.util.*;

/*
 * The hex board of Type T that we will use as the map of the game. T will be a terrain
 */
public class HexBoardClass<T> implements HexBoard {
    private Object[][] board;
    int xLength;
    int yLength;

    public HexBoardClass(Object o, int xLength, int yLength) {
        board = new Object[xLength][yLength];
        this.xLength = xLength;
        this.yLength = yLength;

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                board[i][j] = "" + i + j;
            }
        }
    }

    /*
     * A function that returns the object at position x,y
     */
    @Override
    public Object getHex(Position p) {
        return board[p.getX()][p.getY()];
    }

    public void setBoard(Object[][] board) {
        this.board = board;
    }

    /*
     * A function that returns a list of neighbors to an hex at x,y
     */
    @Override
    public List getHexNeighbors(Position p) {
        List<Object> neighbors = new ArrayList<>();

        // Top left neighbor
        if (p.getX() >= 1) neighbors.add(board[p.getX() - 1][p.getY()]);
        else neighbors.add(null);

        // Top right neighbor
        if (p.getX() >= 1 && p.getY() <= yLength - 2) neighbors.add(board[p.getX() - 1][p.getY() + 1]);
        else neighbors.add(null);

        // Left neighbor
        if (p.getY() >= 1) neighbors.add(board[p.getX()][p.getY() - 1]);
        else neighbors.add(null);

        // Right neighbor
        if (p.getY() <= yLength - 2) neighbors.add(board[p.getX()][p.getY() + 1]);
        else neighbors.add(null);

        // Bottom left neighbor
        if (p.getX() <= xLength - 2 && p.getY() >= 1) neighbors.add(board[p.getX() + 1][p.getY() - 1]);
        else neighbors.add(null);

        // Bottom right neighbor
        if (p.getX() <= xLength - 2) neighbors.add(board[p.getX() + 1][p.getY()]);
        else neighbors.add(null);

        System.out.print("Neighbors of " + p.getX() + p.getY() + ": ");
        return neighbors;
    }

    /*
     * A function that sets a hex at position x,y
     */
    @Override
    public boolean setHex(Position p, Object hex) {
//        board[p.getX()][p.getY()] = board[hex];
        return false;
    }

    public Object[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }

    public void display() {
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (j == 0) {
                    for (int k = 0; k < i; k++) {
                        System.out.print("  ");
                    }
                }
                System.out.print(board[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        HexBoardClass<Terrain> h = new HexBoardClass<>(o, 10, 10);
        PositionClass p = new PositionClass(9, 9);
        h.display();
        System.out.println(h.getHexNeighbors(p));
    }

}

