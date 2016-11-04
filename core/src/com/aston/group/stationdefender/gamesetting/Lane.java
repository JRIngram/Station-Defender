package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.TestAlien;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Lane class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Lane implements UnitCallback{

    private ShapeRenderer shapeRenderer;

    private int x, y, width, height;
    private Array<Tile> tiles = new Array<Tile>();
    private Array<Unit> units = new Array<Unit>();
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
        tiles.addAll(tile);

        shapeRenderer = new ShapeRenderer();
        projectileFactory = new ProjectileFactory();
    }

    /**
     * Places a Unit in a Lane using the Tile as a helper
     *
     * @param unit The Unit to place
     * @param x    The X co-ordinate of the Tile
     * @param y    The Y co-ordinate of the Tile
     */
    public void place(Unit unit, int x, int y) {
        for (int i = 0; i < tiles.size; i++) {
            if(tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i)){
                unit.setX(tiles.get(i).getCenterX() - (unit.getWidth() / 2));
                unit.setY(tiles.get(i).getCenterY() - (unit.getHeight() / 2));
                units.add(unit);
            }
        }
    }

    /**
     * Check if a Unit is on a tile
     *
     * @param tileIndex The index of the Tile to check
     * @return true if a Unit is on the Tile, false if a Unit is not on the Tile
     */
    public boolean isTileOccupied(int tileIndex) {
        if (tiles.get(tileIndex) != null) {
            for (int i = 0; i < units.size; i++) {
                if (tiles.get(tileIndex).isColliding(units.get(i))) {
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
        tiles.removeIndex(index);
    }

    /**
     * Removes a tile from the Board by Tile Object
     *
     * @param tile The tile Object to be removed from the Board
     */
    public void removeTileByObject(Tile tile) {
        tiles.removeValue(tile, true);
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
        //w Units
        for (int i = 0; i < units.size; i++) {
            units.get(i).render(delta);
            units.get(i).setUnitCallback(this);
        }

        //Check if Units are adjacent. if they are, share the adjacent actor with each other
        for (int i = 0; i < units.size; i++) {
            boolean isUnitAdjacent = false;

            Unit unit = null;

            for (int j = 0; j < units.size; j++) {
                if (i != j) {
                    if(units.get(i).isUnitAdjacent(units.get(j))){
                        isUnitAdjacent = true;
                        unit = units.get(j);
                        break;
                    }
                }
            }

            if (isUnitAdjacent) {
                units.get(i).setIsAdjacent(true);
            } else {
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

        //Draw Projectiles
        projectileFactory.render(delta);

        for (int i = 0; i < projectileFactory.getProjectiles().size; i++) {
            for (int j = 0; j < units.size; j++) {
                if (units.get(j).isFacingLeft() && projectileFactory.getProjectiles().get(i).isColliding(units.get(j).getX(),
                        units.get(j).getY(), units.get(j).getWidth(), units.get(j).getHeight())) {
                    units.get(j).takeDamage(200);
                }
            }
        }
    }

    /**
     * Returns the X co-ordinate of the center of the last Tile
     *
     * @return The X co-ordinate of the center of the last Tile
     */
    public int getLastTileCenterX() {
        if (tiles.size > 0) {
            return tiles.get(tiles.size - 1).getCenterX();
        } else {
            return 0;
        }
    }

    /**
     * Returns the Y co-ordinate of the center of the last Tile
     *
     * @return The Y co-ordinate of the center of the last Tile
     */
    public int getLastTileCenterY() {
        if (tiles.size > 0) {
            return tiles.get(tiles.size - 1).getCenterY();
        } else {
            return 0;
        }
    }

    /**
     * Check if an objects X & Y co-ordinates or width & height
     * overlaps the Lanes X & Y co-ordinates, or width & height
     *
     * @param x The X co-ordinate of the object to check
     * @param y The Y co-ordinate of the object to check
     * @param width The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    public boolean isColliding (int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width &&
                y + height > this.y && y < this.y + this.height;
    }

    @Override
    public void onFire(int x, int y, int speed) {
        projectileFactory.shootBullet(x, y, speed);
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