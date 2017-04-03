package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Fast Alien with slow, but high damage.
 *
 * @author IngramJ
 */
public class CloseCombatWeapon extends Weapon {

    /**
     * Create a new CloseCombatWeapon with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the CloseCombatWeapon
     * @param y The Y co-ordinate of the CloseCombatWeapon
     */
    public CloseCombatWeapon(int x, int y) {
        super("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, x, y, 60, 60, 2.0, 15, 25);
        setTexture(TextureManager.INSTANCE.loadTexture(25));
    }
}