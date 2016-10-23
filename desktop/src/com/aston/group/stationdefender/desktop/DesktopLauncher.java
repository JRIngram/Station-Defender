package com.aston.group.stationdefender.desktop;

import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aston.group.stationdefender.Main;

/**
 * The Launcher class for the game on Desktop PC.
 * @author Jonathon Fitch
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = Constants.GAME_NAME;
		config.width = Constants.SCREEN_WIDTH;
		config.height = Constants.SCREEN_HEIGHT;

		new LwjglApplication(new Main(), config);
	}
}