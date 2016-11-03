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
    protected boolean alive;
    protected int damage;
    protected int speed;
    private ShapeRenderer shapeRenderer;

    public Projectile() {
        x = 0;
        y = 0;
        alive = false;
        shapeRenderer = new ShapeRenderer();
    }

    public void init(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        width = 5;
        height = 4;
        this.speed = speed;
        alive = true;
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
        width = 5;
        height = 4;
        alive = false;
    }

    public void render(float delta){
        x += (speed * delta * 60);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        if (isOutOfScreen())
            alive = false;
    }


    public boolean isOutOfScreen(){
        if(x > Gdx.graphics.getWidth() + 1 || x < -10){
            return true;
        }else{
            return false;
        }
    }

    public boolean isColliding (int x, int y, int width, int height) {
        if (x + width > this.x && x < this.x + this.width &&
                y + height > this.y && y < this.y + this.height) {

            alive = false;
            return true;
        } else {
            return false;
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}