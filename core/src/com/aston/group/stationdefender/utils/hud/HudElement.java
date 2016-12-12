package com.aston.group.stationdefender.utils.hud;

/**
 * Abstract class for HudElements
 *
 * @author Mohammad Foysal
 */
public abstract class HudElement {
    int x;
    int y;
    int width;
    int height;
    String title;

    /**
     * Render the HudElement
     *
     * @param delta The time in seconds since the last render
     */
    public abstract void render(float delta);

    /**
     * Checks whether the HudElement is colliding with another object
     *
     * @return True if the HudElement is colliding with another object, false if it isn't
     */
    public abstract boolean isColliding();

    /**
     * Sets the X co-ordinate of the HudElement
     *
     * @param x The X co-ordinate of the HudElement
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y co-ordinate of the HudElement
     *
     * @param y The Y co-ordinate of the HudElement
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the Object to be associated with the HudElement
     *
     * @param object The Object to be associated with the HudElement
     */
    public void setObject(Object object) {
    }
}