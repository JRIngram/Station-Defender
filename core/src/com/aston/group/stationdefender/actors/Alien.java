package com.aston.group.stationdefender.actors;

/**
 * Superclass for different Alien types.
 * @author IngramJ
 * @version 20/10/2016
 */
public abstract class Alien extends Unit{
	
	public Alien(String name,  double speed, double damage, double rateOfFire, double health, double range){
		super(name, speed, damage, rateOfFire, health, range);
	}
	
    /**
     * Move to the left.
     * Checks if adjacent space to the left is occupied by a human weapon, and if so, attack.
     * @see com.aston.group.stationdefender.actors.Unit#act()
     */
	@Override
	public abstract void act();

	@Override
	public abstract void destroy();
}