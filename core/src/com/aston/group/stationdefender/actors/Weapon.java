package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Weapon is a class that represents a weapon object
 * that Humans (Unit) can arm themselves with and use to destroy Aliens
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class Weapon extends Unit implements Actor {
    boolean built;
    private double buildTime;
    private double remainingBuildTime;
    private int cost;
    private int costToUpgrade;
    private ProjectileFactory projectileFactory;
    private ShapeRenderer shapeRenderer;
    private long lastTime;

    public Weapon() {
        x = 0;
        y = 0;
        setName("Weapon");
    }

    public Weapon(int x, int y) {
        super(x, y);
        shapeRenderer = new ShapeRenderer();
        facingLeft = false;
        if (facingLeft) {
            speed = -100;
        } else {
            speed = 100;
        }
        health = Constants.WEAPON_HEALTH;
    }

    public Weapon(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height,
                  double buildTime, int cost, int costToUpgrade) {
        super(name, speed, damage, rateOfFire, health, range, x, y, width, height);
        this.buildTime = buildTime;
        this.cost = cost;
        this.costToUpgrade = costToUpgrade;
        remainingBuildTime = buildTime;
        built = false;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (!isAdjacent()) {
            shapeRenderer.setColor(Color.GREEN);

            if (unitCallback != null && System.currentTimeMillis() - lastTime > 2000 + Math.random() * 4000) {
                unitCallback.onFire(x, y, 20);
                lastTime = System.currentTimeMillis();
            }
        } else
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
        //TODO: Play explosion animation
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion.mp3"));
        sound.play();
        sound.dispose();
        System.out.println("UNIT DESTROYED - FILLER BEFORE IMPLEMENTATION.");
        exists = false;
    }

    /**
     * Decrements the build timer by 1. If afterwards the build timer equals 0 then built is set to true.
     */
    public void decrementBuildTimer() {
        if (remainingBuildTime > 0) {
            remainingBuildTime--;
        }
        if (remainingBuildTime == 0) {
            built = true;
        }
    }

    /**
     * Returns the build time for the Weapon
     *
     * @return buildTime
     */
    public double getBuildTime() {
        return buildTime;
    }

    /**
     * Returns the remaining build time of the Weapon.
     *
     * @return remainingBuildTime
     */
    public double getRemainingBuildTime() {
        return remainingBuildTime;
    }

    /**
     * Returns the cost of building the weapon.
     *
     * @return cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the cost to upgrade the weapon.
     *
     * @return costToUpgrade
     */
    public int getCostToUpgrade() {
        return costToUpgrade;
    }

    /**
     * Returns a boolean stating if the Unit has been built or not.
     *
     * @return built
     */
    public boolean getBuilt() {
        return built;
    }

    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
}