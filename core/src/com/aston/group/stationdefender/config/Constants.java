package com.aston.group.stationdefender.config;

/**
 * This class is for constant known variables used throughout the game
 *
 * @author Mohammed Foysal
 */
public interface Constants {

    boolean DEBUG = false;

    // 16/9 Aspect Ratio
    int SCREEN_WIDTH = 1024;
    int SCREEN_HEIGHT = 576;

    String GAME_NAME = "Station Defender";

    String[] MENU_ITEMS = {
            "Background",
            "How to Play",
            "Play",
            "Exit"
    };

    String BACKGROUND = "Background paragraph will go here";

    String BACK = "Back";

    String INSTRUCTIONS = "Instruction text will go here";

    int TILE_WIDTH = 80;
    int TILE_HEIGHT = 80;
    int TILE_AMOUNT = 12;

    int TOWER_HEALTH = 1000;
    int UNIT_HEALTH = 100;
    int WEAPON_HEALTH = 100;
    int DEFAULT_DAMAGE = 100;
}