package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.callbacks.LaneCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Lane;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LaneTest implements LaneCallback {
    private final int numberOfTiles = Constants.TILE_AMOUNT;
    private Lane lane;

    @Before
    public void setUp() {
        lane = new Lane(this, 0, 0, numberOfTiles, 2 * 10);
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
        lane.removeTileByIndex(4);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles - 2, tiles.size);
        Tile tile = new Tile(0, 0);
        lane.addTile(tile);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles - 1, tiles.size);
        lane.removeTileByObject(tile);
        tiles = lane.getAllTiles();
        assertEquals(numberOfTiles - 2, tiles.size);
    }

    @Override
    public void addMoney(int money) {
    }

    @Override
    public void addScore(int score) {
    }

    @Override
    public void towerTakeDamage(double damage) {
    }

    @Override
    public boolean isTowerColliding(int x, int y, int width, int height) {
        return false;
    }

    @Override
    public void collectItem(Item item) {
    }

    @Override
    public int getLevelNumber() {
        return 1;
    }
}