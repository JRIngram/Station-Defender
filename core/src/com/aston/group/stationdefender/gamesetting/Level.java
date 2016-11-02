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

    public Level() {
        this(1);
    }

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void incrememnetLevel() {
        levelNumber++;
    }

    public int getLevel() {
        return levelNumber;
    }

    public void setLevel(int level) {
        levelNumber = level;
    }
}