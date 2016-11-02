package com.aston.group.stationdefender.actors;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 * @author Jonathon Fitch
 */
public class Tower implements Actor {
    private int height, width;
    private double health;
    private boolean exists = false;

    /**
     * Constructs a new Tower
     */
    public Tower() {
    }

    @Override
    public void render(float delta) {
        exists = true;
    }

    @Override
    public boolean exists(){
        return exists;
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

    /**
     * Causes the Units health to lower by the damage parameter.
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage){
    	health -= damage;
    }
}