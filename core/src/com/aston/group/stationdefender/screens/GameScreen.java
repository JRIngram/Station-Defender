package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.actors.Actor;
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
 * @author Mohammad Foysal
 */
public class GameScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Level level;
    private Player player;
    private static ArrayList<Actor> actorBufferA = new ArrayList<Actor>();
    private static ArrayList<Actor> actorBufferB = new ArrayList<Actor>();
    private static byte mainUpdateBuffer = (byte) 0;

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


    public static void refresh(float delta) {
        for (Actor a : getMainBuffer()) {
            a.render(delta);
        }

        for (Actor a : getMainBuffer()) {
            if (a.getExists()) {
                getOtherBuffer().add(a);
            }
        }
        getMainBuffer().clear();
        mainUpdateBuffer = (byte) (mainUpdateBuffer == 0 ? 1 : 0);
    }

    private static ArrayList<Actor> getMainBuffer() {
        return (mainUpdateBuffer == 0 ? actorBufferA : actorBufferB);
    }

    private static ArrayList<Actor> getOtherBuffer() {
        return (mainUpdateBuffer == 1 ? actorBufferA : actorBufferB);
    }
}