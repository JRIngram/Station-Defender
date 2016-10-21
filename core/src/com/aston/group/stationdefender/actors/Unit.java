package com.aston.group.stationdefender.actors;


/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 * @author IngramJ
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

    public Unit(){
    }

    @Override
    public abstract void act();

    @Override
    public abstract void destroy();
}