package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.BackgroundCallback;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BackgroundScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BackgroundCallback backgroundCallback;
    private Stage stage;
    private BitmapFont font;
    private TextButton backButton;
    private float fadeElapsed = 0;
    private ChangeListener buttonListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (actor.equals(backButton)) {
                backgroundCallback.onBack();
            }
        }
    };

    public BackgroundScreen() {
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);

        //Initialise Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 50;
        font = fontGenerator.generateFont(params);

        //Buttons
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        backButton = new TextButton(Constants.BACKGROUND, textButtonStyle);
        backButton.setColor(0, 0, 0, 0);
        backButton.setWidth(400);
        backButton.setHeight(50);
        stage.addActor(backButton);
        backButton.addListener(buttonListener);
        backButton.setPosition((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (100 - 60));
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
        font.setColor(1, 1, 1, fade);
        font.draw(batch, Constants.MENU_ITEMS[0], (Gdx.graphics.getWidth() / 2) - 150, Gdx.graphics.getHeight() - 25);
        batch.end();
        batch.begin();
        stage.draw();
        // delay animation by a certain amount for each menu item
        font.setColor(1, 1, 1, fade2);
        fade2 = Interpolation.fade.apply((fadeElapsed - (fadeDelay + 1f)) / fadeInTime);
        backButton.setColor(1, 1, 1, fade2);
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

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        batch.dispose();
    }

    public void setBackgroundCallback(BackgroundCallback backgroundCallback) {
        this.backgroundCallback = backgroundCallback;
    }
}