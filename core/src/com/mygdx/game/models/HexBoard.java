package com.mygdx.game.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import lombok.Getter;

/**
 * A class that represents the HexBoard of Type T that we will use as the map of the game.
 */
@Getter
public class HexBoard<T> {
    private T[][] board;
    private int numRows;
    private int numColumns;

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
    public Optional<Integer> getDistanceBetweenTwoPositions(Position p1, Position p2) {
//        int deltaX = p2.getX() - p1.getX();
//        int deltaY = p2.getY() - p1.getY();
        Optional<Position> opt1 = invertYPosition(p1);
        Optional<Position> opt2 = invertYPosition(p2);

        Position newP1;
        Position newP2;

        if (opt1.isPresent() && opt2.isPresent()) {
            newP1 = opt1.get();
            newP2 = opt2.get();
        } else {
            return Optional.empty();
        }
//        int absY = Math.abs(p2.getY() - p1.getY());
//        int absX = Math.abs(p2.getX() - p1.getX());

        if (isValidPosition(newP2) && isValidPosition(newP1)) {
            int absY = Math.abs(newP1.getY() - newP2.getY());
            int absX = Math.abs(newP1.getX() - newP2.getX());

            int z1 = -newP1.getX() - newP1.getY();
            int z2 = -newP2.getX() - newP2.getY();
            int absZ = Math.abs(z1 - z2);

            int max = Math.max(Math.max(absX, absY), absZ);
            return Optional.of(max);

//            if (getPositionNeighbors(p1).contains(p2)) {
//                return Optional.of(1);
//            }
//
//            if (absY == absX) {
//                return Optional.of(absX);
//            }

//            int z1 = 0 - (p1.getX() + p1.getY());
//            int z2 = 0 - (p2.getX() + p2.getY());
//            int absZ = Math.abs(z2 - z1);
//            int absTest = Math.abs(absX - absY);
//            int negX1 = -1 * p1.getX();
//            int negY1 = -1 * p1.getY();
//            int negX2 = -1 * p2.getX();
//            int negY2 = -1 * p2.getY();
//            int max1 = Math.max(absX, absY);
//            int max2 = Math.max(max1, absTest);
//            return Optional.of(max2);
//            return Optional.of((absX + absY + absZ) / 2);
        }
        return Optional.empty(); // returns Optional.empty() if either position is invalid

//            if ((deltaY >= 0 && deltaX <= 0) || (deltaY <= 0 && deltaX >= 0)) {
//            } else {
//                int max1 = Math.max(absX, absY);
//                int max2 = Math.max(max1, Math.abs(0 - p1.getY() - p1.getX() + p2.getY() + p2.getX()));
//                return Optional.of(max2);
//            }
//            return Optional.of(absY + absX);


//            if (deltaY >= 0 && deltaX <= 0 || deltaY <= 0 && deltaX >= 0) {
//            } else if ((deltaY < 0 && deltaX < 0 || deltaY > 0 && deltaX > 0) && (deltaY % 2 == 0)) {
//                absX = Math.max(0, absX - (absY + 2) / 2);
//            } else if (deltaY < 0 && deltaX < 0 || deltaY > 0 && deltaX > 0) {
//                absX = Math.max(0, absX - (absY + 1) / 2);
//            } else {
//                absX = 0;
//            }
//            return Optional.of(absX + absY);
//        }
//        return Optional.empty(); // returns Optional.empty() if either position is invalid


//        if (p1.equals(p2) || dy == 0 || dx == 0) {
//        } else if (dy < 0 && dx < 0) {
//            x = Math.max(0, x + (y - 2) / 2);
//        } else if (dy < 0 && dx >= 0) {
//            x = Math.max(0, x - (y + 2) / 2);
//        } else if (dy >= 0 && dx < 0) {
//            x = Math.max(0, x - (y + 2) / 2);
//        } else {
//            x = Math.max(0, x + (y - 2) / 2);
//        }


//        if ((dx < 0) ^ ((p1.getY() & 1) == 1)) {
//            x = Math.max(0, x - (y + 1) / 2);
//        } else {
//            x = Math.max(0, x - (y / 2));
//            System.out.println(x);
//        }
//        int sum = x + y;
//
//        if (isValidPosition(p1) && isValidPosition(p2)) {
//            return Optional.of(sum);
//        }

//        if (isValidPosition(p1) && isValidPosition(p2)) {
//            if (p1.getY() == p2.getY() && p1.getX() == p2.getX()) { // same position
//                return Optional.of(0);
//            } else if (getPositionNeighbors(p1).contains(p1)) {
//                return Optional.of(1);
//            }
//            } else if (p1.getY() == p2.getY() || p1.getX() == p2.getX()) { // parallel
//                return Optional.of(Math.abs(p1.getY() - p2.getY()) + Math.abs(p1.getX() - p2.getX()));
//            } else {
//                return Optional.of(Math.abs(p1.getY() - p2.getY()) + Math.abs(p1.getX() - p2.getX()) - 2);

    }

