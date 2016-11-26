package com.aston.group.stationdefender.callbacks;

/**
 * MenuCallback is the callback class for IntroScreen
 *
 * @author Jonathon Fitch
 */
public interface MenuCallback {

    /**
     * The callback to display background information
     */
    void onDisplayBackground();

    /**
     * The callback to display the game instructions
     */
    void onDisplayInstructions();

    /**
     * The callback to play the game
     *
     * @param refresh Whether to dump the current GameScreen or not
     */
    void onPlay(boolean refresh);

    /**
     * The callback to exit the game
     */
    void onExit();
}