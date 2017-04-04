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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Objects;

/**
 * MenuScreen is the screen shown when pressing the menu button
 * from within the game.  It shows options such as play, exit, save etc.
 *
 * @author Jonathon Fitch
 */
public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final TextButton playButton, exitButton;
    private final Stage stage;
    private final Texture texture;
    private final GameEngine gameEngine;
    private MenuCallback menuCallback;

    /**
     * Construct a new MenuScreen
     */
    public MenuScreen() {
        gameEngine = GameEngine.INSTANCE;
        batch = GameEngine.getBatch();
        font = FontManager.getFont(50);

        ChangeListener buttonListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                if (Objects.equals(actor, playButton)) {
                    menuCallback.onPlay(false);
                } else if (Objects.equals(actor, exitButton)) {
                    menuCallback.onExit();
                }
            }
        };

        stage = new Stage();

        Texture hoverTexture = TextureManager.INSTANCE.loadTexture(23);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(hoverTexture));
        playButton = new TextButton(Constants.MENU_ITEMS[2], textButtonStyle);
        exitButton = new TextButton(Constants.MENU_ITEMS[3], textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        playButton.addListener(buttonListener);
        exitButton.addListener(buttonListener);
        table.add(playButton).row();
        table.add(exitButton).row();

        texture = TextureManager.INSTANCE.loadTexture(1);

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image image = new Image();
        image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));

        background.addActor(image);
        stage.addActor(background);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameEngine.render();
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(delta);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameEngine.update(width, height);
        stage.getViewport().update(width, height, true);
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