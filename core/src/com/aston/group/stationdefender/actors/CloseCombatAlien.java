package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Fast Alien with slow, but high damage.
 *
 * @author IngramJ
 */
public class CloseCombatAlien extends Alien {

    /**
     * Create a new CloseCombatAlien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the CloseCombatAlien
     * @param y The Y co-ordinate of the CloseCombatAlien
     */
    public CloseCombatAlien(int x, int y) {
        super("Close Combat Alien", -125, 60.0, 2, Constants.UNIT_HEALTH, 1, 7.0, x, y, 20, 20);
        setTexture(TextureManager.INSTANCE.loadTexture(22));
    }
}