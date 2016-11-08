package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.IntroCallback;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * IntroScreen is the first screen shown in the game and contains
 * the game title and buttons that link to other screens such as the
 * background information screen, instruction screen etc.
 *
 * @author Jonathon Fitch
 */
public class IntroScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Stage stage;
    private final BitmapFont font;
    private final TextButton backgroundButton, instructionButton, playButton, exitButton;
    private final TextButton[] buttons;
    private final Texture texture;
    private IntroCallback introCallback;
    private float fadeElapsed = 0;

    /**
     * Construct a new IntroScreen
     */
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
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        backgroundButton = new TextButton(Constants.MENU_ITEMS[0], textButtonStyle);
        instructionButton = new TextButton(Constants.MENU_ITEMS[1], textButtonStyle);
        playButton = new TextButton(Constants.MENU_ITEMS[2], textButtonStyle);
        exitButton = new TextButton(Constants.MENU_ITEMS[3], textButtonStyle);
        buttons = new TextButton[]{backgroundButton, instructionButton, playButton, exitButton};
        for (TextButton button : buttons) {
            button.setColor(0, 0, 0, 0);
            button.setWidth(400);
            button.setHeight(50);
            stage.addActor(button);
            ChangeListener buttonListener = new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                    if (actor.equals(backgroundButton)) {
                        introCallback.onDisplayBackground();
                    } else if (actor.equals(instructionButton)) {
                        introCallback.onDisplayInstructions();
                    } else if (actor.equals(playButton)) {
                        introCallback.onPlay(true);
                    } else if (actor.equals(exitButton)) {
                        introCallback.onExit();
                    }
                }
            };
            button.addListener(buttonListener);
        }
        for (int i = 0; i < Constants.MENU_ITEMS.length; i++) {
            buttons[i].setPosition((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (100 - 60 * i));
        }
        texture = new Texture(Gdx.files.internal("textures/intro-back.jpg"));
    }

    /**
     * Show the IntroScreen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Render the IntroScreen.
     *
     * @param delta - The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // fade in animation
        fadeElapsed += delta;
        float fadeInTime = .5f;
        float fadeDelay = 0f;
        float fade = Interpolation.fade.apply((fadeElapsed - fadeDelay) / fadeInTime);

        //render
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        batch.begin();
        stage.draw();
        // delay animation by a certain amount for each menu item
        font.setColor(1, 1, 1, fade);
        for (int i = 0; i < buttons.length; i++) {
            fade = Interpolation.fade.apply((fadeElapsed - (fadeDelay + i + 1f)) / fadeInTime);
            buttons[i].setColor(1, 1, 1, fade);
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

    /**
     * Dispose of unused resources
     */
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