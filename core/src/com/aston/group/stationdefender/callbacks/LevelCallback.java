package com.aston.group.stationdefender.callbacks;

/**
 * LevelCallback is the callback class for Level
 *
 * @author Mohammed Foysal
 */
public interface LevelCallback {

    /**
     * The win/lost situation callback
     *
     * @param won Whether the Player has won the game or not
     */
    void onWinLost(boolean won);

    void addMoney(int money);

    void addScore(int score);
}
