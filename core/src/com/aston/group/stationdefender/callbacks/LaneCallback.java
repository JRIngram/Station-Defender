package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.gamesetting.items.Item;

/**
 * LaneCallback is the callback class for Lane
 *
 * @author Jonathon Fitch
 */
public interface LaneCallback {

    /**
     * Callback to add money to the Player
     *
     * @param money The amount of money to add to the Player
     */
    void addMoney(int money);

    /**
     * Callback to add points to the Player
     *
     * @param score The amount of points to add to the Player
     */
    void addScore(int score);

    /**
     * Callback to tell the Tower to take damage
     *
     * @param damage The amount of damage for the Tower to take
     */
    void towerTakeDamage(double damage);

    /**
     * Callback to check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Lanes X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x      The X co-ordinate of the object to check
     * @param y      The Y co-ordinate of the object to check
     * @param width  The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    boolean isTowerColliding(int x, int y, int width, int height);

    /**
     * Callback to collect an Item
     *
     * @param item The Item to be collected
     */
    void collectItem(Item item);
}