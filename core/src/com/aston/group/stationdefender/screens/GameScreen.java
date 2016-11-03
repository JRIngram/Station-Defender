package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Level;
import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * This screen holds the main game loop
 *
 * @author Mohammed Foysal
 */
public class GameScreen implements Screen {
    private static ArrayList<Actor> actorBufferA = new ArrayList<Actor>();
    private static ArrayList<Actor> actorBufferB = new ArrayList<Actor>();
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Level level;
    private Player player;

    /**
     * Construct a new GameScreen
     */
    public GameScreen() {
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        level = new Level();

        player = new Player();
        player.setPlayerCallback(new PlayerCallback() {
            @Override
            public void placeActor(Actor actor, int x, int y) {
                //todo implement place actor
            }
        });
    }

    /**
     * Refresh the GameScreen.
     * @param delta - The time in seconds since the last render.
     */
    public static void refresh(float delta) {
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

    /**
     * Show the GameScreen.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(player);
    }

    /**
     * Render the GameScreen.
     * @param delta - The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    /**
     * Dispose of unused resources
     */
    @Override
    public void dispose() {
        batch.dispose();
    }
}