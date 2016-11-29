package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Superclass for different Alien types.
 *
 * @author IngramJ
 * @version 01/11/2016
 */
public class Alien extends Unit {
    private final Texture texture;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    /**
     * Construct a new Alien with default X and Y co-ordinates of '0'
     */
    public Alien() {
        this(0, 0);
    }

    /**
     * Construct a new Alien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate to give the Alien
     * @param y The Y co-ordinate to give the Alien
     */
    public Alien(int x, int y) {
        this("Alien", -100, 50, 5, Constants.UNIT_HEALTH, 12, x, y, 100, 38);
    }

    /**
     * Construct a new Alien with given name, speed, damage, rateOfFile, health, range, x co-ordinate, y co-ordinate,
     * width and height
     *
     * @param name       The name of the Alien
     * @param speed      The speed of the Alien
     * @param damage     The damage the Alien inflicts
     * @param rateOfFire The rate of fire of the Alien
     * @param health     The health of the Alien
     * @param range      The range of the Alien
     * @param x          The X co-ordinate of the Alien
     * @param y          The Y co-ordinate of the Alien
     * @param width      The width of the Alien
     * @param height     The height of the Alien
     */
    public Alien(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height) {
        super(name, speed, damage, rateOfFire, health, range, x, y, width, height);
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        texture = TextureManager.INSTANCE.loadTexture(7);
        facingLeft = true;
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        renderParticleEffect(delta, batch);
        if (!isAdjacent())
            batch.setColor(1f, 1f, 1f, 1f);
        else
            batch.setColor(.5f, .5f, .5f, 1f);
        batch.draw(texture, x, y, width, height);
        batch.end();
        act(delta);
        indicatorManager.render(delta, x, y);
    }

    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (isAdjacent) {
                adjacentActor.takeDamage(fire());
            } else {
                move(delta);
            }
        } else {
            destroy();
        }
    }

    /**
     * Moves the Alien from the left side of the lane to the right
     *
     * @param delta The time in seconds since the last move
     */
    private void move(float delta) {
        if (!isAdjacent()) {
            x += (speed * delta);
        } else {
            if (getAdjacentActor() != null && !((Unit) getAdjacentActor()).isFacingLeft()) {
                getAdjacentActor().takeDamage(200);
            }
        }
    }
}