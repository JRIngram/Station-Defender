package com.aston.group.stationdefender.actors;

/**
 * Interface implemented by the Unit and Tower classes.
 * Used for entities within the game that can act and be destroyed.
 *
 * @author Jamie Ingram
 * @version 20/10/2016
 */
public interface Actor {

    /**
     * Render the Actor.
     * @param delta - The time in seconds since the last render.
     */
    void render(float delta);

    void act();

    void destroy();

    boolean getExists();

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    void takeDamage(double damage);
}