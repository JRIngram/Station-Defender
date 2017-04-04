package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.TwoTextCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.Objects;

/**
 * TwoTextScreen is a class that creates a screen holding a title text,
 * a body of text, a back button and an optional continue button.
 *
 * @author Jonathon Fitch
 */
public class TwoTextScreen implements Screen {
    private final SpriteBatch batch;
    private final Stage stage;
    private final BitmapFont titleFont;
    private final BitmapFont bodyFont;
    private final TextButton backButton;
    private final boolean continueBool;
    private final GameEngine gameEngine;
    private final Label titleLabel;
    private final Label bodyLabel;
    private TextButton continueButton;
    private TwoTextCallback twoTextCallback;
    private float fadeElapsed = 0;
    private String title;
    private String body;

    /**
     * Constructor sets the camera, viewpoint and
     * initializes the font and button(s).
     *
     * @param continueBool true if the continue button should be displayed.
     *                     False if the continue button should not be displayed.
     */
    public TwoTextScreen(boolean continueBool) {
        this.continueBool = continueBool;
        gameEngine = GameEngine.INSTANCE;
        batch = GameEngine.getBatch();
        bodyFont = FontManager.getFont(30);
        titleFont = FontManager.getFont(50);
        BitmapFont buttonFont = FontManager.getFont(18);
        stage = new Stage();

        //Background
        Texture texture = TextureManager.INSTANCE.loadTexture(2);

        Table table = new Table();
        table.setFillParent(true);

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image image = new Image();
        image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));

        background.addActor(image);
        titleLabel = new Label(title, new Label.LabelStyle(titleFont, Color.WHITE));
        titleLabel.setAlignment(Align.center);
        bodyLabel = new Label(body, new Label.LabelStyle(bodyFont, Color.WHITE));

        bodyLabel.setColor(1, 1, 1, 0);
        titleLabel.setColor(1, 1, 1, 0);
        table.add(titleLabel).expandX().padTop(16).row();
        table.add(bodyLabel).expandY();

        stage.addActor(background);
        stage.addActor(table);

        //Buttons
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = buttonFont;
        backButton = new TextButton(Constants.BACK, textButtonStyle);
        backButton.setColor(0, 0, 0, 0);
        backButton.setWidth(400);
        backButton.setHeight(50);
        if (continueBool) {
            textButtonStyle.font = bodyFont;
            continueButton = new TextButton(Constants.CONTINUE, textButtonStyle);
            continueButton.setColor(0, 0, 0, 0);
            continueButton.setWidth(400);
            continueButton.setHeight(50);
            stage.addActor(continueButton);
            continueButton.setPosition((Gdx.graphics.getWidth() / 2) - 210, (Gdx.graphics.getHeight() / 2) - 175);
        }
        stage.addActor(backButton);
        ChangeListener buttonListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Objects.equals(actor, backButton)) {
                    twoTextCallback.onBack();
                } else if (Objects.equals(actor, continueButton)) {
                    twoTextCallback.onContinue();
                }
            }
        };
        backButton.addListener(buttonListener);
        backButton.setPosition(-150, (Gdx.graphics.getHeight()) - 60);
        if (continueBool)
            continueButton.addListener(buttonListener);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // fade in animation
        fadeElapsed += delta;
        float fadeInTime = 2f;
        float fadeDelay = 1f;
        float fade = Interpolation.fade.apply(fadeElapsed / fadeInTime);
        float fade2 = Interpolation.fade.apply((fadeElapsed - fadeDelay) / fadeInTime);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameEngine.render();

        // Draw things on screen
        batch.begin();
        titleLabel.setColor(1, 1, 1, fade);
        backButton.setColor(1, 1, 1, fade);
        if (continueBool)
            continueButton.setColor(1, 1, 1, fade);
        restartBatch();
        stage.draw();
        restartBatch();

        // delay animation by a certain amount for each menu item
        bodyLabel.setColor(1, 1, 1, fade2);
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
        stage.dispose();
        bodyFont.dispose();
        titleFont.dispose();
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
        this.titleLabel.setText(title);
    }

    /**
     * Sets the text of the body message
     *
     * @param body The String to set as the body message
     */
    public void setBody(String body) {
        this.body = body;
        this.bodyLabel.setText(body);
    }

    /**
     * Restart the batch to allow objects to be drawn over the stage.
     */
    private void restartBatch() {
        batch.end();
        batch.begin();
    }

}