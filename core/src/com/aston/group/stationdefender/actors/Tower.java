package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 *
 * @author Jonathon Fitch, Peter Holmes
 */
public class Tower implements Actor {
    private int height, width, x, y;
    private double health;
    private boolean exists;
    private ShapeRenderer shapeRenderer;
    

    /**
     * Constructs a new Tower
     */
    public Tower(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shapeRenderer = new ShapeRenderer();
        exists = true;
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
    	shapeRenderer.rect(x, y, width, height);
    	shapeRenderer.end();
    }
    
    /**
     * Determines how the Tower acts when colliding with another unit.
     */
    @Override
    public void act() {
    }

    public boolean isColliding (int x, int y, int width, int height) {
        if (x + width > this.x && x < this.x + this.width &&
                y + height > this.y && y < this.y + this.height) {

            takeDamage(Constants.DEFAULT_DAMAGE);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines what happens when the tower gets destroyed.
     */

    @Override
    public void destroy() {
        //TODO: Play explosion animation
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion.mp3"));
        sound.play();
        sound.dispose();
        exists = false;
    }

    /**
     * Returns the existence state of the Unit.
     *
     * @return true if the Unit exits, false if not
     */
    @Override
    public boolean getExists() {
        return exists;
    }

    /**
     * Sets the existence state of the Unit.
     *
     * @param exists The existence state of the Unit
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    /**
     * Returns the height of the Tower
     *
     * @return The height of the Tower
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Tower
     *
     * @return The width of the Tower
     */
    public int getWidth() {
        return width;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage) {
        if ((health - damage) <= 0) {
            destroy();
    	} else {
    		health -= damage;
    	}
    }
}