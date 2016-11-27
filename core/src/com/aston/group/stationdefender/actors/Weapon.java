package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Weapon is a class that represents a weapon object
 * that Humans (Unit) can arm themselves with and use to destroy Aliens
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class Weapon extends Unit {
    private final SpriteBatch batch;
    private final Texture texture;
    private final double buildTime;
    private final int cost;
    private final int costToUpgrade;
    private boolean built;
    private double remainingBuildTime;
    private ProjectileFactory projectileFactory;
    private long lastTime;

    /**
     * Construct a new Weapon with default X and Y co-ordinates of '0'
     */
    public Weapon() {
        this(0, 0);
    }

    /**
     * Construct a new Weapon with given X and Y co-ordinates
     *
     * @param x The X co-ordinate to give the Weapon
     * @param y The Y co-ordinate to give the Weapon
     */
    public Weapon(int x, int y) {
        this("Weapon", 50, 50, 2, Constants.WEAPON_HEALTH, 12, x, y, 60, 60, 5, 10, 10);
    }

    /**
     * Construct a new Weapon with given name, speed, damage, rateOfFile, health, range, x co-ordinate, y co-ordinate,
     * width, height, buildTime, cost and costToUpgrade parameters
     *
     * @param name          The name of the Weapon
     * @param speed         The speed of the Weapon
     * @param damage        The damage the Weapon inflicts
     * @param rateOfFire    The rate of fire of the Weapon
     * @param health        The health of the Weapon
     * @param range         The range of the Weapon
     * @param x             The X co-ordinate of the Weapon
     * @param y             The Y co-ordinate of the Weapon
     * @param width         The width of the Weapon
     * @param height        The height of the Weapon
     * @param buildTime     The build time of the Weapon
     * @param cost          The cost of the Weapon
     * @param costToUpgrade THe cost to upgrade to the Weapon
     */
    public Weapon(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height,
                  double buildTime, int cost, int costToUpgrade) {
        super(name, speed, damage, rateOfFire, health, range, x, y, width, height);
        this.buildTime = buildTime;
        this.cost = cost;
        this.costToUpgrade = costToUpgrade;
        remainingBuildTime = buildTime;
        built = false;
        facingLeft = false;
        batch = new SpriteBatch();
        texture = TextureManager.getInstance().loadTexture(8);
    }

    @Override
    public void render(float delta) {
        if (!isAdjacent()) {
            if (unitCallback != null && System.currentTimeMillis() - lastTime > 1000 + Math.random() * 4000) {
                unitCallback.onFire(x + 40, y + 35, speed, getDamage());
                lastTime = System.currentTimeMillis();
            }
        }
        batch.begin();
        renderParticleEffect(delta, batch);
        batch.draw(texture, x, y, width, height);
        batch.end();
    }

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

    /**
     * Returns the ProjectileFactory used for the Weapon
     *
     * @return The ProjectileFactory used for the Weapon
     */
    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    /**
     * Sets the ProjectileFactory used for the Weapon
     *
     * @param projectileFactory The ProjectileFactory to be used for the Weapon
     */
    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
}