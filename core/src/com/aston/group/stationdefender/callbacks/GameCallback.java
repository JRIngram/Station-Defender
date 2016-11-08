package com.aston.group.stationdefender.callbacks;

/**
 * GameCallback is the callback class for GameScreen
 *
 * @author Mohammed Foysal
 */
public interface GameCallback {

    void onWin(int score, int money);
    void onLost(int score, int money);
    void onPaused();

}
