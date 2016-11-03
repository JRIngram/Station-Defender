package com.aston.group.stationdefender.actors;

/**
 * Test class for Alien
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class TestAlien extends Alien {

    public TestAlien(double speed, double damage, double rateOfFire, double health, double range) {
        super("Test Alien", speed, damage, rateOfFire, health, range);
    }

    @Override
    public void render(float delta) {
    }

    /**
     * The main method which determines how the weapon acts.
     */
    @Override
    public void act() {
        if (!checkZeroHealth()) {
            if (isAdjacent) {
                try {
                    adjacentActor.takeDamage(fire());
                } catch (Exception e) {
                    System.out.println("Null values are not allowed");
                }
            } else {
                move(speed);
            }
        } else {
            destroy();
        }
    }

    /**
     * Plays an explosion sound and animation.
     */
    @Override
    public void destroy() {
        //TODO IMPLEMENT
        //Play explosion animation.
        //Play explosion sound.
        exists = false;
    }
}