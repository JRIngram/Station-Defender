package com.aston.group.stationdefender.gamesetting;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Skeleton Board class
 * @author Jonathon Fitch
 */
public class Board {
    private ArrayList<Lane> lanes = new ArrayList<Lane>();

    /**
     * Construct a new Board with a default of 4 Lanes
     */
    public Board() {
        this(4);
    }

    /**
     * Construct a new Board with a given number of Lanes
     * @param numberOfLanes The number of Lanes for the Board to have
     */
    public Board(int numberOfLanes) {
        Lane[] lane = new Lane[numberOfLanes - 1];
        Collections.addAll(lanes, lane);
    }

    /**
     * Adds a Lane to the Board
     * @param lane The Lane to add to the Board
     */
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    /**
     * Removes a Lane from the Board by lane number
     * @param index The Lane number to remove from the Board
     */
    public void removeLaneByIndex(int index) {
        lanes.remove(index - 1);
    }

    /**
     * Removes a Lane from the Board by Lane Object
     * @param lane The Lane Object to be removed from the Board
     */
    public void removeLaneByObject(Lane lane) {
        lanes.remove(lane);
    }

    /**
     * Returns a Lane by the specific Lane number
     * @param index The lane number of the lane to get
     * @return The lane of the specific lane number
     */
    public Lane getLane(int index) {
        return lanes.get(index - 1);
    }
}