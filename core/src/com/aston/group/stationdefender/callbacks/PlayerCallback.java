package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.actors.Actor;

/**
 * PlayerCallback is the callback class for Player
 *
 * @author Mohammed Foysal
 */
public interface PlayerCallback {

    /**
     * The callback to place an Actor on the Board
     *
     * @param actor The Actor to place on the Board
     * @param x     The X co-ordinate to place the Actor
     * @param y     The Y co-ordinate to place the Actor
     */
    void placeActor(Actor actor, int x, int y);

    /**
     * The callback to pause the game
     */
    void onPause();
}