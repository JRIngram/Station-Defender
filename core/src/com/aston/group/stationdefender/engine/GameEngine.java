package com.aston.group.stationdefender.engine;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This enum is the controller for the camera
 *
 * @author Mohammad Foysal
 */
public enum GameEngine {
    INSTANCE;
    private static final SpriteBatch batch;
    private static final ShapeRenderer shapeRenderer;
    private static final OrthographicCamera camera;
    private static final Viewport viewport;
    private static Vector3 mousePosition;

    static {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        mousePosition = new Vector3();
    }

    /**
     * Returns the SpriteBatch for the Game
     *
     * @return The SpriteBatch for the Game
     */
    public static SpriteBatch getBatch() {
        return batch;
    }

    /**
     * Returns the ShapeRenderer for the Game
     *
     * @return The ShapeRenderer for the Game
     */
    public static ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    /**
     * Render the camera and set the properties for the ShapeRenderer and SpriteBatch
     */
    public void render() {
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePosition = camera.unproject(mousePosition);
        MouseInput.getPosition().set(mousePosition.x, mousePosition.y);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Update the camera viewport
     *
     * @param width  The current width of the viewport
     * @param height The current height of the viewport
     */
    public void update(int width, int height) {
        viewport.update(width, height, false);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }
}