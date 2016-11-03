package com.aston.group.stationdefender.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Test class for Weapon
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class TestWeapon extends Weapon {

    public TestWeapon(double damage, double rateOfFire, double health,
                      double range, double buildTime, int cost, int costToUpgrade
    ) {
        super("Test Weapon", 0, damage, rateOfFire, health, range, buildTime, cost, costToUpgrade);
    }

    @Override
    public void render(float delta) {
    }

    /**
     * The main method which determines how the weapon acts.
     */
    @Override
    public void act() {
        if (!checkZeroHealth() && built) {
            if (isAdjacent) {
                try {
                    adjacentActor.takeDamage(fire());
                } catch (Exception e) {
                    System.out.println("Null values are not allowed");
                }
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
        // TODO IMPLEMENT
        //Play explosion animation.
        //Play explosion sound.
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion.mp3"));
        sound.play();
        System.out.println("UNIT DESTROYED - FILLER BEFORE IMPLEMENTATION.");
        exists = false;
    }
}