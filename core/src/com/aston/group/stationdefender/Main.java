package com.aston.group.stationdefender;

import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.callbacks.TwoTextCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.screens.*;
import com.aston.group.stationdefender.utils.SoundManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * The Main game class.
 *
 * @author Jonathon Fitch
 */
public class Main extends Game implements GameCallback, TwoTextCallback, MenuCallback {
    private IntroScreen introScreen;
    private TwoTextScreen backgroundScreen;
    private TwoTextScreen instructionScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private int levelNumber;
    private int totalScore;

    @Override
    public void create() {
        backgroundScreen = new TwoTextScreen(false);
        instructionScreen = new TwoTextScreen(false);
        introScreen = new IntroScreen();
        menuScreen = new MenuScreen();
        levelNumber = 1;
        totalScore = 0;
        initGame();

        // Setup title and body text
        backgroundScreen.setTitle(Constants.MENU_ITEMS[0]);
        backgroundScreen.setBody(Constants.BACKGROUND);
        backgroundScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 500);
        backgroundScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (185));
        instructionScreen.setTitle(Constants.MENU_ITEMS[1]);
        instructionScreen.setBody(Constants.INSTRUCTIONS);
        instructionScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 500);
        instructionScreen.setBodyY((Gdx.graphics.getHeight() / 2) + (185));

        // Setup callbacks
        introScreen.setMenuCallback(this);
        backgroundScreen.setTwoTextCallback(this);
        instructionScreen.setTwoTextCallback(this);
        menuScreen.setMenuCallback(this);

        //Set the screen to intro upon creation if debug flag is not set
        if (!Constants.DEBUG) {
            setScreen(introScreen);
        } else {
            setScreen(gameScreen);
        }
        SoundManager.INSTANCE.playSound(1);
    }

    /**
     * Disposes of any existing GameScreen then create a new one
     */
    private void initGame() {
        if (gameScreen != null)
            gameScreen.dispose();
        gameScreen = new GameScreen(this, levelNumber);
    }

    @Override
    public void onWinLost(boolean won, int score, int money) {
        PostLevelScreen postLevelScreen;
        String title;
        if (won) {
            title = "Level cleared";
            postLevelScreen = new PostLevelScreen(true);
            levelNumber++;
        } else {
            title = "You failed!";
            postLevelScreen = new PostLevelScreen(false);
        }
        totalScore += score;
        postLevelScreen.setTitle(title);
        postLevelScreen.setBody("Level Score: " + score + "\nMoney: £" + money + "\n\nTotal Score: " + totalScore);
        postLevelScreen.setTitleX((Gdx.graphics.getWidth() / 2) - 125);
        postLevelScreen.setTitleY(Gdx.graphics.getHeight() - 25);
        postLevelScreen.setBodyX((Gdx.graphics.getWidth() / 2) - 110);
        postLevelScreen.setBodyY((Gdx.graphics.getHeight() / 2) + 75);
        postLevelScreen.setTwoTextCallback(this);
        if (!won)
            totalScore = 0;
        setScreen(postLevelScreen);
    }

    @Override
    public void onPause() {
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