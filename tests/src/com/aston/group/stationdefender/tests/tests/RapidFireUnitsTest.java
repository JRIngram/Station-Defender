package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.RapidFireAlien;
import com.aston.group.stationdefender.actors.RapidFireWeapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RapidFireUnitsTest {
    private RapidFireAlien alien;
    private RapidFireWeapon weapon;

    @Before
    public void setUp() {
        alien = new RapidFireAlien(2, 2);
        weapon = new RapidFireWeapon(4, 4);
    }

    /**
     * Tests the Constructor of the Rapid Fire Alien
     */
    @Test
    public void testAlienConstructor() {
        assertEquals("Rapid Fire Alien", alien.getName());
        assertEquals(2, alien.getSpeed(), 0);
        assertEquals(5.0, alien.getDamage(), 0);
        assertEquals(10, alien.getRateOfFire(), 0);
        assertEquals(100, alien.getHealth(), 0);
        assertEquals(2, alien.getRange(), 0);
        assertEquals(0.5, alien.getChanceToHit(), 0);
        assertEquals(2, alien.getX());
        assertEquals(2, alien.getY());
        assertEquals(100, alien.getWidth());
        assertEquals(38, alien.getHeight());
    }

    /**
     * Tests the Constructor of the Rapid Fire Weapon
     */
    @Test
    public void testWeaponConstructor() {
        assertEquals("Rapid Fire Weapon", weapon.getName());
        assertEquals(0, weapon.getSpeed(), 0);
        assertEquals(5.0, weapon.getDamage(), 0);
        assertEquals(15, weapon.getRateOfFire(), 0);
        assertEquals(10, weapon.getHealth(), 0);
        assertEquals(2, weapon.getRange(), 0);
        assertEquals(0.5, weapon.getChanceToHit(), 0);
        assertEquals(4.0, weapon.getX(), 0);
        assertEquals(4.0, weapon.getY(), 0);
        assertEquals(20, weapon.getWidth(), 0);
        assertEquals(20, weapon.getHeight(), 0);
        assertEquals(7.0, weapon.getBuildTime(), 0);
        assertEquals(60, weapon.getCost(), 0);
        assertEquals(25, weapon.getCostToUpgrade(), 0);
    }

    /**
     * Tests that the Alien Overloads if all shots hit.
     */
    @Test
    public void testAlienOverload() {
        alien.setAdjacentActor(weapon);
        assertEquals(false, alien.getOverloaded());
        for (int i = 1; i <= 10; i++) {
            System.out.print("Alien Rapid Fire Overload Test Run: " + i);
            alien.setOverloaded(false);
            assertEquals(false, alien.getOverloaded());
        }
    }

    @Test
    public void testWeaponOverload() {
        weapon.setAdjacentActor(alien);
        while (weapon.getRemainingBuildTime() > 0) {
            weapon.decrementBuildTimer();
        }
        assertEquals(false, weapon.getOverloaded());
        for (int i = 1; i <= 10; i++) {
            System.out.print("Weapon Rapid Fire Overload Test Run: " + i);
            weapon.setOverloaded(false);
            assertEquals(false, weapon.getOverloaded());
            overload();
        }
    }

    private void overload() {
        while (!alien.getOverloaded()) {
            alien.act(0.1f);
            if (alien.getOverloaded()) {
                double wepHealth = weapon.getHealth();
                alien.act(0.1f);
                assertEquals(wepHealth, weapon.getHealth(), 0);
                alien.setOverloaded(true);
                System.out.println(":" + " pass.");
            }
        }
    }
}