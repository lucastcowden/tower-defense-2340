package edu.gatech.cs2340.td;

import java.util.Map;

public enum Direction {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int deltaX;
    private final int deltaY;
    // left
    // right


    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    public static final Map<Direction, Direction> LEFT_MAP = Map.of(
            NORTH, WEST,
            EAST, NORTH,
            SOUTH, EAST,
            WEST, SOUTH
    );
    public static final Map<Direction, Direction> RIGHT_MAP = Map.of(
            NORTH, EAST,
            EAST, SOUTH,
            SOUTH, WEST,
            WEST, NORTH
    );


    public Direction turnLeft() {
        return LEFT_MAP.get(this);
    }

    public Direction turnRight() {
        return RIGHT_MAP.get(this);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
