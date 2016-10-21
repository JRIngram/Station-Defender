package com.aston.group.stationdefender.actors;

public abstract class Alien extends Unit implements Actor{
    private int buildTime;
    private double upgradeCost;
	
	public Alien(){
		super();
	}

	@Override
	public abstract void act();

	@Override
	public abstract void destroy();
}