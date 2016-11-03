package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Lane class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Lane {
    private int x, y, width, height;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    /**
     * Construct a new Lane with default
     * X and Y co-ordinates of '0'
     *
     * @param numberOfTiles The Number of tiles in the lane
     */
    public Lane(int numberOfTiles) {
        this(0, 0, numberOfTiles);
    }

    /**
     * Construct a new Lane
     *
     * @param x             The X co-ordinate of the Lane
     * @param y             The Y co-ordinate of the Lane
     * @param numberOfTiles The Number of tiles in the lane
     */
    public Lane(int x, int y, int numberOfTiles) {
        this.x = x;
        this.y = y;

        Tile[] tile = new Tile[numberOfTiles - 1];

        int tileX = x;

        for (int i = 0; i < numberOfTiles - 1; i++) {
            tile[i] = new Tile(tileX, y);
            tileX += Constants.TILE_WIDTH;
        }
        Collections.addAll(tiles, tile);
    }

    /**
     * Returns a tile by the specific tile number
     *
     * @param index The lane number of the lane to get
     * @return The lane of the specific lane number
     */
    public Tile getTile(int index) {
        return tiles.get(index);
    }

    /**
     * Adds a tile to the Board
     *
     * @param tile The tile to add to the Board
     */
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * Removes a tile from the Board by Tile number
     *
     * @param index The tile number to remove from the Board
     */
    public void removeTileByIndex(int index) {
        tiles.remove(index);
    }

    /**
     * Removes a tile from the Board by Tile Object
     *
     * @param tile The tile Object to be removed from the Board
     */
    public void removeTileByObject(Tile tile) {
        tiles.remove(tile);
    }

    public void render(float delta) {
        for (Tile tile : tiles) {
            tile.render(delta);
        }
    }

    public void dispose() {
    }
}