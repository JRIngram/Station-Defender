package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Lane class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Lane {

    private ShapeRenderer shapeRenderer;

    private int x, y, width, height;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

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
        this.height = Constants.TILE_HEIGHT;

        Tile[] tile = new Tile[numberOfTiles - 1];

        int tileX = x;

        for (int i = 0; i < numberOfTiles - 1; i++) {
            tile[i] = new Tile(tileX, y);
            tileX += Constants.TILE_WIDTH;
            width += Constants.TILE_WIDTH;
        }
        Collections.addAll(tiles, tile);

        shapeRenderer = new ShapeRenderer();
    }

    public void place(Unit unit, int x, int y){
        for (int i = 0; i < tiles.size(); i++) {
            if(tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i)){
                unit.setX(tiles.get(i).getCenterX() - (unit.getWidth() / 2));
                unit.setY(tiles.get(i).getCenterY() - (unit.getHeight() / 2));
                units.add(unit);
                System.out.println("Placed Unit");
            }
        }
    }

    //Check if a unit is on a tile
    public boolean isTileOccupied(int tileIndex){
        if(tiles.get(tileIndex) != null){
            for (int i = 0; i < units.size(); i++) {
                if(tiles.get(tileIndex).isColliding(units.get(i))){
                    return true;
                }
            }
        }

        return false;
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

    /**
     * Render the Lane.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        for (Tile tile : tiles) {
            tile.render(delta);
        }

        for (int i = 0; i < units.size(); i++) {
            units.get(i).render(delta);
        }
    }

    public boolean isColliding (int x, int y, int width, int height) {
        if (x + width > this.x && x < this.x + this.width &&
                y + height > this.y && y < this.y + this.height) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).dispose();
        }

        //Todo dispose units
    }
}