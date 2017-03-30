package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.utils.TextureManager;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Boss Alien displayed at the end of the level.
 *
 * @author Jonathon Fitch
 */
public class BossAlien extends Alien {

    /**
     * Create a new BossAlien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the BossAlien
     * @param y The Y co-ordinate of the BossAlien
     */
    public BossAlien(int x, int y) {
        super("Alien", -60, 500, 5, 800, 5.0, 8, x, y, 500, 375);
        setTexture(TextureManager.INSTANCE.loadTexture(ThreadLocalRandom.current().nextInt(15, 19)));
    }
}