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
     *
     * @param delta - The time in seconds since the last render.
     */
    void render(float delta);

    /**
     * Makes the Actor act
     */
    void act();

    /**
     * Destroys the Actor
     */
    void destroy();

    /**
     * Returns the exist state of the Actor
     *
     * @return true if the Actor exists, false if the Actor does not exist
     */
    boolean getExists();

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    void takeDamage(double damage);
}