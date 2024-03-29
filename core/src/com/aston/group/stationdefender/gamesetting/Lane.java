package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Mine;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.helpers.UnitFactory;
import com.aston.group.stationdefender.callbacks.LaneCallback;
import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.aston.group.stationdefender.utils.resources.ItemFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
    private final Array<Item> itemDrops = new Array<>();
    private final ProjectileFactory projectileFactory;
    private final LaneCallback laneCallback;
    private int width;
    private boolean overrun;
    private boolean cleared;
    private int alienAmount;
    private long lastRenderTime;

    /**
     * Construct a new Lane
     *
     * @param laneCallback  THe LaneCallback to be used for the Lane
     * @param x             The X co-ordinate of the Lane
     * @param y             The Y co-ordinate of the Lane
     * @param numberOfTiles The Number of tiles in the lane
     * @param difficulty    The difficulty of the Level
     */
    public Lane(LaneCallback laneCallback, int x, int y, int numberOfTiles, double difficulty) {
        this.laneCallback = laneCallback;
        this.x = x;
        this.y = y;
        this.height = Constants.TILE_HEIGHT;

        Tile[] tile = new Tile[numberOfTiles];
        int tileX = x;
        int itemTileProbability = 2 * laneCallback.getLevelNumber();
        int invalidTileProbability = 14 / laneCallback.getLevelNumber();
        Random rand = new Random();
        for (int i = 0; i < numberOfTiles; i++) {
            tile[i] = new Tile(tileX, y);

            // Probability will be 1 / tileProbability
            tile[i].setHasItem(rand.nextInt(itemTileProbability) == 0);
            tile[i].setInvalid(rand.nextInt(invalidTileProbability) == 0);
            tileX += Constants.TILE_WIDTH;
            width += Constants.TILE_WIDTH;
        }
        tiles.addAll(tile);

        alienAmount = (int) difficulty;
        lastRenderTime = System.currentTimeMillis();
        projectileFactory = new ProjectileFactory();
    }

    /**
     * Places a Unit in a Lane using the Tile as a helper
     *
     * @param unit The Unit to place
     * @param x    The X co-ordinate of the Tile
     * @param y    The Y co-ordinate of the Tile
     * @return True if the placement was successful, false if the placement was unsuccessful
     */
    boolean place(Unit unit, int x, int y) {
        for (int i = 0; i < tiles.size; i++) {
            if (tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i) && !tiles.get(i).isInvalid()) {
                unit.setX(tiles.get(i).getCenterX() - (unit.getWidth() / 2));
                unit.setY(tiles.get(i).getCenterY() - (unit.getHeight() / 2));
                if (tiles.get(i).isHasItem()) {
                    Item item = ItemFactory.getRandomItem();
                    laneCallback.collectItem(item);
                }
                units.add(unit);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a Unit is on a tile
     *
     * @param tileIndex The index of the Tile to check
     * @return true if a Unit is on the Tile, false if a Unit is not on the Tile
     */
    private boolean isTileOccupied(int tileIndex) {
        return tiles.get(tileIndex) != null && IntStream.range(0, units.size).anyMatch(i -> tiles.get(tileIndex).isColliding(units.get(i)));
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
     */
    public void clear() {
        tiles.clear();
    }

    /**
     * Adds a tile to the Level
     *
     * @param tile The tile to add to the Level
     */
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * Removes a tile from the Level by Tile number
     *
     * @param index The tile number to remove from the Level
     */
    public void removeTileByIndex(int index) {
        tiles.removeIndex(index);
    }

    /**
     * Removes a tile from the Level by Tile Object
     *
     * @param tile The tile Object to be removed from the Level
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
                    if (units.get(i).isUnitAdjacent(units.get(j)) && !(units.get(i) instanceof Mine)) {
                        isUnitAdjacent = true;
                        unit = units.get(j);
                        break;
                    }
                }
            }

            if (!(units.get(i) instanceof Mine)) {
                if (isUnitAdjacent) {
                    units.get(i).setIsAdjacent(true);
                } else {
                    units.get(i).setIsAdjacent(false);
                }
                units.get(i).setAdjacentActor(unit);
            }

            //Check if aliens are near tower
            unit = units.get(i);
            if (unit.isFacingLeft() && laneCallback.isTowerColliding(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight())) {
                laneCallback.towerTakeDamage(unit.getDamage());
                overrun = true;
                unit.destroy();
                units.removeIndex(i);
            }
        }

        //Remove Dead Units
        Iterator<Unit> unitsIterator = units.iterator();
        while (unitsIterator.hasNext()) {
            Unit unit = unitsIterator.next();
            if (!unit.getExists()) {
                dropItem(ItemFactory.getItemByChance(), unit.getX(), unit.getY());
                unit.setHealth(-1);
            }
            if (unit.getHealth() <= 0) {
                unitsIterator.remove();
            }
        }

        //Spawn New Aliens
        if (System.currentTimeMillis() - lastRenderTime > 2200 + Math.random() * 3000) {
            if (alienAmount > 0) {
                Unit unit = UnitFactory.getRandomEnemy();
                if (unit instanceof Mine)
                    unit.setX(getRandomTileCenterX() - (unit.getHeight() / 2));
                else
                    unit.setX(getLastTileCenterX() - (unit.getWidth() / 2));
                unit.setY(getLastTileCenterY() - (unit.getHeight() / 2));
                units.add(unit);
                alienAmount--;
            }
            lastRenderTime = System.currentTimeMillis();
        }

        //Draw Projectiles
        projectileFactory.render(delta);
        projectileCollision(units, null);

        //Check if lane is cleared
        if (isLaneCleared() && alienAmount <= 0) {
            cleared = true;
        }

        //Render item drops
        for (int i = 0; i < itemDrops.size; i++) {
            itemDrops.get(i).render();
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && itemDrops.get(i).isJustSpawned() && MouseInput.isColliding(itemDrops.get(i).getX(), itemDrops.get(i).getY(), itemDrops.get(i).getWidth(), itemDrops.get(i).getHeight())) {
                laneCallback.collectItem(itemDrops.get(i));
                removeItem(i);
            }
        }
    }

    /**
     * Returns the X co-ordinate of the center of the last Tile
     *
     * @return The X co-ordinate of the center of the last Tile
     */
    private int getLastTileCenterX() {
        if (tiles.size > 0)
            return tiles.get(tiles.size - 1).getCenterX();
        else
            return 0;
    }

    /**
     * Returns the X co-ordinate of the center of a random Tile
     *
     * @return The X co-ordinate of the center of a random Tile
     */
    private int getRandomTileCenterX() {
        if (tiles.size > 0)
            return tiles.get(ThreadLocalRandom.current().nextInt(1, tiles.size)).getCenterX();
        else
            return 0;
    }

    /**
     * Returns whether the lane is cleared of Aliens or not
     *
     * @return True if the lane is cleared of Aliens, false if not
     */
    private boolean isLaneCleared() {
        return IntStream.range(0, units.size).noneMatch(i -> units.get(i).isFacingLeft());
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
     * @param x The X co-ordinate of the object to check
     * @param y The Y co-ordinate of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    boolean isColliding(int x, int y) {
        return x + 1 > this.x && x < this.x + this.width && y + 1 > this.y && y < this.y + this.height;
    }

    @Override
    public void onFire(int x, int y, double speed, double damage) {
        projectileFactory.shootBullet(x, y, speed, damage);
    }

    /**
     * Drop an Item on the Lane
     *
     * @param item The Item to drop
     * @param x    The X co-ordinate to drop the Item
     * @param y    The Y co-ordinate to drop the Item
     */
    private void dropItem(Item item, int x, int y) {
        if (item != null) {
            item.setX(x);
            item.setY(y);
            item.setJustSpawned();
            itemDrops.add(item);
        }
    }

    /**
     * Remove an Item from the Lane
     *
     * @param index The index of the Item to be removed
     */
    private void removeItem(int index) {
        itemDrops.removeIndex(index);
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
        for (Unit unit : units) {
            unit.dispose();
        }
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

    /**
     * Checks if projectiles are colliding with a Unit or an array of Units
     *
     * @param unitsArray The array of Units to cycle through
     * @param bossUnit   The singular boss Unit to check for collisions
     */
    void projectileCollision(Array<Unit> unitsArray, Unit bossUnit) {
        for (int i = 0; i < projectileFactory.getProjectiles().size; i++) {
            if (unitsArray != null) {
                for (int j = 0; j < unitsArray.size; j++) {
                    Unit unit = unitsArray.get(j);
                    projectileCollisionHelper(projectileFactory.getProjectiles().get(i), unit);
                }
            } else {
                projectileCollisionHelper(projectileFactory.getProjectiles().get(i), bossUnit);
            }
        }
    }

    /**
     * Helper method to avoid duplicate code in projectileCollision()
     *
     * @param projectile The Projectile to check for collisions
     * @param unit       The Unit to check for collisions against the Projectile
     */
    private void projectileCollisionHelper(Projectile projectile, Unit unit) {
        if (unit.isFacingLeft() && projectile.isColliding(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight())) {
            projectile.setDead();
            double damage = projectile.getDamage();
            if (unit.getHealth() - damage <= 0) {
                laneCallback.addMoney(Constants.MONEY_REGENERATION);
                laneCallback.addScore(Constants.ADD_SCORE_AMOUNT);
            }
            unit.takeDamage(damage);
        }
        if (laneCallback.isTowerColliding(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight())) {
            laneCallback.towerTakeDamage(projectile.getDamage());
            projectile.setDead();
        }
    }
}