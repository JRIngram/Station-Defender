package com.aston.group.stationdefender.callbacks;

/**
 * GameCallback is the callback class for GameScreen
 *
 * @author Mohammed Foysal
 */
public interface GameCallback {
    void onWinLost(boolean won, int score, int money);

    void onPause();
}