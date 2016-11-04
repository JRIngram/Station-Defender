package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Tower;

/**
 * Skeleton Level class
 *
 * @author Jonathon Fitch
 */
public class Level {
    private int levelNumber;
    private Tower tower = new Tower(1, 1, 10, 10);
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
    public void incrementLevel() {
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
     * Returns the level number
     *
     * @return the level number
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Sets the level number
     *
     * @param levelNumber The number of the level
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Returns the Tower object in the Level
     *
     * @return The Tower object in the Level
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Sets the Tower object to be used in the Level
     *
     * @param tower The Tower object to be used in the Level
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    /**
     * Returns the Board object used in the Level
     *
     * @return The Board object used in the Level
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the Board object to be used in the Level
     *
     * @param board The Board object to be used in the Level
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        board.dispose();
    }
}