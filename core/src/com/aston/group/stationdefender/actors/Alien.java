package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.SoundManager;
import com.aston.group.stationdefender.utils.indicators.IndicatorManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    private final IndicatorManager damageIndicator;

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
        super(x, y);
        batch = new SpriteBatch();

        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();

        texture = new Texture(Gdx.files.internal("textures/enemy.png"));
        facingLeft = true;
        speed = -100;

        width = 100;
        height = 38;

        damageIndicator = new IndicatorManager();
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

        texture = new Texture(Gdx.files.internal("textures/enemy.png"));

        damageIndicator = new IndicatorManager();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        if (!isAdjacent())
            batch.setColor(1f, 1f, 1f, 1f);
        else
            batch.setColor(.5f, .5f, .5f, 1f);
        batch.draw(texture, x, y, width, height);
        batch.end();

        if (!isAdjacent()) {
            x += (speed * delta);
        } else {
            if (getAdjacentActor() != null && !((Unit) getAdjacentActor()).isFacingLeft()) {
                getAdjacentActor().takeDamage(200);
            }
        }

        damageIndicator.render(delta, x, y);
    }

    @Override
    public void takeDamage(double damage) {
        super.takeDamage(damage);
        damageIndicator.addIndicator((int) damage, Color.RED);
    }

    @Override
    public void act() {
        if (!checkZeroHealth()) {
            if (isAdjacent) {
                try {
                    adjacentActor.takeDamage(fire());
                } catch (Exception e) {
                    System.out.println("Null values are not allowed");
                }
            } else {
                move(speed);
            }
        } else {
            destroy();
        }
    }

    @Override
    public void destroy() {
        //TODO: Play explosion animation
        SoundManager.getInstance().playSound(1);
        exists = false;
    }

    /**
     * Moves the Alien from the left side of the lane to the right
     *
     * @param speed Distance the Alien moves.
     */
    public void move(double speed) {
        //TODO IMPLEMENT
    }
}