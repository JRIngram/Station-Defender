package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

public class Mine extends Weapon {

    public Mine() {
        this(0, 0);
    }

    public Mine(int x, int y) {
        super("Mine", 0, 100, 1, Constants.UNIT_HEALTH, 1, 0.9, x, y, 60, 60, 8.0, 80, 25);
        setTexture(TextureManager.INSTANCE.loadTexture(11));
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
                destroy();
            }
        } else {
            decrementBuildTimer();
        }
    }
}