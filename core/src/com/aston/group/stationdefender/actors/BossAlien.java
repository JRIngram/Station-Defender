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
     * Create a new BossAlien with default X and Y co-ordinates of '0'
     *  */
    public BossAlien() {
        super("Alien", -60, 500, 5, 800, 5.0, 8, 0, 0, 500, 375);
        setTexture(TextureManager.INSTANCE.loadTexture(ThreadLocalRandom.current().nextInt(15, 19)));
    }
}