package com.aston.group.stationdefender.gamesetting;

import java.util.ArrayList;
import java.util.Collections;
import com.aston.group.stationdefender.actors.*;

/**
 * Board class
 * 
 * @author Twba Alshaghdari
 */
public class Board {
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private static int numberOfLanes = 4;
	private static int numberOfTiles = 4;

	/**
	 * Construct a new Board with a default of 4 Lanes
	 */
	public Board() {
		this(numberOfLanes, numberOfTiles);
	}

	/**
	 * Construct a new Board with a given number of Lanes each lane will have
	 * the same number of Tiles.
	 * 
	 * @param numberOfLanes
	 *            The number of Lanes for the Board to have
	 * @param numberOfTiles
	 *            The number of Lanes for the Board to have
	 */
	public Board(int numberOfLanes, int numberOfTiles) {
		Lane[] lane = new Lane[numberOfLanes - 1];
		for (int i = 0; i < numberOfLanes; i++) {
			lane[i] = new Lane(numberOfTiles);
		}
		Collections.addAll(lanes, lane);
	}

	/**
	 * Adds a Lane to the Board
	 * 
	 * @param lane
	 *            The Lane to add to the Board
	 */
	public void addLane(Lane lane) {
		lanes.add(lane);
	}

	/**
	 * Removes a Lane from the Board by lane number
	 * 
	 * @param index
	 *            The Lane number to remove from the Board
	 */
	public void removeLaneByIndex(int index) {
		lanes.remove(index);
	}

	/**
	 * Removes a Lane from the Board by Lane Object
	 * 
	 * @param lane
	 *            The Lane Object to be removed from the Board
	 */
	public void removeLaneByObject(Lane lane) {
		lanes.remove(lane);
	}

	/**
	 * Returns a Lane by the specific Lane number
	 * 
	 * @param index
	 *            The lane number of the lane to get
	 * @return The lane of the specific lane number
	 */
	public Lane getLane(int index) {
		return lanes.get(index);
	}

	/**
	 * Empty the board
	 **/
	public void clear() {
		for (int i = 0; i < numberOfLanes; i++)
			lanes.clear();
	}

	/**
	 * Place an actor at the given lane and tile. if there is already an actor
	 * at that tile placing should not happen.
	 * 
	 * @param actor
	 *            The actor to be placed
	 * @param laneNo
	 *            The index of the lane the entity is in
	 * @param tileNo
	 *            the index of the tile the entity is in
	 * @return true if the actor has been placed
	 * @return false if the actor hasn't been placed in the tile
	 **/
	public boolean place(Actor actor, int laneNo, int tileNo) {

		return lanes.get(laneNo).getTile(tileNo).placeActor(actor);

	}

	/**
	 * Place an entity at the given lane and tile. if there is already an entity
	 * at that tile it will be lost.
	 * 
	 * @param entity
	 *            The entity to be placed
	 * @param laneNo
	 *            The index of the lane the entity is in
	 * @param tileNo
	 *            the index of the tile the entity is in
	 **/
	public Actor getActorAt(int laneNo, int tileNo) {
		return lanes.get(laneNo).getTile(tileNo).getActor();
	}
	
}