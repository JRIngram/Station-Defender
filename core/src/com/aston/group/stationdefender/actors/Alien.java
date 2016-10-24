package com.aston.group.stationdefender.actors;

/**
 * Alien is the enemy object and
 * attacks the Humans and Tower
 * @author Jonathon Fitch
 */
public abstract class Alien extends Unit implements Actor{
    private int buildTime;
    private double upgradeCost;

	/**
	 * Construct a new Alien
	 */
	public Alien(){
		super();
	}

	@Override
	public abstract void act();

	@Override
	public abstract void destroy();
}