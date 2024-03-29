package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Fast Firing Alien with fast, but low damage.
 *
 * @author IngramJ
 */
public class RapidFireAlien extends Alien {
    private boolean overloaded = false;

    /**
     * Construct a new RapidFireAlien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate to give the Alien
     * @param y The Y co-ordinate to give the Alien
     */
    public RapidFireAlien(int x, int y) {
        super("Rapid Fire Alien", -125, 5.0, 10, Constants.UNIT_HEALTH, 2, 0.5, x, y, 100, 38);
        setTexture(TextureManager.INSTANCE.loadTexture(12));
    }

    /**
     * Similar to usual act method, but if the alien hits on all shots the alien "overloads" and cannot fire for the next call of act.
     * No damage is dealt on this round.
     *
     * @param delta The time in seconds since the last act
     */
    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (!overloaded) {
                if (isAdjacent && !(getAdjacentActor() instanceof Mine)) {
                    overloaded = rapidFireHelper();
                } else {
                    move(delta);
                }
            } else
                overloaded = false;
        } else {
            destroy();
        }
    }

    /**
     * Returns if the Alien is overloaded.
     *
     * @return Overloaded state of the Alien.
     */
    public boolean getOverloaded() {
        return overloaded;
    }

    /**
     * Sets if the Alien is overloaded or not.
     *
     * @param overloaded state of the Alien.
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
    }
}