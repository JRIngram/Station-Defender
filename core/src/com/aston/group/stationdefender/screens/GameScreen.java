package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.LevelCallback;
import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Level;
import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This screen holds the main game loop
 *
 * @author Mohammad Foysal
 */
public class GameScreen implements Screen, PlayerCallback, LevelCallback {
    private final Array<Actor> actorBufferB = new Array<Actor>();
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Player player;
    private final GameCallback gameCallback;
    private final Level level;
    private Array<Actor> actorBufferA = new Array<Actor>();

    public GameScreen(final GameCallback gameCallback, int levelNumber) {
        batch = new SpriteBatch();
        this.gameCallback = gameCallback;

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);

        player = new Player();
        player.setPlayerCallback(this);
        level = new Level(player, this, levelNumber);
    }

    private void refresh(float delta) {
        for (Actor a : actorBufferA) {
            a.render(delta);
        }
        for (Actor a : actorBufferA) {
            if (a.getExists()) {
                actorBufferB.add(a);
            }
        }
        actorBufferA.clear();
        actorBufferA = actorBufferB;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        refresh(delta);
        level.render(delta);
        player.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        batch.dispose();
    }

    @Override
    public void onWinLost(boolean won) {
        gameCallback.onWinLost(won, player.getScore(), player.getMoney());
    }

    @Override
    public void placeActor(Actor actor, int x, int y) {
        //TODO: Change Actor to Unit
        y = Gdx.graphics.getHeight() - y;
        level.getBoard().place(new Weapon(x, y), x, y);
    }
}
