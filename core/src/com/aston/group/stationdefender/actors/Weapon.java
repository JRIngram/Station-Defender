package com.aston.group.stationdefender.actors;

/**
 * Weapon is a class that represents a weapon object 
 * that Humans (Unit) can arm themselves with and use to destroy Aliens
 * @author Jamie Ingram
 * @version 20/10/2016
 *
 */
public abstract class Weapon extends Unit implements Actor {
	protected double buildTime;
	protected boolean built;
	protected double remainingBuildTime;
	protected int cost;
	protected int costToUpgrade;
	
    public Weapon(String name, double speed, double damage, double rateOfFire, double health, 
    		double range,	double buildTime, int cost, int costToUpgrade){ 	
    	super(name, speed, damage, rateOfFire, health, range);
    	this.buildTime = buildTime;
    	this.cost = cost;
    	this.costToUpgrade = costToUpgrade;
    	remainingBuildTime = buildTime;
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
     * Decrements the build timer by 1. If afterwards the build timer equals 0 then built is set to true.
     */
    public void decrementBuildTimer(){
    	if(remainingBuildTime > 0){
        	remainingBuildTime--;
    	}
    	if(remainingBuildTime == 0){
    		built = true;
    	}
    }
    
    public double getBuildTime(){
    	return buildTime;
    }
    
    public double getRemainingBuildTime(){
    	return remainingBuildTime;
    }
    
    public int getCost(){
    	return cost;
    }
    
    public int getCostToUpgrade(){
    	return costToUpgrade;
    }
    
    public boolean getBuilt(){
    	return built;
    }
}   
