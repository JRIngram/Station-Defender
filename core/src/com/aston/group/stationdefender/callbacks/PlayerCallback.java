package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.actors.Actor;

/**
 * PlayerCallback is the callback class for Player
 *
 * @author Mohammad Foysal
 */
public interface PlayerCallback {
    void placeActor(Actor actor, int x, int y);
}