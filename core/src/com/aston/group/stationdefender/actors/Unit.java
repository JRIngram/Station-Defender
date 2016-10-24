package com.aston.group.stationdefender.actors;

/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 * @author Jamie Ingram
 * @version 20/10/2016
 *
 */
public abstract class Unit implements Actor {
    protected String name;
    protected double speed;
    protected double damage;
    protected double rateOfFire;
    protected double health;
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