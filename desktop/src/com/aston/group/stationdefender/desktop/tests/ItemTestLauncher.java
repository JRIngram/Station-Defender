package com.aston.group.stationdefender.desktop.tests;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.tests.ItemTest;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ItemTestLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = Constants.GAME_NAME;
        config.width = Constants.SCREEN_WIDTH;
        config.height = Constants.SCREEN_HEIGHT;

        new LwjglApplication(new ItemTest(), config);
    }

}
