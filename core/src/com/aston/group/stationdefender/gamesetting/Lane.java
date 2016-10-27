package com.aston.group.stationdefender.gamesetting;

/**
 * Skeleton Lane class
 * @author Jonathon Fitch
 */
public class Lane {
    private int x, y;

    /**
     * Construct a new Lane with default
     * X and Y co-ordinates of '0'
     */
    public Lane() {
        this(0, 0);
    }

    /**
     * Construct a new Lane
     * @param x The X co-ordinate of the Lane
     * @param y The Y co-ordinate of the Lane
     */
    public Lane(int x, int y) {
        this.x = x;
        this.y = y;
    }
}