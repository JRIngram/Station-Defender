package com.aston.group.stationdefender.actors;

import java.util.Random;

/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 *
 * @author Jamie Ingram
 * @version 01/11/2016
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

    /**
     * Checks if the Unit is adjacent to any other unit.
     * This information is retrieved from the Board.
     */
    protected boolean isAdjacent;

    /**
     * The Unit that this Unit is adjacent to.
     */
    protected Actor adjacentActor;

    /**
     * Whether the Unit is alive or dead.
     */
    protected boolean exists;

    public Unit(String name, double speed, double damage, double rateOfFire, double health, double range){
    	this.name = name;
    	this.speed = speed;
    	this.damage = damage;
    	this.rateOfFire = rateOfFire;
    	this.health = health;
    	this.range = range;
    	isAdjacent = false;
    	adjacentActor = null;
    	exists = false;
    }

    @Override
    public abstract void render(float delta);

    /**
     * Returns the existence state of the Unit.
     *
     * @return true if the Unit exits, false if not
     */
    @Override
    public boolean getExists() {
        return exists;
    }

    /**
     * Sets the existence state of the Unit.
     *
     * @param exists The existence state of the Unit
     */
    public void setExists(boolean exists) {
        this.exists = exists;
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
     * @return name of the Unit.
     */
    public String getName() {
    	return name;
    }

    /**
     *
     * @return Speed of the unit.
     */
    public double getSpeed() {
    	return speed;
    }

    /**
     *
     * @return The damage that the unit inflicts.
     */
    public double getDamage() {
    	return damage;
    }

    /**
     *
     * @return The rate of fire of the unit.
     */
    public double getRateOfFire() {
    	return rateOfFire;
    }

    /**
     *
     * @return The current health of the unit.
     */
    public double getHealth() {
    	return health;
    }

    /**
     *
     * @return The range that the unit can fire.
     */
    public double getRange() {
    	return range;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage) {
    	health -= damage;
    }

    /**
     * Checks if the Unit is adjacent to another entity.
     * @return Boolean which says if the Unit is adjacent to another entity.
     */
    public boolean isAdjacent() {
    	return isAdjacent;
    }

    public void setIsAdjacent(boolean isAdjacent) {
    	this.isAdjacent = isAdjacent;
    }

    public Actor getAdjacentActor() {
    	if (isAdjacent()) {
        	return adjacentActor;
    	} else {
    		return null;
    	}
    }

    public void setAdjacentActor(Actor adjacentActor) {
    	if (adjacentActor != null) {
        	this.adjacentActor = adjacentActor;
        	setIsAdjacent(true);
    	} else {
    		this.adjacentActor = null;
    		setIsAdjacent(false);
    	}
    }

    /**
     * Checks if the Health of the Unit is less than 1.
     * @see #act()
     */
    public boolean checkZeroHealth() {
        return health < 1;
    }

    public double fire() {
    	Random rng = new Random();
    	int hit = 0;
    	for (int i = 0; i < rateOfFire; i++) {
    		if (5 == rng.nextInt(10)) {
    			hit++;
    		}
    	}
    	return (hit * damage);
    }
}