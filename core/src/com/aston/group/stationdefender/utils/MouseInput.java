package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * A utility to handle positions of the mouse input
 *
 * @author Mohammad Foysal
 */
public class MouseInput {
    private static Vector2 position;

    /**
     * Returns the Vector2 position of the mouse
     *
     * @return The Vector2 position of the mouse
     */
    public static Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the Vector2 position of the mouse
     *
     * @param position The Vector2 position to set
     */
    public static void setPosition(Vector2 position) {
        MouseInput.position = position;
    }

    /**
     * Returns the specific X co-ordinate of the Vector2 mouse position
     *
     * @return The X co-ordinate of the Vector2 mouse position
     */
    public static int getX() {
        return (int) position.x;
    }

    /**
     * Returns the specific Y co-ordinate of the Vector2 mouse position
     *
     * @return The Y co-ordinate of the Vector2 mouse position
     */
    public static int getY() {
        return (int) position.y;
    }

    /**
     * Check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Towers X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x      The X co-ordinate of the object to check
     * @param y      The Y co-ordinate of the object to check
     * @param width  The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    public static boolean isColliding(int x, int y, int width, int height) {
        return x + width > MouseInput.getX() && x < MouseInput.getX() && y + height > MouseInput.getY() && y < MouseInput.getY();
    }
}