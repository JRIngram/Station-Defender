package com.aston.group.stationdefender.callbacks;

/**
 * MenuCallback is the callback class for MenuScreen
 *
 * @author Jonathon Fitch
 */
public interface MenuCallback {
    void onPlay(boolean refresh);

    void onExit();
}