package com.aston.group.stationdefender.tests.utils;

import com.aston.group.stationdefender.actors.Weapon;

public class ThreadSleep {
    public static void threadSleep(Weapon weapon) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        weapon.decrementBuildTimer();
    }
}