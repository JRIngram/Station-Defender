package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * MenuScreen is the screen shown when pressing the menu button
 * from within the game.  It shows options such as play, exit, save etc.
 *
 * @author Jonathon Fitch
 */
public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final FreeTypeFontGenerator fontGenerator;
    private final BitmapFont font;
    private MenuCallback menuCallback;

    /**
     * Construct a new MenuScreen
     */
    public MenuScreen() {
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);

        //Initialise Font
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 50;
        font = fontGenerator.generateFont(params);
    }

    @Override
    public void show() {
    }

    /**
     * Render the MenuScreen.
     *
     * @param delta - The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //render
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, Constants.GAME_NAME, (Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + 25);
        batch.end();
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
        fontGenerator.dispose();
        font.dispose();
        batch.dispose();
    }

    public void setMenuCallback(MenuCallback menuCallback) {
        this.menuCallback = menuCallback;
    }
}