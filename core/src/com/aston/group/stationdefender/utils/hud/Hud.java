package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class Hud {

    private static Hud instance;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ArrayList<HudElement> hudElements = new ArrayList<>();

    public Hud() {
        instance = this;

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        stage = new Stage(viewport, batch);

    }

    public void render(float delta) {
        for (int i = 0; i < hudElements.size(); i++) {
            hudElements.get(i).render(delta);
        }
    }

    public void dispose() {

    }

    public boolean isColliding() {
        for (int i = 0; i < hudElements.size(); i++) {
            if (hudElements.get(i).isColliding()) {
                return true;
            }
        }

        return false;
    }

    public void addHudElement(HudElement hudElement){
        hudElements.add(hudElement);
    }

    public void removeHudElement(HudElement hudElement){
        hudElements.remove(hudElement);
    }

    public ArrayList<HudElement> getHudElements() {
        return hudElements;
    }

    public void setHudElements(ArrayList<HudElement> hudElements) {
        this.hudElements = hudElements;
    }

    public static Hud getInstance() {
        return instance;
    }
}
