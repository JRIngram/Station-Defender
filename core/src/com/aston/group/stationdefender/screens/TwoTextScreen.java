package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.TwoTextCallback;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * TwoTextScreen is a class that creates a screen holding a title text,
 * a body of text and a back button.
 *
 * @author Jonathon Fitch
 */
public class TwoTextScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private TwoTextCallback twoTextCallback;
    private Stage stage;
    private BitmapFont titleFont;
    private BitmapFont bodyFont;
    private TextButton backButton;
    private float fadeElapsed = 0;
    private String title;
    private String body;
    private ChangeListener buttonListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (actor.equals(backButton)) {
                twoTextCallback.onBack();
            }
        }
    };

    /**
     * Constructor sets the camera, viewpoint and
     * initializes the font and button(s).
     */
    public TwoTextScreen() {
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);

        //Initialise Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontParameter params = new FreeTypeFontParameter();
        params.size = 18;
        BitmapFont buttonFont = fontGenerator.generateFont(params);
        params.size = 30;
        bodyFont = fontGenerator.generateFont(params);
        params.size = 50;
        titleFont = fontGenerator.generateFont(params);

        //Buttons
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = buttonFont;
        backButton = new TextButton(Constants.BACK, textButtonStyle);
        backButton.setColor(0, 0, 0, 0);
        backButton.setWidth(400);
        backButton.setHeight(50);
        stage.addActor(backButton);
        backButton.addListener(buttonListener);
        backButton.setPosition(-150, (Gdx.graphics.getHeight()) - 60);
    }

    /**
     * Show the TwoTextScreen as the main screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Render the TwoTextScreen.
     * @param delta - The time in seconds since the last render.
     */
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

        // Draw things on screen
        batch.begin();
        titleFont.setColor(1, 1, 1, fade);
        backButton.setColor(1, 1, 1, fade);
        titleFont.draw(batch, title, (Gdx.graphics.getWidth() / 2) - 150, Gdx.graphics.getHeight() - 25);
        restartBatch();
        stage.draw();
        restartBatch();

        // delay animation by a certain amount for each menu item
        bodyFont.setColor(1, 1, 1, fade2);
        bodyFont.draw(batch, body, (Gdx.graphics.getWidth() / 2) - 235, (Gdx.graphics.getHeight() / 2) + (100 - 60));
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
        stage.dispose();
        batch.dispose();
    }

    /**
     * Sets the TwoTextCallback to be used within this class
     *
     * @param twoTextCallback The TwoTextCallback supplied in Main.java
     */
    public void setTwoTextCallback(TwoTextCallback twoTextCallback) {
        this.twoTextCallback = twoTextCallback;
    }

    /**
     * Sets the text of the screen title
     *
     * @param title The String to set as the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the text of the body message
     *
     * @param body The String to set as the body message
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Restart the batch to allow objects to be drawn over the stage.
     */
    private void restartBatch() {
        batch.end();
        batch.begin();
    }
}