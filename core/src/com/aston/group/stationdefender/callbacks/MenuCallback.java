package com.aston.group.stationdefender.callbacks;

/**
 * MenuCallback is the callback class for MenuScreen
 *
 * @author Jonathon Fitch
 */
public interface MenuCallback {

    /**
     * The callback to continue the game whether the Player left off
     *
     * @param refresh Whether to dump the current GameScreen or not
     */
    void onPlay(boolean refresh);

    /**
     * The callback to exit the game
     */
    void onExit();
}