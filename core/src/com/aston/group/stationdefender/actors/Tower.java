package com.aston.group.stationdefender.actors;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 * @author Jonathon Fitch
 */
public class Tower implements Actor {
    private int height, width;

    /**
     * Constructs a new Tower
     */
    public Tower() {
    }

    @Override
    public void act() {
    }

    @Override
    public void destroy() {
    }

    /**
     * Returns the height of the Tower
     * @return The height of the Tower
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Tower
     * @return The width of the Tower
     */
    public int getWidth() {
        return width;
    }
}