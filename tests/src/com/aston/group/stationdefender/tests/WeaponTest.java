package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Weapon;
import com.badlogic.gdx.Gdx;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeaponTest extends GdxTestRunner {
    private Weapon testWep;
    private Alien adjacentAlien;

    @Before
    public void setUp() {
        Gdx.app.postRunnable(() -> {
            testWep = new Weapon("Weapon", 0, 2, 2, 10, 1, 10, 10, 200, 200, 0.0d, 100, 100);
            adjacentAlien = new Alien("Alien", 0, 5, 2, 5, 1, 200, 200, 100, 100);
        });
    }

    @Test
    public void testConstructor() {
        Gdx.app.postRunnable(() -> {
            assertEquals("Weapon", testWep.getName());
            assertEquals(0, testWep.getSpeed(), 0);
            assertEquals(1.5, testWep.getDamage(), 0);
            assertEquals(3, testWep.getRateOfFire(), 0);
            assertEquals(5, testWep.getHealth(), 0);
            assertEquals(1, testWep.getRange(), 0);
            assertEquals(10.0, testWep.getBuildTime(), 0);
            assertEquals(100, testWep.getCost(), 0);
            assertEquals(25, testWep.getCostToUpgrade(), 0);
        });
    }

    @Test
    public void testBuiltAndRemainingBuildTime() {
        Gdx.app.postRunnable(() -> {
            assertEquals(testWep.getBuilt(), false);
            assertEquals(true, (testWep.getBuildTime() == testWep.getRemainingBuildTime()));
            assertEquals(10.0, testWep.getRemainingBuildTime(), 0);

            for (int i = 10; i > 1; i--) {
                testWep.decrementBuildTimer();
                assertEquals(false, testWep.getBuilt());
            }

            assertEquals(1.0, testWep.getRemainingBuildTime(), 0);
            testWep.decrementBuildTimer();
            assertEquals(true, testWep.getBuilt());
            assertEquals(0.0, testWep.getRemainingBuildTime(), 0);
            testWep.decrementBuildTimer();
            assertEquals(true, testWep.getBuilt());
            assertEquals(0.0, testWep.getRemainingBuildTime(), 0);
        });
    }

    @Test
    public void testDamageAndCheckHealth() {
        Gdx.app.postRunnable(() -> {
            for (int i = 5; i > 0; i--) {
                assertEquals(i, testWep.getHealth(), 0);
                assertEquals(false, testWep.checkZeroHealth());
                testWep.takeDamage(1);
            }
            assertEquals(0, testWep.getHealth(), 0);
            assertEquals(true, testWep.checkZeroHealth());
        });
    }

    @Test
    public void testFiring() {
        Gdx.app.postRunnable(() -> {
            double damageDealt = testWep.fire();
            assertTrue((damageDealt >= 0) && (damageDealt <= 4.5));
        });
    }

    @Test
    public void testAdjacent() {
        Gdx.app.postRunnable(() -> {
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
        });
    }

    @Test
    public void testDamageDealing() {
        Gdx.app.postRunnable(() -> {
            assertEquals(5, adjacentAlien.getHealth(), 0);
            testWep.setAdjacentActor(adjacentAlien);
            assertEquals(adjacentAlien, testWep.getAdjacentActor());
            for (int i = 10; i > 1; i--) {
                testWep.decrementBuildTimer();
                assertEquals(false, testWep.getBuilt());
            }
            testWep.decrementBuildTimer();
            assertEquals(true, testWep.getBuilt());
            testWep.act();
            assertTrue((adjacentAlien.getHealth() <= 5));
        });
    }
}