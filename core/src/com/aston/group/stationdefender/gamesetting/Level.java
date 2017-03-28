package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.helpers.UnitFactory;
import com.aston.group.stationdefender.callbacks.LaneCallback;
import com.aston.group.stationdefender.callbacks.LevelCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Skeleton Level class
 *
 * @author Jonathon Fitch
 */
public class Level implements LaneCallback {
    private static boolean isBossCreated = false;
    private static boolean isBossDestroyed = false;
    private final SpriteBatch batch;
    private final Texture texture;
    private final LevelCallback levelCallback;
    private final BitmapFont font;
    private final Array<Lane> lanes = new Array<>();
    private final int levelNumber;
    private final Tower tower;
    private boolean hasWon;
    private boolean hasLost;
    private static final int[] backgroundTextures = new int[]{3, 20, 21};

    /**
     * Construct a new Level with a given level number.
     *
     * @param levelCallback The LevelCallBack to be used for the Level
     * @param levelNumber   The number of the Level
     */
    public Level(LevelCallback levelCallback, int levelNumber) {
        this.levelNumber = levelNumber;
        this.levelCallback = levelCallback;
        tower = new Tower(100, 100, 400);
        batch = GameEngine.getBatch();
        if (levelNumber == 1)
            texture = TextureManager.INSTANCE.loadTexture(3);
        else {
            int randomTexture = new Random().nextInt(backgroundTextures.length);
            texture = TextureManager.INSTANCE.loadTexture(backgroundTextures[randomTexture]);
        }
        double difficulty = (2 + (levelNumber / 10)) * 3;

        int laneY = 110;
        for (int i = 0; i < Constants.LANE_AMOUNT; i++) {
            lanes.add(new Lane(this, 100, laneY, Constants.TILE_AMOUNT, difficulty));
            laneY += (Constants.TILE_HEIGHT + (Constants.TILE_HEIGHT / 4));
        }

        font = FontManager.getFont(50);
    }

    /**
     * Render the Level.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        batch.begin();
        batch.draw(texture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        font.setColor(Color.BLACK);
        font.draw(batch, "Level " + levelNumber, (Gdx.graphics.getWidth() / 2) - 84, Gdx.graphics.getHeight() - 25);
        font.setColor(Color.WHITE);
        font.draw(batch, "Level " + levelNumber, (Gdx.graphics.getWidth() / 2) - 85, Gdx.graphics.getHeight() - 25);
        batch.end();

        for (Lane lane : lanes) {
            lane.render(delta);
            if (lane.isOverrun()) {
                if (!tower.getExists())
                    hasLost = true;
            }
        }

        if (isAllLanesCleared()) {
            if (isBossCreated) {
                if (isBossDestroyed)
                    hasWon = true;
            } else
                createBoss();
        }
        if (hasLost)
            levelCallback.onWinLost(false);
        if (hasWon)
            levelCallback.onWinLost(hasWon);

        if (tower != null)
            tower.render(delta);
    }

    /**
     * Adds a Lane to the Level
     *
     * @param lane The Lane to add to the Level
     */
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    /**
     * Returns all the Lanes in an Array
     *
     * @return An Array of all the Lanes
     */
    public Array<Lane> getAllLanes() {
        return lanes;
    }

    /**
     * Removes a Lane from the Level by lane number
     *
     * @param index The Lane number to remove from the Level
     */
    public void removeLaneByIndex(int index) {
        lanes.removeIndex(index - 1);
    }

    /**
     * Removes a Lane from the Level by Lane Object
     *
     * @param lane The Lane Object to be removed from the Level
     */
    public void removeLaneByObject(Lane lane) {
        lanes.removeValue(lane, true);
    }

    /**
     * Empty the Level
     **/
    public void clear() {
        lanes.clear();
    }

    /**
     * Place an actor at the given lane and tile. if there is already an actor
     * at that tile placing should not happen.
     *
     * @param unit The Unit to be placed
     * @param x    The X co-ordinate the place the Unit
     * @param y    The Y co-ordinate of the Unit
     * @return true if the placement is successful, false if not
     **/
    public boolean place(Unit unit, int x, int y) {
        boolean result = false;
        for (Lane lane : lanes) {
            if (lane.isColliding(x, y)) {
                if (lane.place(unit, x, y))
                    result = true;
            }
        }
        return result;
    }

    /**
     * Returns whether all the lanes are clear or not
     *
     * @return true if all the lanes are cleared, false if any of the lanes are not cleared
     */
    private boolean isAllLanesCleared() {
        boolean cleared = true;
        for (int i = 0; i < lanes.size; i++) {
            if (!lanes.get(i).isCleared()) {
                cleared = false;
            }
        }
        return cleared;
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (Lane lane : lanes) {
            lane.dispose();
        }
    }

    /**
     * Returns whether the Player has won the game or not
     *
     * @return true if the Player has won, false if the Player has not won
     */
    public boolean isHasWon() {
        return hasWon;
    }

    /**
     * Returns whether the Player has lost the game or not
     *
     * @return true if the Player has lost, false if the Player has not lost
     */
    public boolean isHasLost() {
        return hasLost;
    }

    @Override
    public void addMoney(int money) {
        levelCallback.addMoney(money);
    }

    @Override
    public void addScore(int score) {
        levelCallback.addScore(score);
    }

    @Override
    public void towerTakeDamage(double damage) {
        tower.takeDamage(damage);
    }

    @Override
    public boolean isTowerColliding(int x, int y, int width, int height) {
        return tower.isColliding(x, y, width, height);
    }

    @Override
    public void collectItem(Item item) {
        levelCallback.collectItem(item);
    }

    /**
     * Returns the Level Number
     *
     * @return the levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    private void createBoss() {
        isBossCreated = true;
        Unit unit = UnitFactory.getBossEnemy();
        unit.setX(Gdx.graphics.getWidth() / 2 - (unit.getWidth() / 2));
        unit.setY(Gdx.graphics.getHeight() / 2 - (unit.getHeight() / 2));
        if (unit.getHealth() == 0)
            unit.destroy();
            isBossDestroyed = true;
    }
}