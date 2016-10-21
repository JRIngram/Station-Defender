package com.aston.group.stationdefender.actors;


/**
 * Superclass for Human weapons within the game.
 * @author IngramJ
 * @version 20/10/2016
 *
 */
public abstract class Weapon extends Unit implements Actor {
	private double buildTime;
	private int cost;
	private int costToUpgrade;
	
    public Weapon() {
    	super();
    }

    @Override
    public abstract void act();

    @Override
    public abstract void destroy();
}