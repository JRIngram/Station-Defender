package com.aston.group.stationdefender;

import com.aston.group.stationdefender.callbacks.BackgroundCallback;
import com.aston.group.stationdefender.callbacks.IntroCallback;
import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.screens.BackgroundScreen;
import com.aston.group.stationdefender.screens.GameScreen;
import com.aston.group.stationdefender.screens.IntroScreen;
import com.aston.group.stationdefender.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
    private IntroScreen introScreen;
    private BackgroundScreen backgroundScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {

        backgroundScreen = new BackgroundScreen();
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

            @Override
            public void onExit() {
                Gdx.app.exit();
            }

            @Override
            public void onDisplayBackground() {
                setScreen(backgroundScreen);
            }
        });

        backgroundScreen.setBackgroundCallback(new BackgroundCallback() {
            @Override
            public void onBack() {
                setScreen(introScreen);
            }
        });

        menuScreen.setMenuCallback(new MenuCallback() {
            @Override
            public void onPlay() {
                setScreen(gameScreen);
            }

            @Override
            public void onExit() {
                Gdx.app.exit();
            }
        });

        //Set the screen to intro upon creation
        if(!Constants.DEBUG){
            setScreen(introScreen);
        }else{
            setScreen(gameScreen);
        }

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        introScreen.dispose();

        super.dispose();
    }
}