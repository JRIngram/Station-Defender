package com.aston.group.stationdefender;

import com.aston.group.stationdefender.other.callbacks.MenuCallback;
import com.aston.group.stationdefender.screens.GameScreen;
import com.aston.group.stationdefender.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {

	private MenuScreen menuScreen;
	private GameScreen gameScreen;

	@Override
	public void create () {

		menuScreen = new MenuScreen();
		gameScreen = new GameScreen();

		menuScreen.setMenuCallback(new MenuCallback() {
			@Override
			public void onPlay() {

				setScreen(gameScreen);
			}

			@Override
			public void onExit() {
				System.exit(0);
			}
		});

		//Set the screen to menu upon creation
		setScreen(menuScreen);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		menuScreen.dispose();

		super.dispose();
	}
}