package com.aston.group.stationdefender.actors;

public class RapidFireWeapon extends Weapon {
    private boolean overloaded;

    public RapidFireWeapon(int x, int y) {
        super(x, y);
        overloaded = false;
    }

    public RapidFireWeapon(int x, int y, int width, int height, double buildTime, int cost, int costToUpgrade) {
        super("Rapid Fire Weapon", 0, 0.5, 15, 10, 2, 0.5, x, y, width, height, buildTime, cost, costToUpgrade);
        overloaded = false;
    }

    /**
     * Similar to usual act method, but if the Weapon hits on all shots the Weapon "overloads" and cannot fire for the next call of act.
     * No damage is dealt on this round,
     */
    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (!overloaded) {
                if (isAdjacent) {
                    try {
                        double damageDealt = fire();
                        if ((damageDealt / getDamage()) == getRateOfFire()) {
                            overloaded = true;
                        } else {
                            adjacentActor.takeDamage(damageDealt);
                        }

                    } catch (Exception e) {
                        System.out.println("Null values are not allowed");
                    }
                }
            } else
                overloaded = false;
        } else {
            destroy();
        }
    }

    /**
     * Returns if the Weapon is overloaded.
     *
     * @return Overloaded state of the Weapon.
     */
    public boolean getOverloaded() {
        return overloaded;
    }

    /**
     * Sets if the Weapon is overloaded or not.
     *
     * @param overloaded state of the Weapon.
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
    }
}
