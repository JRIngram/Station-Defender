package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Board;
import com.aston.group.stationdefender.gamesetting.Lane;
import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {
    private final int numberOfLanes = Constants.LANE_AMOUNT;
    private Player player;
    private Tower tower;
    private Board board;

    @Before
    public void setUp() {
        player = new Player();
        tower = new Tower(1, 1, 50, 50);
        board = new Board(player, tower);
    }

    @Test
    public void testConstructor() {
        assertFalse(board.isHasLost());
        assertFalse(board.isHasWon());
    }

    @Test
    public void testClearLanes() {
        Array<Lane> lanes = board.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        board.clear();
        lanes = board.getAllLanes();
        assertEquals(0, lanes.size);
    }

    @Test
    public void testAddLane() {
        Array<Lane> lanes = board.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        board.addLane(new Lane(player, tower, 0, 0, 12));
        lanes = board.getAllLanes();
        assertEquals(numberOfLanes + 1, lanes.size);
    }

    @Test
    public void testRemoveLanes() {
        Array<Lane> lanes = board.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        board.removeLaneByIndex(1);
        lanes = board.getAllLanes();
        assertEquals(numberOfLanes - 1, lanes.size);
        board.removeLaneByIndex(2);
        lanes = board.getAllLanes();
        assertEquals(numberOfLanes - 2, lanes.size);
        Lane lane = new Lane(player, tower, 0, 0, 12);
        board.addLane(lane);
        board.addLane(lane);
        lanes = board.getAllLanes();
        assertEquals(numberOfLanes, lanes.size);
        board.removeLaneByObject(lane);
        lanes = board.getAllLanes();
        assertEquals(numberOfLanes - 1, lanes.size);
    }
}