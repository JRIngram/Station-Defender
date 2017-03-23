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
     * Create a new CloseCombatAlien with default X and Y co-ordinates of '0'
     */
    public BossAlien() {
        this(0, 0);
    }

    /**
     * Create a new CloseCombatAlien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the CloseCombatAlien
     * @param y The Y co-ordinate of the CloseCombatAlien
     */
    public BossAlien(int x, int y) {
        super("Alien", -80, 500, 5, 1000, 5.0, 8, x, y, 300, 114);
        setTexture(TextureManager.INSTANCE.loadTexture(ThreadLocalRandom.current().nextInt(15, 19)));
    }
}