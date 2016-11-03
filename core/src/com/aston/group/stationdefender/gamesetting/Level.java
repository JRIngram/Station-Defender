package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Tower;

/**
 * Skeleton Level class
 *
 * @author Jonathon Fitch
 */
public class Level {
    private int levelNumber;
    private Tower tower = new Tower();
    private Board board = new Board();

    /**
     * Construct a new Level with default level number of 1.
     */
    public Level() {
        this(1);
    }

    /**
     * Construct a new Level with a given level number.
     *
     * @param levelNumber The number of the Level
     */
    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Increments the level number by 1.
     */
    public void incrememnetLevel() {
        levelNumber++;
    }

    /**
     * Returns the current level number
     *
     * @return The number of the Level
     */
    public int getLevel() {
        return levelNumber;
    }

    /**
     * Sets the current level number
     *
     * @param level The number of the level
     */
    public void setLevel(int level) {
        levelNumber = level;
    }

    /**
     * Render the Level.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        if (board != null)
            board.render(delta);
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        board.dispose();
    }
}