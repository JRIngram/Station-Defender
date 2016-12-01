package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.LaneCallback;
import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Lane class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Lane implements UnitCallback {
    private final int x, y, height;
    private final Array<Tile> tiles = new Array<>();
    private final Array<Unit> units = new Array<>();
    private final ProjectileFactory projectileFactory;
    private final Tower tower;
    private final LaneCallback laneCallback;
    private int width;
    private boolean overrun;
    private boolean cleared;
    private int alienAmount;
    private long lastRenderTime;

    /**
     * Construct a new Lane
     *
     * @param tower         The current Tower on the Board
     * @param x             The X co-ordinate of the Lane
     * @param y             The Y co-ordinate of the Lane
     * @param numberOfTiles The Number of tiles in the lane
     */
    public Lane(LaneCallback laneCallback, Tower tower, int x, int y, int numberOfTiles) {
        this.laneCallback = laneCallback;
        this.x = x;
        this.y = y;
        this.height = Constants.TILE_HEIGHT;
        this.tower = tower;

        Tile[] tile = new Tile[numberOfTiles];
        int tileX = x;
        for (int i = 0; i < numberOfTiles; i++) {
            tile[i] = new Tile(tileX, y);
            tileX += Constants.TILE_WIDTH;
            width += Constants.TILE_WIDTH;
        }
        tiles.addAll(tile);

        alienAmount = (int) (2 + (Math.random() * 10));
        lastRenderTime = System.currentTimeMillis();
        projectileFactory = new ProjectileFactory();
    }

    /**
     * Places a Unit in a Lane using the Tile as a helper
     *
     * @param unit The Unit to place
     * @param x    The X co-ordinate of the Tile
     * @param y    The Y co-ordinate of the Tile
     */
    void place(Unit unit, int x, int y) {
        for (int i = 0; i < tiles.size; i++) {
            if (tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i)) {
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
    private boolean isTileOccupied(int tileIndex) {
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
     * Returns all the Lanes in an Array
     *
     * @return An Array of all the Lanes
     */
    public Array<Tile> getAllTiles() {
        return tiles;
    }

    /**
     * Empty the Lane of Tiles
     **/
    public void clear() {
        tiles.clear();
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
        for (Tile tile : tiles) {
            tile.render();
        }
        //Units
        for (Unit unit : units) {
            unit.render(delta);
            unit.setUnitCallback(this);
        }

        //Check if Units are adjacent. if they are, share the adjacent actor with each other
        for (int i = 0; i < units.size; i++) {
            boolean isUnitAdjacent = false;

            Unit unit = null;
            for (int j = 0; j < units.size; j++) {
                if (i != j) {
                    if (units.get(i).isUnitAdjacent(units.get(j))) {
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

            //Check if aliens are near tower
            unit = units.get(i);
            if (unit.isFacingLeft() && tower.isColliding(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight())) {
                tower.takeDamage(unit.getDamage());
                overrun = true;
                unit.destroy();
                units.removeIndex(i);
            }
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
        if (System.currentTimeMillis() - lastRenderTime > 2200 + Math.random() * 3000) {
            if (alienAmount > 0) {
                Alien alien = new Alien();
                alien.setX(getLastTileCenterX() - (alien.getWidth() / 2));
                alien.setY(getLastTileCenterY() - (alien.getHeight() / 2));

                units.add(alien);
                alienAmount--;
            }
            lastRenderTime = System.currentTimeMillis();
        }

        //Draw Projectiles
        projectileFactory.render(delta);

        for (int i = 0; i < projectileFactory.getProjectiles().size; i++) {
            for (int j = 0; j < units.size; j++) {
                Projectile projectile = projectileFactory.getProjectiles().get(i);
                if (units.get(j).isFacingLeft() && projectile.isColliding(units.get(j).getX(), units.get(j).getY(),
                        units.get(j).getWidth(), units.get(j).getHeight())) {
                    projectile.setAlive(false);
                    double damage = projectile.getDamage();
                    if (units.get(j).getHealth() - damage <= 0) {
                        laneCallback.addMoney(Constants.MONEY_REGENERATION);
                        laneCallback.addScore(Constants.ADD_SCORE_AMOUNT);
                    }
                    units.get(j).takeDamage(damage);
                }
            }
        }

        //Check if lane is cleared
        if (isLaneCleared() && alienAmount <= 0) {
            cleared = true;
        }
    }

    /**
     * Returns the X co-ordinate of the center of the last Tile
     *
     * @return The X co-ordinate of the center of the last Tile
     */
    private int getLastTileCenterX() {
        if (tiles.size > 0) {
            return tiles.get(tiles.size - 1).getCenterX();
        } else {
            return 0;
        }
    }

    private boolean isLaneCleared() {
        for (int i = 0; i < units.size; i++) {
            if (units.get(i).isFacingLeft()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the Y co-ordinate of the center of the last Tile
     *
     * @return The Y co-ordinate of the center of the last Tile
     */
    private int getLastTileCenterY() {
        if (tiles.size > 0) {
            return tiles.get(tiles.size - 1).getCenterY();
        } else {
            return 0;
        }
    }

    /**
     * Check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Lanes X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x      The X co-ordinate of the object to check
     * @param y      The Y co-ordinate of the object to check
     * @param width  The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    boolean isColliding(int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width && y + height > this.y && y < this.y + this.height;
    }

    @Override
    public void onFire(int x, int y, double speed, double damage) {
        projectileFactory.shootBullet(x, y, speed, damage);
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

    /**
     * Returns whether a Lane is overrun with Aliens or not
     *
     * @return true if a Lane is overrun with Aliens, false if the Lane is not overrun by Aliens
     */
    public boolean isOverrun() {
        return overrun;
    }

    /**
     * Sets whether a Lane is overrun by Aliens or not
     *
     * @param overrun Whether a Lane is overrun by Aliens or not
     */
    public void setOverrun(boolean overrun) {
        this.overrun = overrun;
    }

    /**
     * Returns whether a Lane is cleared or not and there are no more Aliens onscreen
     *
     * @return true if a Lane is cleared, false if a Lane is not cleared
     */
    public boolean isCleared() {
        return cleared;
    }

    /**
     * Sets whether a Lane is cleared or not and there are no more Aliens onscreen
     *
     * @param cleared Whether a Lane is cleared or not and there are no more Aliens onscreen
     */
    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}