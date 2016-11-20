package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.LevelCallback;
import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.Level;
import com.aston.group.stationdefender.gamesetting.Player;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This screen holds the main game loop
 *
 * @author Mohammad Foysal
 */
public class GameScreen implements Screen, PlayerCallback, LevelCallback {
    private final Array<Actor> actorBufferB = new Array<>();
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Player player;
    private final GameCallback gameCallback;
    private final Level level;
    private Array<Actor> actorBufferA = new Array<>();

    /**
     * Create a new GameScreen with a specified GameCallBack and LevelNumber
     *
     * @param gameCallback The GameCallBAck to be used for the GameScreen
     * @param levelNumber  The Level Number for the game
     */
    public GameScreen(final GameCallback gameCallback, int levelNumber) {
        this.gameCallback = gameCallback;
        batch = new SpriteBatch();

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

    /**
     * Refresh all Actors to see if they exist and render them if they do
     *
     * @param delta The time in seconds since the last render
     */
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

        Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        vector2 = viewport.unproject(vector2);
        MouseInput.setPosition(vector2);

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
        level.getBoard().place(new Weapon(x, y), x, y);
    }

    @Override
    public void onPause() {
        gameCallback.onPause();
    }
}
