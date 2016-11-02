package com.aston.group.stationdefender.actors;

/**
 * Unit is an abstract class which represents a unit of Humans
 * that Aliens attack.
 *
 * @author Jonathon Fitch
 */
public abstract class Unit implements Actor {
    protected String name;
    protected double speed, damage, rateOfFire, health, range;

    /**
     * Construct a new Unit
     */
    public Unit() {
    }

    @Override
    public abstract void act();

    @Override
    public abstract void destroy();
}