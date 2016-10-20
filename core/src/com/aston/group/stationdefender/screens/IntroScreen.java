package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.IntroCallback;
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

public class IntroScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private IntroCallback introCallback;
    private Stage stage;
    private BitmapFont font;
    private TextButton introButton, instructionButton, playButton, exitButton;
    private float fadeElapsed = 0;
    private TextButton[] buttons;
    private ChangeListener buttonListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (actor.equals(introButton)) {
                //TODO: Add with pitch/game setting screen
            } else if (actor.equals(instructionButton)) {
                //TODO: Add with instructions screen
            } else if (actor.equals(playButton)) {
                introCallback.onPlay();
            } else if (actor.equals(exitButton)) {
                introCallback.onExit();
            }
        }
    };

    public IntroScreen() {
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
        introButton = new TextButton(Constants.MENU_ITEMS[0], textButtonStyle);
        instructionButton = new TextButton(Constants.MENU_ITEMS[1], textButtonStyle);
        playButton = new TextButton(Constants.MENU_ITEMS[2], textButtonStyle);
        exitButton = new TextButton(Constants.MENU_ITEMS[3], textButtonStyle);
        buttons = new TextButton[]{introButton, instructionButton, playButton, exitButton};
        for (TextButton button : buttons) {
            button.setColor(0, 0, 0, 0);
            button.setWidth(400);
            button.setHeight(50);
            stage.addActor(button);
            button.addListener(buttonListener);
        }
        for (int i = 0; i < Constants.MENU_ITEMS.length; i++) {
            buttons[i].setPosition((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (100 - 60 * i));
        }
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
        font.draw(batch, Constants.GAME_NAME, (Gdx.graphics.getWidth() / 2) - 200, Gdx.graphics.getHeight() - 25);
        batch.end();
        batch.begin();
        stage.draw();
        // delay animation by a certain amount for each menu item
        font.setColor(1, 1, 1, fade2);
        for (int i = 0; i < buttons.length; i++) {
            fade2 = Interpolation.fade.apply((fadeElapsed - (fadeDelay + i + 1f)) / fadeInTime);
            buttons[i].setColor(1, 1, 1, fade2);
        }
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

    public void setIntroCallback(IntroCallback introCallback) {
        this.introCallback = introCallback;
    }
}