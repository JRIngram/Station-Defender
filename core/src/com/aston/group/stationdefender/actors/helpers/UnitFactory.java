package com.aston.group.stationdefender.actors.helpers;

import com.aston.group.stationdefender.actors.*;

/**
 * This class is responsible for handling what Units are created
 *
 * @author Mohammad Foysal
 */
public class UnitFactory {

    /**
     * Returns a new Enemy Unit
     *
     * @param units The set of Units to choose from
     * @return A new Enemy Unit
     */
    private static Unit getEnemy(Units units) {
        switch (units) {
            case ALIEN:
                return new Alien(0, 0);
            case CLOSE_COMBAT_ALIEN:
                return new CloseCombatAlien(0, 0);
            case KAMIKAZE:
                return new KamikazeAlien(0, 0);
            case RAPID_FIRE_ALIEN:
                return new RapidFireAlien(0, 0);
            default:
                return new Alien(0, 0);
        }
    }

    /**
     * Returns a random Enemy Unit
     *
     * @return The new Enemy Unit
     */
    public static Unit getRandomEnemy() {
        int rand = (int) (Math.random() * (Units.values().length));
        return UnitFactory.getEnemy(Units.values()[rand]);
    }
}