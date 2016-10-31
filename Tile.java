package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Actor;

/**
 *  Tile class
 * 
 * @author Twba Al-shaghdari
 */
public class Tile {
	private Actor actor;

	/**
	 * Construct a new Tile
	 */
	public Tile() {
		actor = null;
	}

	/**
	 * Place an actor at the tile. 
	 * If there is already an actor at that tile,
	 * placing should not happen.
	 * 
	 * @param actor
	 *            The actor to be placed
	 * @return true if the actor has been placed
	 * @return false if the actor hasn't been placed in the tile
	 **/
	public boolean placeActor(Actor actor) {
		if (getActor() == null) {
			this.actor = actor;
			return true;
		}
		return false;

	}
	
	/**
	 * get the actor at this tile. 
	 * 
	 * @return the actor
	 **/
	public Actor getActor() {

		return actor;
	}
}