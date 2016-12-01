package com.aston.group.stationdefender.callbacks;

/**
 * LaneCallback is the callback class for Lane
 *
 * @author Jonathon Fitch
 */
public interface LaneCallback {
    void addMoney(int money);

    void addScore(int score);

    void towerTakeDamage(double damage);

    boolean isTowerColliding(int x, int y, int width, int height);
}