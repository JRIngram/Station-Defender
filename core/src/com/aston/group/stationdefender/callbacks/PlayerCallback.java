package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.actors.Actor;

/**
 * PlayerCallback is the callback class for Player
 *
 * @author Mohammed Foysal
 */
public interface PlayerCallback {
    void placeActor(Actor actor, int x, int y);

    void onPause();
}