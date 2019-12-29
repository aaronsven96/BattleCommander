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

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                board[i][j] = (T) ("" + i + j);
            }
        }
    }

    /*
     * A function that returns the object at position x,y
     */
    @Override
    public T getHex(Position p) {
        if (p.getX() >= 0 && p.getX() <= xLength - 1 && p.getY() >= 0 && p.getY() <= yLength - 1) return board[p.getX()][p.getY()];
        else return null;
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
        HexBoardClass<Object> h = new HexBoardClass<>(o, 10, 10);
        PositionClass p1 = new PositionClass(9, 9);
        PositionClass p2 = new PositionClass(0, 0);
        h.display();
        System.out.println(h.getHexNeighbors(p1));
        System.out.println(h.getHex(p1));
        System.out.println(h.setHex(p2, "Test"));
        h.display();
    }

}