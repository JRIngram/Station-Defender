package com.aston.group.stationdefender.config;

import com.badlogic.gdx.graphics.Color;

/**
 * This class is for constant known variables used throughout the game
 *
 * @author Mohammed Foysal
 */
public interface Constants {

    boolean DEBUG = true;

    // 16/9 Aspect Ratio
    int SCREEN_WIDTH = 1024;
    int SCREEN_HEIGHT = 576;

    String GAME_NAME = "Station Defenders";

    String[] MENU_ITEMS = {
            "Background",
            "How to Play",
            "Play",
            "Exit"
    };

    String BACKGROUND = "During an era of peace, 2 centuries into the future humans are attacked\n " +
            "on a space station by their worst fear. Aliens are trying to invade the space\n " +
            "station and the battle has commenced. In order for the survival of the\n " +
            "human race we must defend our most valued Command Centre,\n " +
            "without this the security of our world will be compromised.\n " +
            "Soldiers of various abilities have been brought to the front-line,\n " +
            "equipped with different resources some effective at defense whilst others\n " +
            "at attacking the extraterrestrial species.\n " +
            "However the Aliens are not to be underestimated, attacking in\n " +
            "numbers and with full force.\n\n" +
            "Only with quick and precise strategic thinking can our world be saved!";

    String BACK = "Back";

    String CONTINUE = "Continue";

    String MENU = "Menu";

    String INSTRUCTIONS = "The aim of the game is to defend the command centre from the swarm \nof aliens abroad the space station." +
            "To defend the station from the aliens\nyou can use a range of weapons which can be found in your inventory\nlocated on the bottom left." +
            "\n\nUse your resources carefully as all weapons cost money to build.\n" +
            "Once a weapon is selected place it on a tile on the board and start\ndefeating the aliens, the more aliens you kill the more money you receive\nto use on better weapons.";

    int TILE_WIDTH = 80;
    int TILE_HEIGHT = 80;
    int TILE_AMOUNT = 11;
    int LANE_AMOUNT = 4;

    int TOWER_HEALTH = 1000;
    int UNIT_HEALTH = 100;
    int WEAPON_HEALTH = 100;
    int DEFAULT_DAMAGE = 50;

    int START_MONEY = 50;
    int MONEY_REGENERATION = 1;
    int ADD_SCORE_AMOUNT = 10;

    Color primaryColor = Color.valueOf("#37474F");
    Color primaryDarkColor = Color.valueOf("#263238");
    Color accentColor = Color.valueOf("#f44336");

    float VERSION = 1.1f;
}