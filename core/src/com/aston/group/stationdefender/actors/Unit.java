package com.aston.group.stationdefender.actors;

public abstract class Unit implements Actor {
    private String name;
    private double speed, damage, rateOfFire, health, range;

    public Unit() {
    }

    @Override
    public void act() {
    }

    @Override
    public void destroy() {
    }
}