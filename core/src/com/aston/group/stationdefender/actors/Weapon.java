package com.aston.group.stationdefender.actors;

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