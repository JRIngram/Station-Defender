package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.actors.Unit;

/**
 * PlayerCallback is the callback class for Player
 *
 * @author Mohammed Foysal
 */
public interface PlayerCallback {

    /**
     * The callback to place an Actor on the Level
     *
     * @param unit The Unit to place on the Level
     * @param x    The X co-ordinate to place the Unit
     * @param y    The Y co-ordinate to place the Unit
     * @return true if the placement is successful, false if not
     */
    boolean placeUnit(Unit unit, int x, int y);

    /**
     * The callback to pause the game
     */
    void onPause();
}