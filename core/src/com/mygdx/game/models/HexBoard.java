package com.mygdx.game.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class that represents the HexBoard of Type T that we will use as the map of the game.
 */
public class HexBoard<T> {
    private T[][] board;
    private int numRows, numColumns;

    public HexBoard(int numRows, int numColumns) {
        if (numRows < 1 || numColumns < 1) throw new IllegalArgumentException("number of rows/columns must be positive");

        board = (T[][]) new Object[numRows][numColumns];
        this.numRows = numRows; // number of rows
        this.numColumns = numColumns; // number of columns
    }

    /**
     * Returns the distance (least number of moves) between two positions.
     *
     * @param p1 the first position
     * @param p2 the second position
     * @return the distance (least number of moves) between two positions
     */
    public int getDistanceBetweenTwoPositions(Position p1, Position p2) {
        if (isValidPosition(p1) && isValidPosition(p2)) {
            return (Math.abs(p1.getY() - p2.getY()) + Math.abs(p1.getX() - p2.getX()));
        }
        return -1;
    }

    /**
     * Returns the object at position x,y.
     *
     * @param p the position
     * @return the object at position x,y
     */
    public Optional<T> getHex(Position p) {
        // Returns Optional.empty() if the position is outside the Array bounds
        if (isValidPosition(p)) {
            if (board[p.getX()][p.getY()] == null) {
                return Optional.empty();
            } else {
                return Optional.of(board[p.getX()][p.getY()]);
            }
        }
        return Optional.empty();
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
        if (!isValidPosition(p)) return neighbors;

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
     * Returns the number of columns in the board.
     *
     * @return the number of columns in the board
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows in the board
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns true if the positions are in proximity to each other, or false otherwise.
     *
     * @param p1       the first position
     * @param p2       the second position
     * @param distance the distance between two positions
     * @return true if the positions are in proximity to each other, or false otherwise.
     */
    public boolean isInProximity(Position p1, Position p2, int distance) {
        if (getDistanceBetweenTwoPositions(p1, p2) <= distance) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the position is valid, or false otherwise.
     *
     * @param p the position
     * @return true if the position is valid, or false otherwise
     */
    private boolean isValidPosition(Position p) {
        if (!(p.getX() >= 0 && p.getX() <= numRows - 1 && p.getY() >= 0 && p.getY() <= numColumns - 1)) {
            return false;
        }
        return true;
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
}