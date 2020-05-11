package com.mygdx.game.models;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

/**
 * A class that represents the HexBoard of Type T that we will use as the map of the game.
 */
@Getter
public class HexBoard<T> implements Iterable<T> {
    private T[][] board;
    private int numRows;
    private int numColumns;
    private Position cursor; // The cursor position in the forEach loop


    public HexBoard(int numRows, int numColumns) {
        if (numRows < 1 || numColumns < 1) throw new IllegalArgumentException("number of rows/columns must be positive");

        board = (T[][]) new Object[numRows][numColumns];
        this.numRows = numRows; // number of rows
        this.numColumns = numColumns; // number of columns
        cursor = new Position(0, 0);
    }

    /**
     * Returns the shortest distance between two positions.
     *
     * @param p1 the first position
     * @param p2 the second position
     * @return the shortest distance between two positions
     */
    public Optional<Integer> getDistanceBetweenTwoPositions(Position p1, Position p2) {
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

        if (isValidPosition(newP2) && isValidPosition(newP1)) {
            int absY = Math.abs(newP1.getY() - newP2.getY());
            int absX = Math.abs(newP1.getX() - newP2.getX());

            int z1 = -newP1.getX() - newP1.getY();
            int z2 = -newP2.getX() - newP2.getY();
            int absZ = Math.abs(z1 - z2);

            int max = Math.max(Math.max(absX, absY), absZ);
            return Optional.of(max);
        }

        return Optional.empty(); // returns Optional.empty() if either position is invalid
    }

    /**
     * Returns the hex at a position.
     *
     * @param p the position
     * @return the hex at a position
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
     * Returns a List of neighbors to a hex.
     *
     * @param p the position
     * @return a List of neighbors to a hex
     */
    public List<T> getHexNeighbors(Position p) {
        List<Position> pNeighbors = getPositionNeighbors(p);
        List<T> hNeighbors = new ArrayList<>();

        for (Position pos : pNeighbors) {
            Optional<T> opt = getHex(pos);
            if (opt.isPresent()) {
                hNeighbors.add(opt.get());
            }
        }

        return hNeighbors;
    }

    /**
     * Returns a List of positions within range of a given position.
     *
     * @param p     the position
     * @param range the range
     * @return a List of positions within range of a given position
     */
    public List<Position> getNearestPositionNeighbors(Position p, int range) {
        Map<String, Position> marked = new HashMap<>();
        marked.put(p.toString(), p);

        Queue<Position> toExplore = new LinkedList<>();
        toExplore.add(p);
        toExplore.add(null);

        int depth = 0;
        while (!toExplore.isEmpty()) {
            Position current = toExplore.remove();

            if (current == null) {
                depth++;
                toExplore.add(null);
                if (toExplore.peek() == null || depth == range) {
                    break;
                } else {
                    continue;
                }
            }

            for (Position neighbor : getPositionNeighbors(current)) {
                if (!marked.containsKey(neighbor.toString())) {
                    marked.put(neighbor.toString(), neighbor);
                    toExplore.add(neighbor);
                }
            }
        }

        marked.remove(p.toString());
        List<Position> npNeighbors = new ArrayList<>(marked.values());
        return npNeighbors;
    }


    /**
     * Returns a List of hexes within range of a given position.
     *
     * @param p     the position
     * @param range the range
     * @return a List of hexes within range of a given position
     */
    public List<T> getNearestHexNeighbors(Position p, int range) {
        List<Position> positionNeighbors = getNearestPositionNeighbors(p, range);
        List<T> nhNeighbors = new ArrayList<>();
        for (Position pos : positionNeighbors) {
            Optional<T> opt = getHex(pos);
            if (opt.isPresent()) {
                T newT = opt.get();
                nhNeighbors.add(newT);
            }
        }

        return nhNeighbors;
    }

