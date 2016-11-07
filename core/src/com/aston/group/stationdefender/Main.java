package com.aston.group.stationdefender;

import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.IntroCallback;
import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.callbacks.TwoTextCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.screens.GameScreen;
import com.aston.group.stationdefender.screens.IntroScreen;
import com.aston.group.stationdefender.screens.MenuScreen;
import com.aston.group.stationdefender.screens.TwoTextScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * The Main game class.
 *
 * @author Jonathon Fitch
 */
public class Main extends Game implements GameCallback, TwoTextCallback{
    private IntroScreen introScreen;
    private TwoTextScreen backgroundScreen;
    private TwoTextScreen instructionScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {
        backgroundScreen = new TwoTextScreen();
        instructionScreen = new TwoTextScreen();
        introScreen = new IntroScreen();
        menuScreen = new MenuScreen();
        initGame();

        // Setup title and body text
        backgroundScreen.setTitle(Constants.MENU_ITEMS[0]);
        backgroundScreen.setBody(Constants.BACKGROUND);
        backgroundScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 235);
        backgroundScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (100 - 60));
        instructionScreen.setTitle(Constants.MENU_ITEMS[1]);
        instructionScreen.setBody(Constants.INSTRUCTIONS);
        instructionScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 190);
        instructionScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (100 - 60));

        // Setup callbacks
        setupCallbacks();

        //Set the screen to intro upon creation if debug flag is not set
        if (!Constants.DEBUG) {
            setScreen(introScreen);
        } else {
            setScreen(gameScreen);
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets the callbacks to be used for each screen and adds actions based
     * on which callback method is used.
     */
    private void setupCallbacks() {
        introScreen.setIntroCallback(new IntroCallback() {
            @Override
            public void onDisplayBackground() {
                setScreen(backgroundScreen);
            }

            @Override
            public void onDisplayInstructions() {
                setScreen(instructionScreen);
            }

            @Override
            public void onPlay(boolean refresh) {
                if(refresh) initGame();
                setScreen(gameScreen);
            }

            @Override
            public void onExit() {
                Gdx.app.exit();
            }
        });

        backgroundScreen.setTwoTextCallback(this);

        instructionScreen.setTwoTextCallback(this);

        menuScreen.setMenuCallback(new MenuCallback() {
            @Override
            public void onPlay(boolean refresh) {
                if(refresh) initGame();
                setScreen(gameScreen);
            }

            @Override
            public void onExit() {
                Gdx.app.exit();
            }
        });
    }

    private void initGame(){
        if(gameScreen != null)
            gameScreen.dispose();
        gameScreen = new GameScreen(this);
    }

    @Override
    public void onWin(int score, int money) {
        TwoTextScreen twoTextScreen = new TwoTextScreen();
        twoTextScreen.setTitle("YOU WON");
        twoTextScreen.setBody("Score: " + score + " - Money: £" + money);
        twoTextScreen.setTitleX((Gdx.graphics.getWidth() / 2) - 125);
        twoTextScreen.setTitleY(Gdx.graphics.getHeight() - 25);
        twoTextScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 150);
        twoTextScreen.setTwoTextCallback(this);
        setScreen(twoTextScreen);
    }

    @Override
    public void onLost(int score, int money) {
        TwoTextScreen twoTextScreen = new TwoTextScreen();
        twoTextScreen.setTitle("YOU LOST");
        twoTextScreen.setBody("Score: " + score + " - Money: £" + money);
        twoTextScreen.setTitleX((Gdx.graphics.getWidth() / 2) - 125);
        twoTextScreen.setTitleY(Gdx.graphics.getHeight() - 25);
        twoTextScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 150);
        twoTextScreen.setTwoTextCallback(this);
        setScreen(twoTextScreen);
    }

    @Override
    public void onPaused() {
    }

    @Override
    public void onBack() {
        setScreen(introScreen);
    }
}