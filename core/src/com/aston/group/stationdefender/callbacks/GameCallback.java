package com.aston.group.stationdefender.callbacks;

public interface GameCallback {

    void onWin(int score, int money);
    void onLost(int score, int money);
    void onPaused();

}
