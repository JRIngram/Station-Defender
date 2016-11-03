package com.aston.group.stationdefender.actors;

/**
 * Weapon is a class that represents a weapon object
 * that Humans (Unit) can arm themselves with and use to destroy Aliens
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public abstract class Weapon extends Unit implements Actor {
    boolean built;
    private double buildTime;
    private double remainingBuildTime;
    private int cost;
    private int costToUpgrade;

    public Weapon(String name, double speed, double damage, double rateOfFire, double health, double range, int x, int y, int width, int height,
                  double buildTime, int cost, int costToUpgrade) {
        super(name, speed, damage, rateOfFire, health, range, x, y, width, height);
        this.buildTime = buildTime;
        this.cost = cost;
        this.costToUpgrade = costToUpgrade;
        remainingBuildTime = buildTime;
        built = false;
    }

    @Override
    public abstract void render(float delta);

    /**
     * Checks if adjacent space to the right is occupied by an alien, and if so, attack.
     */
    @Override
    public abstract void act();


    /**
     * Play destruction animation and remove.
     */
    @Override
    public abstract void destroy();

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
}