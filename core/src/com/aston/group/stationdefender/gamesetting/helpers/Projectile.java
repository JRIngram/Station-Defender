package com.aston.group.stationdefender.gamesetting.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Pool;

/**
 * Projectile is a reusable game object
 *
 * @author Mohammed Foysal
 */
public class Projectile implements Pool.Poolable {
    protected int x, y, width, height;
    private boolean alive;
    private int damage;
    private int speed;
    private ShapeRenderer shapeRenderer;

    /**
     * Construct a new Projectile with a default
     * X and Y co-ordinates of '0'
     */
    public Projectile() {
        x = 0;
        y = 0;
        alive = false;
        shapeRenderer = new ShapeRenderer();
    }

    /**
     * Initiate the Projectile moving
     *
     * @param x     The initial X co-ordinate of the Projectile
     * @param y     The initial Y co-ordinate of the Projectile
     * @param speed The speed of the Projectile
     */
    public void init(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        width = 5;
        height = 4;
        this.speed = speed;
        alive = true;
    }

    /**
     * Reset the X, Y, width, height, speed and alive parameters
     * of the Projectile
     */
    @Override
    public void reset() {
        x = 0;
        y = 0;
        width = 5;
        height = 4;
        alive = false;
    }

    /**
     * Render the Projectile.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta){
        x += (speed * delta * 60);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        if (isOutOfScreen())
            alive = false;
    }

    /**
     * Returns whether the Projectile is out of the screen bounds or not
     *
     * @return true if the Projectile is out of the screen bounds,
     *          false if the Projectile if not out of the screen bounds
     */
    public boolean isOutOfScreen(){
        return x > Gdx.graphics.getWidth() + 1 || x < -10;
    }

    /**
     * Check if an objects X & Y co-ordinates or width & height
     * overlaps the Projectiles X & Y co-ordinates, or width & height
     *
     * @param x The X co-ordinate of the object to check
     * @param y The Y co-ordinate of the object to check
     * @param width The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    public boolean isColliding (int x, int y, int width, int height) {
        if (x + width > this.x && x < this.x + this.width && y + height > this.y && y < this.y + this.height) {
            alive = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Dispose of unused assets
     */
    public void dispose() {
        shapeRenderer.dispose();
    }

    /**
     * Returns the X co-ordinate of the Projectile
     *
     * @return The X co-ordinate of the Projectile
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the Projectile
     *
     * @param x The X co-ordinate of the Projectile
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate of the Projectile
     *
     * @return The Y co-ordinate of the Projectile
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the Projectile
     *
     * @param y The Y co-ordinate of the Projectile
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the Projectile
     *
     * @return The width of the Projectile
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the Projectile
     *
     * @param width The width of the Projectile
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the Projectile
     *
     * @return The height of the Projectile
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the Projectile
     *
     * @param height The height of the Projectile
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns whether the Projectile is alive or not
     *
     * @return true if the Projectile is alive, false if the Projectile is not alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set whether the Projectile is alive or not
     *
     * @param alive Whether the Projectile is alive or not
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Returns the damage of the Projectile
     *
     * @return The damage of the Projectile
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage of the Projectile
     *
     * @param damage The damage of the Projectile
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Returns the speed of the Projectile
     *
     * @return The speed of the Projectile
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the Projectile
     *
     * @param speed The speed of the Projectile
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}