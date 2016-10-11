package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.other.callbacks.IntroCallback;
import com.aston.group.stationdefender.other.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private FreeTypeFontGenerator fontGenerator;
    private BitmapFont font;
    private IntroCallback introCallback;
    private float fadeElapsed = 0;

    public IntroScreen() {
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

    @Override
    public void render(float delta) {
        // fade in animation
        fadeElapsed += delta;
        float fadeInTime = 2f;
        float fadeDelay = 1f;
        float fade = Interpolation.fade.apply(fadeElapsed / fadeInTime);
        float fade2 = Interpolation.fade.apply((fadeElapsed - fadeDelay) / fadeInTime);

        //render
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // fade-in animation
        font.setColor(1, 1, 1, fade);
        font.draw(batch, Constants.GAME_NAME, (Gdx.graphics.getWidth() / 2) - 200, Gdx.graphics.getHeight() - 25);

        // delay animation by a certain amount for each menu item
        font.setColor(1, 1, 1, fade2);
        for (int i = 0; i < Constants.MENU_ITEMS.length; i++) {
            font.draw(batch, Constants.MENU_ITEMS[i], (Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (100 - 60 * i));
            fade2 = Interpolation.fade.apply((fadeElapsed - (fadeDelay + i + 1f)) / fadeInTime);
            font.setColor(1, 1, 1, fade2);
        }

        batch.end();

        // input
        takeInput();

    }

    private void takeInput(){
        if(Gdx.input.isTouched()){
            introCallback.onDisplayMenu();
        }
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
        fontGenerator.dispose();
        font.dispose();
        batch.dispose();
    }

    public void setIntroCallback(IntroCallback introCallback) {
        this.introCallback = introCallback;
    }
}