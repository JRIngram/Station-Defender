package com.aston.group.stationdefender.engine;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public enum GameEngine {
    INSTANCE;
    private static final SpriteBatch batch;
    private static final ShapeRenderer shapeRenderer;
    private static final OrthographicCamera camera;
    private static final Viewport viewport;
    private static Vector3 mousePosition;

    static {

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        //Setup viewport
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        mousePosition = new Vector3();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void render() {
//        if (Gdx.input.isTouched()) {
//            mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.translate(-(float) Gdx.input.getDeltaX(), (float) Gdx.input.getDeltaY(), 0);
//            mousePosition = camera.unproject(mousePosition);
//            MouseInput.getPosition().set(mousePosition.x, mousePosition.y);
//        }

        mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePosition = camera.unproject(mousePosition);
        MouseInput.getPosition().set(mousePosition.x, mousePosition.y);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
    }

    public void update(int width, int height) {
        viewport.update(width, height);
    }
}