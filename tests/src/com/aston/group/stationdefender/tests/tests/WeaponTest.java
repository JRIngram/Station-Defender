package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Weapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeaponTest {
    private Weapon testWep;
    private Alien adjacentAlien;

    @Before
    public void setUp() {
        testWep = new Weapon("Weapon", 0, 2, 2, 10, 1, 10, 10, 200, 200, 2.0, 100, 25);
        adjacentAlien = new Alien("Alien", 0, 5, 2, 5, 1, 200, 200, 100, 100);
    }

    @Test
    public void testConstructor() {
        Weapon weapon = new Weapon(0, 0);
        assertEquals(0, weapon.getX());
        assertEquals(0, weapon.getY());
        assertEquals("Weapon", testWep.getName());
        assertEquals(0, testWep.getSpeed(), 0);
        assertEquals(2, testWep.getDamage(), 0);
        assertEquals(2, testWep.getRateOfFire(), 0);
        assertEquals(10, testWep.getHealth(), 0);
        assertEquals(1, testWep.getRange(), 0);
        assertEquals(2.0, testWep.getBuildTime(), 0);
        assertEquals(100, testWep.getCost(), 0);
        assertEquals(25, testWep.getCostToUpgrade(), 0);
    }

    @Test
    public void testBuiltAndRemainingBuildTime() {
        assertEquals(testWep.getBuilt(), false);
        assertEquals(true, (testWep.getBuildTime() == testWep.getRemainingBuildTime()));
        assertEquals(2.0, testWep.getRemainingBuildTime(), 0);

        for (int i = 2; i > 1; i--) {
            threadSleep();
            threadSleep();
            assertEquals(false, testWep.getBuilt());
        }

        assertEquals(1.0, testWep.getRemainingBuildTime(), 0);
        threadSleep();
        threadSleep();
        assertEquals(true, testWep.getBuilt());
        assertEquals(0.0, testWep.getRemainingBuildTime(), 0);
    }

    @Test
    public void testDamageAndCheckHealth() {
        for (int i = 10; i > 0; i--) {
            assertEquals(i, testWep.getHealth(), 0);
            assertEquals(false, testWep.checkZeroHealth());
            testWep.takeDamage(1);
        }
        assertEquals(0, testWep.getHealth(), 0);
        assertEquals(true, testWep.checkZeroHealth());
    }

    @Test
    public void testFiring() {
        double damageDealt = testWep.fire();
        assertTrue((damageDealt >= 0) && (damageDealt <= 4.5));
    }

    @Test
    public void testAdjacent() {
        testWep = new Weapon("Weapon", 0, 2, 2, 10, 1, 10, 10, 200, 200, 0.0d, 100, 100);
        Weapon adjacentWeapon = new Weapon("AdjWeapon", 0, 2, 2, 10, 1, 10, 10, 200, 200, 0.0d, 100, 100);
        assertEquals(false, testWep.isAdjacent());
        assertEquals(null, testWep.getAdjacentActor());
        testWep.setAdjacentActor(adjacentWeapon);
        assertEquals(true, testWep.isAdjacent());
        assertEquals(adjacentWeapon, testWep.getAdjacentActor());
        testWep.setAdjacentActor(adjacentAlien);
        assertEquals(true, testWep.isAdjacent());
        assertEquals(adjacentAlien, testWep.getAdjacentActor());
        testWep.setAdjacentActor(null);
        assertEquals(false, testWep.isAdjacent());
        assertEquals(null, testWep.getAdjacentActor());
    }

    @Test
    public void testDamageDealing() {
        assertEquals(5, adjacentAlien.getHealth(), 0);
        testWep.setAdjacentActor(adjacentAlien);
        assertEquals(adjacentAlien, testWep.getAdjacentActor());
        for (int i = 2; i > 1; i--) {
            threadSleep();
            threadSleep();
            assertEquals(false, testWep.getBuilt());
        }
        threadSleep();
        threadSleep();
        assertEquals(true, testWep.getBuilt());
        testWep.act(0.1f);
        assertTrue((adjacentAlien.getHealth() <= 5));
    }

    private void threadSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testWep.decrementBuildTimer();
    }
}