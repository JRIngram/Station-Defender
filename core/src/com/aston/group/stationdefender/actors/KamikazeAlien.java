package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Alien with slow rate of fire and speed but a high damage.
 *
 * @author IngramJ
 */
public class KamikazeAlien extends Alien {

    /**
     * Create a new KamikazeAlien with default X and Y co-ordinates of '0'
     */
    public KamikazeAlien() {
        this(0, 0);
    }

    /**
     * Create a new KamikazeAlien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the KamikazeAlien
     * @param y The Y co-ordinate of the KamikazeAlien
     */
    public KamikazeAlien(int x, int y) {
        super("Kamikaze Alien", 4, 100, 1, Constants.UNIT_HEALTH, 1.0, 0.9, x, y, 100, 38);
        setTexture(TextureManager.INSTANCE.loadTexture(14));
    }

    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (isAdjacent) {
                adjacentActor.takeDamage(fire());
                super.destroy();
            } else {
                move(delta);
            }
        } else {
            destroy();
        }
    }
}