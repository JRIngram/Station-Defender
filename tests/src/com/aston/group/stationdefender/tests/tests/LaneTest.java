package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Lane;
import com.aston.group.stationdefender.gamesetting.Player;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LaneTest {
    private final int numberOfTiles = Constants.TILE_AMOUNT;
    private Lane lane;

    @Before
    public void setUp() {
        Player player = new Player();
        Tower tower = new Tower(1, 1, 50, 50);
        lane = new Lane(player, tower, 0, 0, numberOfTiles);
    }

    @Test
    public void testConstructor() {
        assertNotNull(lane);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTileNumber() {
        assertNotNull(lane.getTile(numberOfTiles));
        assertNotNull(lane.getTile(numberOfTiles));
        assertNull(lane.getTile(numberOfTiles + 1));
    }

    @Test
    public void testIsOverrun() {
        assertFalse(lane.isOverrun());
        lane.setOverrun(true);
        assertTrue(lane.isOverrun());
        lane.setOverrun(false);
    }

    @Test
    public void testIsCleared() {
        assertFalse(lane.isCleared());
        lane.setCleared(true);
        assertTrue(lane.isCleared());
        lane.setCleared(false);
    }

    @Test
    public void testClearLanes() {
        Array<Tile> tiles = lane.getAllTiles();
        assertEquals(numberOfTiles, tiles.size);
        lane.clear();
        tiles = lane.getAllTiles();
        assertEquals(0, tiles.size);
    }

    @Test
    public void testAddLane() {
        Array<Tile> tiles = lane.getAllTiles();
        assertEquals(numberOfTiles, tiles.size);
        lane.addTile(new Tile(0, 0));
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles + 1, tiles.size);
    }

    @Test
    public void testRemoveLanes() {
        Array<Tile> tiles = lane.getAllTiles();
        assertEquals(numberOfTiles, tiles.size);
        lane.removeTileByIndex(1);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles - 1, tiles.size);
        Tile tile = new Tile(0, 0);
        lane.addTile(tile);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles, tiles.size);
        lane.removeTileByObject(tile);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles - 1, tiles.size);
    }
}