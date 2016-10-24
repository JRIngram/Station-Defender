package com.aston.group.stationdefender.actors;

/**
 * Interface implemented by the Unit and Tower classes.
 * Used for entities within the game that can act and be destroyed.
 * @author Jamie Ingram
 */
public interface Actor {
	public void act();
	public void destroy();
}