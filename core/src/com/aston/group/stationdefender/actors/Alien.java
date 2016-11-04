package com.aston.group.stationdefender.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Superclass for different Alien types.
 *
 * @author IngramJ
 * @version 01/11/2016
 */
public class Alien extends Unit {
    private ShapeRenderer shapeRenderer;

    public Alien() {
        this(0, 0);
    }

    public Alien(int x, int y) {
        super(x, y);
        shapeRenderer = new ShapeRenderer();
        facingLeft = true;
        if (facingLeft) {
            speed = -100;
        } else {
            speed = 100;
        }
    }

    public Alien(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height) {
        super(name, speed, damage, rateOfFire, health, range, x, y, width, height);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (!isAdjacent())
            shapeRenderer.setColor(Color.BLUE);
        else
            shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        if (!isAdjacent()) {
            x += (speed * delta);
        } else {
            if (getAdjacentActor() != null && !((Unit) getAdjacentActor()).isFacingLeft()) {
                getAdjacentActor().takeDamage(200);
            }
        }
    }

    /**
     * The main method which determines how the weapon acts.
     */
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

    /**
     * Plays an explosion sound and animation.
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
     * Moves the Alien from the left side of the lane to the right
     *
     * @param speed Distance the Alien moves.
     */
    public void move(double speed) {
        //TODO IMPLEMENT
    }
}