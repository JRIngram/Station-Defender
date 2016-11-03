package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.TestAlien;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
    private ProjectileFactory projectileFactory;

    private long lastRenderTime;

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
        projectileFactory = new ProjectileFactory();
    }

    public void place(Unit unit, int x, int y){
        for (int i = 0; i < tiles.size(); i++) {
            if(tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i)){
                unit.setX(tiles.get(i).getCenterX() - (unit.getWidth() / 2));
                unit.setY(tiles.get(i).getCenterY() - (unit.getHeight() / 2));
                units.add(unit);
            }
        }
    }

    //Check if a unit is on a tile
    public boolean isTileOccupied(int tileIndex){
        if(tiles.get(tileIndex) != null){
            for (Unit unit : units) {
                if (tiles.get(tileIndex).isColliding(unit)) {
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
        for (Unit unit1 : units) {
            unit1.render(delta);
        }

        //Check if Units are adjacent. if they are, share the adjacent actor with each other
        for (int i = 0; i < units.size(); i++) {
            boolean isUnitAdjacent = false;

            Unit unit = null;

            for (int j = 0; j < units.size(); j++) {
                if(i != j){
                    if(units.get(i).isUnitAdjacent(units.get(j))){
                        isUnitAdjacent = true;
                        unit = units.get(j);
                        break;
                    }
                }
            }

            if(isUnitAdjacent){
                units.get(i).setIsAdjacent(true);
            }else{
                units.get(i).setIsAdjacent(false);
            }

            units.get(i).setAdjacentActor(unit);
        }

        //Remove Dead Units
        Iterator<Unit> unitsIterator = units.iterator();
        while (unitsIterator.hasNext()) {
            Unit unit = unitsIterator.next();

            if (unit.getHealth() <= 0) {
                unitsIterator.remove();
            }
        }

        //Spawn New Aliens
        if(System.currentTimeMillis() - lastRenderTime > 2000){
            TestAlien testAlien = new TestAlien();
            testAlien.setX(getLastTileCenterX() - (testAlien.getWidth() / 2));
            testAlien.setY(getLastTileCenterY() - (testAlien.getHeight() / 2));

            units.add(testAlien);
            lastRenderTime = System.currentTimeMillis();

        }

    }

    public int getLastTileCenterX(){
        if(tiles.size() > 0){
            return tiles.get(tiles.size() - 1).getCenterX();
        }else{
            return 0;
        }
    }

    public int getLastTileCenterY(){
        if(tiles.size() > 0){
            return tiles.get(tiles.size() - 1).getCenterY();
        }else{
            return 0;
        }
    }

    public boolean isColliding (int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width &&
                y + height > this.y && y < this.y + this.height;
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }

        //Todo dispose units
    }
}