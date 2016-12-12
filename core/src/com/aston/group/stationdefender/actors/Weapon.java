package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.hud.Hud;
import com.aston.group.stationdefender.utils.hud.HudElement;
import com.aston.group.stationdefender.utils.hud.HudWeapon;
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
    private final double buildTime;
    private final int cost;
    boolean built;
    long lastTime;
    private int costToUpgrade;
    private double remainingBuildTime;
    private long startTime;
    private HudElement hudElement;

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
        this("Weapon", 50, Constants.DEFAULT_DAMAGE, 10.0, Constants.WEAPON_HEALTH, 12, 5.0, x, y, 60, 60, 1.5, 10, 10);
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
    public Weapon(String name, double speed, double damage, double rateOfFire, double health, double range, double chanceToHit, int x, int y, int width, int height,
                  double buildTime, int cost, int costToUpgrade) {
        super(name, speed, damage, rateOfFire, health, range, chanceToHit, x, y, width, height);
        this.buildTime = buildTime;
        this.cost = cost;
        this.costToUpgrade = costToUpgrade;
        remainingBuildTime = buildTime;
        built = false;
        facingLeft = false;
        batch = GameEngine.getBatch();
        setTexture(TextureManager.INSTANCE.loadTexture(8));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        renderParticleEffect(delta, batch);
        batch.draw(getTexture(), x, y, width, height);
        batch.end();
        act(delta);
        checkInput();
    }

    @Override
    public void act(float delta) {
        if (built && !checkZeroHealth()) {
            if (!isAdjacent) {
                if (unitCallback != null && System.currentTimeMillis() - lastTime >= (10000 / rateOfFire)) {
                    unitCallback.onFire(x + 40, y + 35, speed, getDamage());
                    lastTime = System.currentTimeMillis();
                }
            } else {
                adjacentActor.takeDamage(fire());
            }
        } else {
            decrementBuildTimer();
        }
    }

    @Override
    public void destroy() {
        if (hudElement != null) {
            Hud.removeHudElement(hudElement);
            hudElement = null;
        }
        super.destroy();
    }

    private void checkInput() {
        if (MouseInput.isColliding(x, y, width, height)) {
            if (hudElement == null) {
                hudElement = new HudWeapon();
                hudElement.setX(x);
                hudElement.setY(y);
                hudElement.setObject(this);
                Hud.addHudElement(hudElement);
            }
        } else if (Hud.isNotColliding()) {
            Hud.removeHudElement(hudElement);
            hudElement = null;
        }
    }

    /**
     * Decrements the build timer by 0.5
     * if the time since the last call to the method is greater than or equal to 500 milliseconds.
     * If afterwards the build timer is less than or equal to 0 then built is set to true.
     */
    public void decrementBuildTimer() {
        if (System.currentTimeMillis() - startTime >= 500) {
            if (remainingBuildTime > 0) {
                remainingBuildTime -= 0.5;
            }
            if (remainingBuildTime <= 0) {
                built = true;
            }
            startTime = System.currentTimeMillis();
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
     * Upgrades the weapon's damage by 10%, and increases cost to upgrade 25%.
     */
    public void upgradeWeapon() {
        //Sets new cost to upgrade;
        Double newUpgradeCost = Math.ceil((costToUpgrade * 1.25));
        costToUpgrade = newUpgradeCost.intValue();

        //Increase Weapon Damage by 10%
        damage = Math.ceil((damage * 1.1));
    }
}