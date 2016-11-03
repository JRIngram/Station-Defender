package com.aston.group.stationdefender.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Test class for Weapon
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class TestWeapon extends Weapon {

    private ShapeRenderer shapeRenderer;

    public TestWeapon() {
        this(0, 0);
    }

    public TestWeapon(int x, int y) {
        super(x, y);
        shapeRenderer = new ShapeRenderer();
        facingLeft = false;
        if(facingLeft){
            speed = -100;
        }else{
            speed = 100;
        }
        health = 100;
    }

    public TestWeapon(double damage, double rateOfFire, double health, int x, int y, int height, int width,
                      double range, double buildTime, int cost, int costToUpgrade
    ) {
        super("Test Weapon", 0, damage, rateOfFire, health, range, x, y, width, height, buildTime, cost, costToUpgrade);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(!isAdjacent())
        shapeRenderer.setColor(Color.GREEN);
        else
            shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    /**
     * The main method which determines how the weapon acts.
     */
    @Override
    public void act() {
        if (!checkZeroHealth() && built) {
            if (isAdjacent) {
                try {
                    adjacentActor.takeDamage(fire());
                } catch (Exception e) {
                    System.out.println("Null values are not allowed");
                }
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
        // TODO IMPLEMENT
        //Play explosion animation.
        //Play explosion sound.
        System.out.println("UNIT DESTROYED - FILLER BEFORE IMPLEMENTATION.");
        exists = false;
    }
}