    /**
     * Returns a List of neighbors to a position.
     *
     * @param p the position
     * @return a List of neighbors to a position
     */
    public List<Position> getPositionNeighbors(Position p) {
        List<Position> pNeighbors = new ArrayList<>();

        // Invalid position check
        if (!isValidPosition(p)) return pNeighbors;

        // Up neighbor
        if (p.getY() >= 1) {
            pNeighbors.add(new Position(p.getY() - 1, p.getX()));
        }

        // Down left neighbor
        if (p.getX() >= 1) {
            pNeighbors.add(new Position(p.getY(), p.getX() - 1));
        }

        // Up left neighbor
        if (p.getY() >= 1 && p.getX() >= 1) {
            pNeighbors.add(new Position(p.getY() - 1, p.getX() - 1));
        }

        // Up right neighbor
        if (p.getX() <= numColumns - 2) {
            pNeighbors.add(new Position(p.getY(), p.getX() + 1));
        }

        // Down neighbor
        if (p.getY() <= numRows - 2) {
            pNeighbors.add(new Position(p.getY() + 1, p.getX()));
        }

        // Down right neighbor
        if (p.getY() <= numRows - 2 && p.getX() <= numColumns - 2) {
            pNeighbors.add(new Position(p.getY() + 1, p.getX() + 1));
        }

        return pNeighbors;
    }

    /**
     * Returns a random shortest path between two positions.
     *
     * @param p1 the first position
     * @param p2 the second position
     * @return a random shortest path between two positions
     */
    public List<Position> getRandomShortestPath(Position p1, Position p2) {
        List<Position> rsPath = new ArrayList<>();

        int totalDistance;
        Optional<Integer> opt1 = getDistanceBetweenTwoPositions(p1, p2);
        if (opt1.isPresent()) {
            totalDistance = opt1.get();
        } else {
            return rsPath;
        }

        Set<String> marked = new HashSet<>();
        Position current = p1;
        int neighborDistance;
        int depth = 0;
        while (rsPath.size() < totalDistance) {

            List<Position> temp = new ArrayList<>();

            for (Position neighbor : getPositionNeighbors(current)) {
                if (marked.contains(neighbor.toString())) {
                    continue;
                }
                marked.add(neighbor.toString());

                Optional<Integer> opt2 = getDistanceBetweenTwoPositions(neighbor, p2);
                if (opt2.isPresent()) {
                    neighborDistance = opt2.get();
                } else {
                    continue;
                }

                if (depth + neighborDistance == totalDistance - 1) {
                    temp.add(neighbor);
                }
            }

            if (temp.size() == 0) {
                return temp;
            }

            Random r = new Random();
            int index = r.nextInt(temp.size());
            current = temp.get(index);
            rsPath.add(current);
            depth++;
        }

        return rsPath;
    }

    /**
     * Returns true if the positions are in proximity to each other, or false otherwise.
     *
     * @param p1    the first position
     * @param p2    the second position
     * @param range the maximum allowed distance between two positions
     * @return true if the positions are in proximity to each other, or false otherwise
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
     * Sets the hex at a position.
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

    // TODO: Implement these methods

    /** Returns true if there are no obstacles between the positions that would block line of sight */
    public boolean checkLineOfSight(HexBoard<Terrain> board, Position startPosition, Position endPosition) {
        return true;
    }

    @Override
    @Nonnull
    public HexBoardIterator iterator() {
        return new HexBoardIterator();
    }

    private class HexBoardIterator implements Iterator<T> {
        @Getter
        private int rowCursor;
        @Getter
        private int columnCursor;
        private int lastRow = -1;
        private int lastColumn = -1;

        @Override
        public boolean hasNext() {
            return this.rowCursor != numRows || this.columnCursor != numColumns;
        }

        @Override
        public T next() {
            int x = columnCursor;
            int y = rowCursor;
            if (x >= numColumns || y >= numRows) {
                throw new NoSuchElementException();
            } else {
                T[][] board = HexBoard.this.board;
                if (columnCursor == numColumns - 1) {
                    columnCursor = 0;
                    rowCursor = y + 1;
                } else {
                    columnCursor = x + 1;
                }
                HexBoard.this.cursor = new Position(rowCursor, columnCursor);
                return board[lastRow = y][lastColumn = x];
            }
        }

        @Override
        public void remove() {
            if (lastRow < 0 && lastColumn < 0) {
                throw new IllegalStateException();
            } else {
                try {
                    board[lastRow][lastColumn] = null;
                    columnCursor = lastColumn;
                    rowCursor = lastRow;
                    lastColumn = -1;
                    lastRow = -1;
                } catch (IndexOutOfBoundsException var2) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     * Returns a position with an inverted y-coordinate.
     *
     * @param p the position
     * @return a position with an inverted y-coordinate
     */
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
