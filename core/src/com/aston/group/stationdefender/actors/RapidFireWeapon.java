package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.utils.TextureManager;

/**
 * RapidFireWeapon is a class that represents a RapidFireWeapon object
 * that Humans (Unit) can arm themselves with and use to destroy Aliens.
 * <p>
 * This Weapon can overload and rapidly fire.
 *
 * @author Jamie Ingram
 */
public class RapidFireWeapon extends Weapon {
    private boolean overloaded = false;

    /**
     * Construct a new RapidFireWeapon with given X and Y co-ordinates
     *
     * @param x The X co-ordinate to give the RapidFireWeapon
     * @param y The Y co-ordinate to give the RapidFireWeapon
     */
    public RapidFireWeapon(int x, int y) {
        super("Rapid Fire Weapon", 25, 5.0, 15.0, 10, 10, 0.5, x, y, 60, 60, 1.0, 15, 25);
        setTexture(TextureManager.INSTANCE.loadTexture(13));
    }

    /**
     * Similar to usual act method, but if the Weapon hits on all shots the Weapon "overloads" and cannot fire for the next call of act.
     * No damage is dealt on this round,
     */
    @Override
    public void act(float delta) {
        if (!checkZeroHealth() && built) {
            if (!overloaded) {
                if (isAdjacent) {
                    overloaded = rapidFireHelper();
                } else {
                    overloaded = false;
                    if (unitCallback != null && System.currentTimeMillis() - lastTime >= (10000 / rateOfFire)) {
                        unitCallback.onFire(x + 40, y + 35, speed, getDamage());
                        lastTime = System.currentTimeMillis();
                    }
                }
            }
        } else {
            decrementBuildTimer();
        }
    }

    /**
     * Returns if the Weapon is overloaded.
     *
     * @return Overloaded state of the Weapon.
     */
    public boolean getOverloaded() {
        return overloaded;
    }

    /**
     * Sets if the Weapon is overloaded or not.
     *
     * @param overloaded state of the Weapon.
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
    }
}