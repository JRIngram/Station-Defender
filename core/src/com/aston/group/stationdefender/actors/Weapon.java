package com.aston.group.stationdefender.actors;

/**
 * Weapon is a class that represnets a weapon object
 * that Humans (Unit) can arm themselves with and use
 * to destroy Aliens
 * @author Jonathon Fitch
 */
public abstract class Weapon extends Unit implements Actor {
	private double buildTime;
	private int cost;
	private int costToUpgrade;

    /**
     * Construct a new Weapoon
     */
    public Weapon() {
    	super();
    }

    @Override
    public abstract void act();

    @Override
    public abstract void destroy();
}