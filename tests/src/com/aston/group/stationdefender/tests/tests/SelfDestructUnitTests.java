package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.KamikazeAlien;
import com.aston.group.stationdefender.actors.Mine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelfDestructUnitTests {

    private Mine testMine;
    private KamikazeAlien testKamikaze;

    @Before
    public void setUp() {
        testMine = new Mine(2, 3);
        testKamikaze = new KamikazeAlien(5, 4);
    }

    @Test
    public void testKamikazeConsturctor() {
        assertEquals("Kamikaze Alien", testKamikaze.getName());
        assertEquals(4, testKamikaze.getSpeed(), 0);
        assertEquals(100, testKamikaze.getDamage(), 0);
        assertEquals(1, testKamikaze.getRateOfFire(), 0);
        assertEquals(100, testKamikaze.getHealth(), 0);
        assertEquals(0.9, testKamikaze.getChanceToHit(), 0);
        assertEquals(1.0, testKamikaze.getRange(), 0);
    }

    @Test
    public void testMineConstructor() {
        assertEquals("Mine", testMine.getName());
        assertEquals(0, testMine.getSpeed(), 0);
        assertEquals(100, testMine.getDamage(), 0);
        assertEquals(1, testMine.getRateOfFire(), 0);
        assertEquals(100, testMine.getHealth(), 0);
        assertEquals(1, testMine.getRange(), 0);
        assertEquals(8.0, testMine.getBuildTime(), 0);
        assertEquals(80, testMine.getCost(), 0);
        assertEquals(25, testMine.getCostToUpgrade(), 0);

    }

    @Test
    public void testAlienSelfDestruct() {
        assertEquals(true, testKamikaze.getExists());
        testKamikaze.setAdjacentActor(testMine);
        assertEquals(testMine, testKamikaze.getAdjacentActor());
        testKamikaze.act(0);
        assertEquals(false, testKamikaze.getExists());
    }

    @Test
    public void testMineSelfDestruct() {
        assertEquals(true, testMine.getExists());
        testMine.setAdjacentActor(testKamikaze);
        assertEquals(testKamikaze, testMine.getAdjacentActor());
        while (testMine.getRemainingBuildTime() > 0) {
            testMine.decrementBuildTimer();
        }
        testMine.act(0);
        assertEquals(false, testMine.getExists());
    }

}
