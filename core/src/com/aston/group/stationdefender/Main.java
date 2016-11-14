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
import com.aston.group.stationdefender.utils.SoundManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * The Main game class.
 *
 * @author Jonathon Fitch
 */
public class Main extends Game implements GameCallback, TwoTextCallback, IntroCallback, MenuCallback {
    private IntroScreen introScreen;
    private TwoTextScreen backgroundScreen;
    private TwoTextScreen instructionScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private int levelNumber;

    @Override
    public void create() {
        backgroundScreen = new TwoTextScreen(false);
        instructionScreen = new TwoTextScreen(false);
        introScreen = new IntroScreen();
        menuScreen = new MenuScreen();
        levelNumber = 1;
        new SoundManager();
        initGame();

        // Setup title and body text
        backgroundScreen.setTitle(Constants.MENU_ITEMS[0]);
        backgroundScreen.setBody(Constants.BACKGROUND);
        backgroundScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 500);
        backgroundScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (185));
        instructionScreen.setTitle(Constants.MENU_ITEMS[1]);
        instructionScreen.setBody(Constants.INSTRUCTIONS);
        instructionScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 190);
        instructionScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (100 - 60));

        // Setup callbacks
        introScreen.setIntroCallback(this);
        backgroundScreen.setTwoTextCallback(this);
        instructionScreen.setTwoTextCallback(this);
        menuScreen.setMenuCallback(this);

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

    private void initGame() {
        if (gameScreen != null)
            gameScreen.dispose();
        gameScreen = new GameScreen(this, levelNumber);
    }

    @Override
    public void onWinLost(boolean won, int score, int money) {
        TwoTextScreen twoTextScreen;
        String title;
        if (won) {
            title = "YOU WON";
            twoTextScreen = new TwoTextScreen(true);
            levelNumber++;
        } else {
            title = "YOU LOST";
            twoTextScreen = new TwoTextScreen(false);
        }
        twoTextScreen.setTitle(title);
        twoTextScreen.setBody("Score: " + score + " - Money: £" + money);
        twoTextScreen.setTitleX((Gdx.graphics.getWidth() / 2) - 125);
        twoTextScreen.setTitleY(Gdx.graphics.getHeight() - 25);
        twoTextScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 150);
        twoTextScreen.setTwoTextCallback(this);
        setScreen(twoTextScreen);
    }

    @Override
    public void onPaused() {
        setScreen(menuScreen);
    }

    @Override
    public void onBack() {
        setScreen(introScreen);
    }

    @Override
    public void onContinue() {
        initGame();
        setScreen(gameScreen);
    }

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
        if (refresh) initGame();
        setScreen(gameScreen);
    }

    @Override
    public void onExit() {
        Gdx.app.exit();
    }
}