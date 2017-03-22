package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.utils.resources.Inventory;

/**
 * GameCallback is the callback class for GameScreen
 *
 * @author Mohammed Foysal
 */
public interface GameCallback {
    /**
     * The win/lost situation callback
     *
     * @param inventory Player's inventory
     * @param won       Whether the Player has won the game or not
     * @param score     The Player's score
     * @param money     The amount of money the Player has
     */
    void onWinLost(Inventory inventory, boolean won, int score, int money);

    /**
     * The pause game situation callback
     */
    void onPause();
}