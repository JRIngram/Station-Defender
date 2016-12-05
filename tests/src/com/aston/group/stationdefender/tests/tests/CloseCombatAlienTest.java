package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.CloseCombatAlien;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CloseCombatAlienTest {
    private CloseCombatAlien alien;

    @Before
    public void setUp() {
        alien = new CloseCombatAlien(0, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Close Combat Alien", alien.getName());
        assertEquals(2.0, alien.getSpeed(), 0);
        assertEquals(6.0, alien.getDamage(), 0);
        assertEquals(100.0, alien.getHealth(), 0);
        assertEquals(7.0, alien.getChanceToHit(), 0);
    }

    @Test
    public void testDamageAndCheckHealth() {
        alien = new CloseCombatAlien(100, 100, 100, 100);
        assertEquals(100, (int) alien.getHealth());
        for (int i = 100; i > 0; i--) {
            assertEquals(i, alien.getHealth(), 0);
            assertEquals(false, alien.checkZeroHealth());
            alien.takeDamage(1);
        }
        assertEquals(0, alien.getHealth(), 0);
        assertEquals(true, alien.checkZeroHealth());
    }
}