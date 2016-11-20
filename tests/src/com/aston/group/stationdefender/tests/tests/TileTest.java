package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    private static final int width = Constants.TILE_WIDTH;
    private static final int height = Constants.TILE_HEIGHT;
    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(1, 1);
    }

    @Test
    public void testConstructor() {
        assertNotNull(tile);
        assertEquals(1 + (width / 2), tile.getCenterX());
        assertEquals(1 + (height / 2), tile.getCenterY());
    }

    @Test
    public void testIsCollidingInt() {
        assertTrue(tile.isColliding(40, 40, 20, 20));
        assertFalse(tile.isColliding(100, 100, 1, 1));
    }

    @Test
    public void testIsCollidingUnit() {
        Alien unit = new Alien();
        assertTrue(tile.isColliding(unit));
        unit.setX(500);
        unit.setY(500);
        assertFalse(tile.isColliding(unit));
    }
}