package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Lane;
import com.aston.group.stationdefender.gamesetting.Level;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LevelTest {
    private final int numberOfLanes = Constants.LANE_AMOUNT;
    private Tower tower;
    private Level level;

    @Before
    public void setUp() {
        tower = new Tower(1, 1, 50, 50);
        level = new Level(null, 1);
    }

    @Test
    public void testConstructor() {
        assertFalse(level.isHasLost());
        assertFalse(level.isHasWon());
    }

    @Test
    public void testClearLanes() {
        Array<Lane> lanes = level.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        level.clear();
        lanes = level.getAllLanes();
        assertEquals(0, lanes.size);
    }

    @Test
    public void testAddLane() {
        Array<Lane> lanes = level.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        level.addLane(new Lane(null, tower, 0, 0, 12));
        lanes = level.getAllLanes();
        assertEquals(numberOfLanes + 1, lanes.size);
    }

    @Test
    public void testRemoveLanes() {
        Array<Lane> lanes = level.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        level.removeLaneByIndex(1);
        lanes = level.getAllLanes();
        assertEquals(numberOfLanes - 1, lanes.size);
        level.removeLaneByIndex(2);
        lanes = level.getAllLanes();
        assertEquals(numberOfLanes - 2, lanes.size);
        Lane lane = new Lane(null, tower, 0, 0, 12);
        level.addLane(lane);
        level.addLane(lane);
        lanes = level.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        level.removeLaneByObject(lane);
        lanes = level.getAllLanes();
        assertEquals(numberOfLanes - 1, lanes.size);
    }
}