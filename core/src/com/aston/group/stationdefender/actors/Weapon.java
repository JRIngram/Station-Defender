package com.aston.group.stationdefender.actors;


/**
 * Superclass for Human weapons within the game.
 * @author IngramJ
 * @version 20/10/2016
 *
 */
public abstract class Weapon extends Unit implements Actor {
	protected double buildTime;
	protected boolean built;
	protected double buildTimer;
	protected int cost;
	protected int costToUpgrade;

	
    public Weapon(String name, double speed, double damage, double rateOfFire, double health, 
    		double range,	double buildTime, int cost, int costToUpgrade){ 	
    	super(name, speed, damage, rateOfFire, health, range);
    	this.buildTime = buildTime;
    	this.cost = cost;
    	this.costToUpgrade = costToUpgrade;
    	buildTimer = buildTime;
    	built = false;
    }
    
    /**
     * Checks if adjacent space to the right is occupied by an alien, and if so, attack.
     */
    @Override
    public abstract void act();
    
    
    /**
     * Play destruction animation and remove.
     */
    @Override
    public abstract void destroy();
    
    /**
     * Decrements the build timer by 1. If afterwards the buildtimer equals 0 then built is set to true.
     */
    public void decrementBuildTimer(){
    	buildTimer--;
    	if(buildTimer == 0){
    		built = true;
    	}
    }
    
}