package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.LevelCallback;
import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.gamesetting.Level;
import com.aston.group.stationdefender.gamesetting.Player;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

/**
 * This screen holds the main game loop
 *
 * @author Mohammad Foysal
 */
public class GameScreen implements Screen, PlayerCallback, LevelCallback {
    private final Player player;
    private final GameCallback gameCallback;
    private final Level level;
    private final GameEngine gameEngine;

    /**
     * Create a new GameScreen with a specified GameCallBack and LevelNumber
     *
     * @param gameCallback The GameCallBAck to be used for the GameScreen
     * @param levelNumber  The Level Number for the game
     */
    public GameScreen(final GameCallback gameCallback, int levelNumber) {
        this.gameCallback = gameCallback;
        gameEngine = GameEngine.INSTANCE;

        player = new Player();
        player.setPlayerCallback(this);
        level = new Level(this, levelNumber);

        MouseInput.setPosition(new Vector2());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameEngine.render();
        level.render(delta);
        player.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        gameEngine.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        level.dispose();
        player.dispose();
    }

    @Override
    public void onWinLost(boolean won) {
        gameCallback.onWinLost(player.getInventory(), won, player.getScore(), player.getMoney());
    }

    @Override
    public void addMoney(int money) {
        player.addMoney(money);
    }

    @Override
    public void addScore(int score) {
        player.addScore(score);
    }

    @Override
    public void collectItem(Item item) {
        player.collectItem(item);
    }

    @Override
    public boolean placeUnit(Unit unit, int x, int y) {
        return level.place(unit, x, y);
    }

    @Override
    public void onPause() {
        gameCallback.onPause();
    }

    @Override
    public void addHealth(int health) {
        level.towerAddHealth(health);
    }
}