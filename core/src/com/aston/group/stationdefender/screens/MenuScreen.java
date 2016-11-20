package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.MenuCallback;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private final TextButton playButton, exitButton;
    private final TextButton[] buttons;
    private final Stage stage;
    private final Texture texture;
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
        fontGenerator.dispose();//Buttons
        stage = new Stage();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        playButton = new TextButton(Constants.MENU_ITEMS[2], textButtonStyle);
        exitButton = new TextButton(Constants.MENU_ITEMS[3], textButtonStyle);
        buttons = new TextButton[]{playButton, exitButton};
        for (TextButton button : buttons) {
            button.setColor(0, 0, 0, 0);
            button.setWidth(400);
            button.setHeight(50);
            stage.addActor(button);
            ChangeListener buttonListener = new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                    if (actor.equals(playButton)) {
                        menuCallback.onPlay(false);
                    } else if (actor.equals(exitButton)) {
                        menuCallback.onExit();
                    }
                }
            };
            button.addListener(buttonListener);
        }
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setPosition((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + (30 - 60 * i));
        }
        texture = new Texture(Gdx.files.internal("textures/intro-back.jpg"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        batch.begin();
        stage.draw();
        // delay animation by a certain amount for each menu item
        font.setColor(1, 1, 1, 1);
        for (TextButton button : buttons) {
            button.setColor(1, 1, 1, 1);
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
        fontGenerator.dispose();
        font.dispose();
        batch.dispose();
    }

    /**
     * Sets the MenuCallBack to be used for the MenuScreen
     *
     * @param menuCallback The MenuCallBack to be used for the MenuScreen
     */
    public void setMenuCallback(MenuCallback menuCallback) {
        this.menuCallback = menuCallback;
    }
}