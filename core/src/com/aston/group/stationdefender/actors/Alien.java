package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Superclass for different Alien types.
 *
 * @author IngramJ
 * @version 01/11/2016
 */
public class Alien extends Unit {
    private final SpriteBatch batch;

    /**
     * Construct a new Alien with given X and Y co-ordinates
     *
     * @param x The X co-ordinate to give the Alien
     * @param y The Y co-ordinate to give the Alien
     */
    public Alien(int x, int y) {
        this("Alien", -120, Constants.DEFAULT_DAMAGE, 5, Constants.UNIT_HEALTH, 5.0, 12, x, y, 100, 38);
    }

    /**
     * Construct a new Alien with given name, speed, damage, rateOfFile, health, range, x co-ordinate, y co-ordinate,
     * width and height
     *
     * @param name        The name of the Alien
     * @param speed       The speed of the Alien
     * @param damage      The damage the Alien inflicts
     * @param rateOfFire  The rate of fire of the Alien
     * @param health      The health of the Alien
     * @param range       The range of the Alien
     * @param chanceToHit The chance of the Weapon to score a hit
     * @param x           The X co-ordinate of the Alien
     * @param y           The Y co-ordinate of the Alien
     * @param width       The width of the Alien
     * @param height      The height of the Alien
     */
    public Alien(String name, double speed, double damage, double rateOfFire, double health, double range, double chanceToHit, int x, int y, int width, int height) {
        super(name, speed, damage, rateOfFire, health, range, chanceToHit, x, y, width, height);
        batch = GameEngine.getBatch();
        setTexture(TextureManager.INSTANCE.loadTexture(7));
        facingLeft = true;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        renderParticleEffect(delta, batch);
        if (!isAdjacent())
            batch.setColor(1f, 1f, 1f, 1f);
        else
            batch.setColor(.5f, .5f, .5f, 1f);
        batch.draw(getTexture(), x, y, width, height);
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
    void move(float delta) {
        if (!isAdjacent()) {
            x += (speed * delta);
        } else {
            if (getAdjacentActor() != null && !((Unit) getAdjacentActor()).isFacingLeft()) {
                getAdjacentActor().takeDamage(getDamage());
            }
        }
    }
}