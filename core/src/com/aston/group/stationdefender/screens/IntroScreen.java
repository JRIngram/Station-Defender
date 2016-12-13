package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Objects;

/**
 * IntroScreen is the first screen shown in the game and contains
 * the game title and buttons that link to other screens such as the
 * background information screen, instruction screen etc.
 *
 * @author Jonathon Fitch
 */
public class IntroScreen implements Screen {
    private final SpriteBatch batch;
    private final Stage stage;
    private final BitmapFont font;
    private final BitmapFont smallerFont;
    private final TextButton backgroundButton, instructionButton, playButton, exitButton;
    private final TextButton[] buttons;
    private final Texture texture;
    private final GameEngine gameEngine;
    private MenuCallback menuCallback;
    private float fadeElapsed = 0;

    /**
     * Construct a new IntroScreen
     */
    public IntroScreen() {
        gameEngine = GameEngine.INSTANCE;
        batch = GameEngine.getBatch();

        font = FontManager.getFont(50);
        smallerFont = FontManager.getFont(16);

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
                    if (Objects.equals(actor, backgroundButton)) {
                        menuCallback.onDisplayBackground();
                    } else if (Objects.equals(actor, instructionButton)) {
                        menuCallback.onDisplayInstructions();
                    } else if (Objects.equals(actor, playButton)) {
                        menuCallback.onPlay(true);
                    } else if (Objects.equals(actor, exitButton)) {
                        menuCallback.onExit();
                    }
                }
            };
            button.addListener(buttonListener);
        }
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setPosition((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (100 - 60 * i));
        }
        texture = TextureManager.INSTANCE.loadTexture(1);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // fade in animation
        fadeElapsed += delta;
        float fadeInTime = .5f;
        float fadeDelay = 0f;
        float fade = Interpolation.fade.apply((fadeElapsed - fadeDelay) / fadeInTime);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameEngine.render();

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

        batch.begin();
        smallerFont.draw(batch, "v" + Constants.VERSION, 20, 30);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameEngine.update(width, height);
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

    public void setMenuCallback(MenuCallback menuCallback) {
        this.menuCallback = menuCallback;
    }
}