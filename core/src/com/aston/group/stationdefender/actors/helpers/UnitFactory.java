package com.aston.group.stationdefender.actors.helpers;

import com.aston.group.stationdefender.actors.*;

public class UnitFactory {

    public static Unit getEnemy(Units units) {
        switch (units) {
            case ALIEN:
                return new Alien();
            case CLOSE_COMBAT_ALIEN:
                return new CloseCombatAlien();
            case KAMIKAZE:
                return new KamikazeAlien();
            case RAPID_FIRE_ALIEN:
                return new RapidFireAlien();
            default:
                return new Alien();
        }
    }

    public static Unit getRandomEnemy() {
        int rand = (int) (Math.random() * (Units.values().length));
        return UnitFactory.getEnemy(Units.values()[rand]);
    }

}
