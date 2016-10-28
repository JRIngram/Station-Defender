package com.aston.group.stationdefender.actors;

/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 * @author Jamie Ingram
 * @version 20/10/2016
 *
 */
public abstract class Unit implements Actor {
    /**
     * Name of the type of unit.
     */
	protected String name;
	/**
	 * How many tiles it can move per "tick".
	 */
    protected double speed;
    /**
     * How much damage each successful hit causes.
     */
    protected double damage;
    /**
     * How many times the unit fires per "tick".
     */
    protected double rateOfFire;
    /**
     * How much damage the Unit can take before being destroyed.
     */
    protected double health;
    /**
     * How many tiles forward the Unit can fire.
     */
    protected double range;

    public Unit(String name, double speed, double damage, double rateOfFire, double health, double range){
    	this.name = name;
    	this.speed = speed;
    	this.damage = damage;
    	this.rateOfFire = rateOfFire;
    	this.health = health;
    	this.range = range;
    }
    
    /**
     * Abstract method for each Unit, the main method which determines how the unit acts.
     */
    @Override
    public abstract void act();
    
    /**
     * Abstract method for each unit, destroys the unit.
     */
    @Override
    public abstract void destroy();
    
    /**
     * Method for getting the name of the Unit.
     * @return
     */
    public String getName(){
    	return name;
    }
    
    /**
     * 
     * @return Speed of the unit.
     */
    public double getSpeed(){
    	return speed;
    }
    
    /**
     * 
     * @return The damage that the unit inflicts.
     */
    public double getDamage(){
    	return damage;
    }
    
    /**
     * 
     * @return The rate of fire of the unit.
     */
    public double getRateOfFire(){
    	return rateOfFire;
    }
    
    /**
     * 
     * @return The current health of the unit.
     */
    public double getHealth(){
    	return health;
    }
    
    /**
     * 
     * @return The range that the unit can fire.
     */
    public double getRange(){
    	return range;
    }
    
    /**
     * 
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage){
    	health = health - damage;
    }
    
    /**
     * Checks if the Unit is adjacent to another entity.
     * @param entity An Actor.
     * @return Boolean which says if the Unit is adjacent to another entity.
     */
    public boolean isAdjacent(Actor entity){
    	return false;
    }
}