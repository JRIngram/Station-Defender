package com.aston.group.stationdefender.gamesetting.helpers;

import com.aston.group.stationdefender.actors.Actor;

/**
 * Tile class
 * @author Jonathon Fitch
 * @author Twba Al-shaghdari
 */
public class Tile {
	private Actor actor;

    /**
     * Construct a new Tile
     */
    public Tile() {
    }

	/**
	 * Place an actor at the tile. 
	 * If there is already an actor at that tile, placing should not happen.
	 * @param actor The actor to be placed
	 * @return true if the actor has been placed, false if the actor hasn't been placed in the tile
	 **/
	public boolean placeActor(Actor actor) {
		if (getActor() == null) {
			this.actor = actor;
			return true;
		}
		return false;
	}
	
	/**
	 * Return the actor at this tile.
	 * @return The actor at this tile.
	 **/
	public Actor getActor() {
		return actor;
	}
}