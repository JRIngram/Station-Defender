package com.aston.group.stationdefender.actors;

/**
 * Interface implemented by the Unit and Tower classes.
 * Used for entities within the game that can act and be destroyed.
 * @author Jamie Ingram
 * @version 20/10/2016
 */
public interface Actor {
    public void render(float delta);
	public void act();
	public void destroy();
	public boolean getExists();
	
    /**
     * Causes the Units health to lower by the damage parameter.
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage);
}