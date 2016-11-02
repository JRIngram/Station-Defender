package com.aston.group.stationdefender.callbacks;

/**
 * IntroCallback is the callback class for IntroScreen
 *
 * @author Jonathon Fitch
 */
public interface IntroCallback {
    void onDisplayBackground();

    void onDisplayInstructions();

    void onPlay();

    void onExit();
}