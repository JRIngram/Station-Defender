package com.aston.group.stationdefender;

import com.aston.group.stationdefender.callbacks.IntroCallback;
import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.screens.GameScreen;
import com.aston.group.stationdefender.screens.IntroScreen;
import com.aston.group.stationdefender.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {
	private IntroScreen introScreen;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;

	@Override
	public void create () {

		introScreen = new IntroScreen();
		menuScreen = new MenuScreen();
		gameScreen = new GameScreen();

		introScreen.setIntroCallback(new IntroCallback() {
            @Override
            public void onPlay() {
                setScreen(gameScreen);
            }
            
			@Override
			public void onDisplayMenu() {
				setScreen(menuScreen);
			}
		});

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

		//Set the screen to intro upon creation
		setScreen(introScreen);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		introScreen.dispose();

		super.dispose();
	}
}