package com.aston.group.stationdefender.actors;

public abstract class Unit implements Actor {
    protected String name;
    protected double speed, damage, rateOfFire, health, range;

    public Unit() {
    }

    @Override
    public abstract void act();

    @Override
    public abstract void destroy();
}