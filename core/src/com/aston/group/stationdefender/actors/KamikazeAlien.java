package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;

public class KamikazeAlien extends Alien {

    public KamikazeAlien() {
        this(0, 0);
    }

    public KamikazeAlien(int x, int y) {
        super("Kamikaze Alien", 4, 100, 1, Constants.UNIT_HEALTH, 1.0, 0.9, x, y, 100, 38);
    }

    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (isAdjacent) {
                adjacentActor.takeDamage(fire());
                super.destroy();
            } else {
                move(delta);
            }
        } else {
            destroy();
        }
    }
}