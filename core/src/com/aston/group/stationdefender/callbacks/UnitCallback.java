package com.aston.group.stationdefender.callbacks;

/**
 * UnitCallBack is the callback class for Unit
 *
 * @author Mohammed Foysal
 */
public interface UnitCallback {

    /**
     * The callback to fire a Weapon
     *
     * @param x     The X co-ordinate of the Weapon
     * @param y     The Y co-ordinate of the Weapon
     * @param speed The speed of the Weapon
     */
    void onFire(int x, int y, double speed, double damage);
}