    /**
     * Returns the object at position (y,x).
     *
     * @param p the position
     * @return the object at position (y,x)
     */
    public Optional<T> getHex(Position p) {
        // Returns Optional.empty() if the position is outside the Array bounds
        if (isValidPosition(p)) {
            if (board[p.getY()][p.getX()] == null) {
                return Optional.empty();
            } else {
                return Optional.of(board[p.getY()][p.getX()]);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a List of neighbors to a hex at position (y,x).
     *
     * @param p the position
     * @return a List of neighbors to a hex at (y,x)
     */
    public List<T> getHexNeighbors(Position p) {
        List<T> neighbors = new ArrayList<>();

        // Invalid position check
        if (!isValidPosition(p)) return neighbors;

        // Top left neighbor
        if (p.getY() >= 1) neighbors.add(board[p.getY() - 1][p.getX()]);

        // Top right neighbor
        if (p.getY() >= 1 && p.getX() <= numColumns - 2) neighbors.add(board[p.getY() - 1][p.getX() + 1]);

        // Left neighbor
        if (p.getX() >= 1) neighbors.add(board[p.getY()][p.getX() - 1]);

        // Right neighbor
        if (p.getX() <= numColumns - 2) neighbors.add(board[p.getY()][p.getX() + 1]);

        // Bottom left neighbor
        if (p.getY() <= numRows - 2 && p.getX() >= 1) neighbors.add(board[p.getY() + 1][p.getX() - 1]);

        // Bottom right neighbor
        if (p.getY() <= numRows - 2) neighbors.add(board[p.getY() + 1][p.getX()]);

        return neighbors;
    }

    public List<T> getNearestHexNeighbors(Position p, int range) {
        Map<String, Position> marked = new HashMap<>();
        marked.put(p.getY() + "," + p.getX(), p);

        Queue<Position> toExplore = new LinkedList<>();
        toExplore.add(p);

        while (!toExplore.isEmpty()) {
            Position current = toExplore.remove();
            for (Position neighbor : getPositionNeighbors(current)) {
                if (!marked.containsKey(neighbor.getY() + "," + neighbor.getX()) && isInProximity(p, neighbor, range)) {
                    marked.put(neighbor.getY() + "," + neighbor.getX(), neighbor);
                    toExplore.add(neighbor);
                }
            }
        }

        marked.remove(p.getY() + "," + p.getX());

        List<T> result = new ArrayList<>();
        for (String s : marked.keySet()) {
            Position neighbor = marked.get(s);
            Optional<T> opt = getHex(neighbor);
            if (opt.isPresent()) {
                T newT = opt.get();
                result.add(newT);
            }
        }

        return result;
    }

    /**
     * Returns a List of neighbors to a position (y,x).
     *
     * @param p the position
     * @return a List of neighbors to a position at (y,x)
     */
    public List<Position> getPositionNeighbors(Position p) {
        List<Position> neighbors = new ArrayList<>();

        // Invalid position check
        if (!isValidPosition(p)) return neighbors;

        // Up neighbor
        if (p.getY() >= 1) {
            neighbors.add(new Position(p.getY() - 1, p.getX()));
        }

        // Down left neighbor
        if (p.getX() >= 1) {
            neighbors.add(new Position(p.getY(), p.getX() - 1));
        }

        // Up left neighbor
        if (p.getY() >= 1 && p.getX() >= 1) {
            neighbors.add(new Position(p.getY() - 1, p.getX() - 1));
        }

        // Up right neighbor
        if (p.getX() <= numColumns - 2) {
            neighbors.add(new Position(p.getY(), p.getX() + 1));
        }

        // Down neighbor
        if (p.getY() <= numRows - 2) {
            neighbors.add(new Position(p.getY() + 1, p.getX()));
        }

        // Down right neighbor
        if (p.getY() <= numRows - 2 && p.getX() <= numColumns - 2) {
            neighbors.add(new Position(p.getY() + 1, p.getX() + 1));
        }

        return neighbors;
    }

    /**
     * Returns true if the positions are in proximity to each other, or false otherwise.
     *
     * @param p1    the first position
     * @param p2    the second position
     * @param range the maximum allowed distance between two positions
     * @return true if the positions are in proximity to each other, or false otherwise.
     */
    public boolean isInProximity(Position p1, Position p2, int range) {
        if (range < 0) {
            throw new IllegalArgumentException("range must be non-negative");
        }

        Optional<Integer> distance = getDistanceBetweenTwoPositions(p1, p2);

        if (distance.isPresent()) {
            return distance.get() <= range;
        }
        return false;
    }

    /**
     * Sets a hex at position (y,x).
     *
     * @param p   the position
     * @param hex the hex
     * @return true if the position is set, false otherwise
     */
    public boolean setHex(Position p, T hex) {
        // Null values for the hex Object can be passed in and set if the position is valid (i.e., they work as expected)
        if (p.getY() >= 0 && p.getY() <= numRows - 1 && p.getX() >= 0 && p.getX() <= numColumns - 1) {
            board[p.getY()][p.getX()] = hex;
            return true;
        }
        return false; // returns false if the position is outside the Array bounds
    }

    private Optional<Position> invertYPosition(Position p) {
        if (isValidPosition(p)) {
            int y = numRows - p.getY() - 1;
            return Optional.of(new Position(y, p.getX()));
        }
        return Optional.empty();
    }

    /**
     * Returns true if the position is valid, or false otherwise.
     *
     * @param p the position
     * @return true if the position is valid, or false otherwise
     */
    private boolean isValidPosition(Position p) {
        if (p == null) {
            return false;
        }
        return p.getY() >= 0 && p.getY() <= numRows - 1 && p.getX() >= 0 && p.getX() <= numColumns - 1;
    }
}