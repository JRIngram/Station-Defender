package com.aston.group.stationdefender.actors;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 *
 * @author Jonathon Fitch
 */
public class Tower implements Actor {
    private int height, width;
    private double health;
    private boolean exists;

    /**
     * Constructs a new Tower
     */
    public Tower() {
        exists = false;
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void act() {
    }

    @Override
    public void destroy() {
        exists = false;
    }

    /**
     * Returns the existence state of the Unit.
     *
     * @return true if the Unit exits, false if not
     */
    @Override
    public boolean getExists() {
        return exists;
    }

    /**
     * Sets the existence state of the Unit.
     *
     * @param exists The existence state of the Unit
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    /**
     * Returns the height of the Tower
     *
     * @return The height of the Tower
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Tower
     *
     * @return The width of the Tower
     */
    public int getWidth() {
        return width;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage){
    	health -= damage;
    }
}