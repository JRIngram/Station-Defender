package com.aston.group.stationdefender.callbacks;

/**
 * TwoTextCallback is the callback class for TwoTextScreen.
 *
 * @author Jonathon Fitch
 */
public interface TwoTextCallback {

    /**
     * The callback to go back to the IntroScreen
     */
    void onBack();

    /**
     * The callback to continue with the game
     */
    void onContinue();
}