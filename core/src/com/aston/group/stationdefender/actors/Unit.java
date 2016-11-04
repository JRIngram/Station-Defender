package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.config.Constants;

import java.util.Random;

/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public abstract class Unit implements Actor {

    /**
     * Unit's position on the X-Axis
     */
    protected int x;
    /**
     * Unit's position on the Y-Axis
     */
    protected int y;
    /**
     * Unit's width.
     */
    protected int width;
    /**
     * Unit's height
     */
    protected int height;
    /**
     * Checks if the Unit is adjacent to any other unit.
     * This information is retrieved from the Board.
     */
    boolean isAdjacent;
    /**
     * How many tiles it can move per "tick".
     */
    double speed;
    /**
     * The Unit that this Unit is adjacent to.
     */
    Actor adjacentActor;
    /**
     * Whether the Unit is alive or dead.
     */
    boolean exists;
    /**
     * Whether the Unit is facing left or not
     */
    boolean facingLeft;
    /**
     * How much damage the Unit can take before being destroyed.
     */
    double health;
    UnitCallback unitCallback;
    /**
     * Name of the type of unit.
     */
    private String name;
    /**
     * How much damage each successful hit causes.
     */
    private double damage;
    /**
     * How many times the unit fires per "tick".
     */
    private double rateOfFire;
    /**
     * How many tiles forward the Unit can fire.
     */
    private double range;

    public Unit() {
    }

    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
        width = 60;
        height = 60;
        health = Constants.UNIT_HEALTH;
    }

    public Unit(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height) {
        this.name = name;
        this.speed = speed;
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.health = health;
        this.range = range;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isAdjacent = false;
        adjacentActor = null;
        exists = false;
        health = Constants.UNIT_HEALTH;
    }

    /**
     * @return The damage that the unit inflicts.
     */
    public double getDamage() {
        return damage;
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
     *
     * @return name of the Unit.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Speed of the unit.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return The rate of fire of the unit.
     */
    public double getRateOfFire() {
        return rateOfFire;
    }

    /**
     * @return The current health of the unit.
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return The range that the unit can fire.
     */
    public double getRange() {
        return range;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage) {
        health -= damage;
    }

    /**
     * Checks if the Unit is adjacent to another entity.
     *
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

    public boolean isUnitAdjacent(Unit unit) {
        if (unit == this) return false;

        if (facingLeft) {
            return unit.getX() + unit.getWidth() > this.x - (this.width + 5) && unit.getX() < this.x + this.width &&
                    y + height > this.y && y < this.y + this.height;
        } else {
            return unit.getX() + unit.getWidth() > this.x && unit.getX() < this.x + (this.width * 2) + 5 &&
                    y + height > this.y && y < this.y + this.height;
        }
    }

    /**
     * Checks if the Health of the Unit is less than 1.
     *
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

    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public UnitCallback getUnitCallback() {
        return unitCallback;
    }

    public void setUnitCallback(UnitCallback unitCallback) {
        this.unitCallback = unitCallback;
    }
}