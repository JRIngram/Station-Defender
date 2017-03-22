package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.utils.Array;

/**
 * LevelInfoCallback is the callback class for loading and saving data
 *
 * @author Jonathon Fitch
 */
public interface LevelInfoCallback {

    /**
     * Callback to load Player stats
     *
     * @param score       The last score of the Player
     * @param money       The amount of money the Player last had
     * @param levelNumber The highest Level number the Player got to in the previous game
     * @param items       The Items the Player had in the previous game
     */
    void onLoaded(int score, int money, int levelNumber, Array<Item> items);
}