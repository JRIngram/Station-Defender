package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.CloseCombatWeapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CloseCombatWeaponTest {
    private CloseCombatWeapon weapon;

    @Before
    public void setUp() {
        weapon = new CloseCombatWeapon();
    }

    /*String name, double speed, double damage, double rateOfFire, double health,
     * double range, double chanceToHit, int x, int y, int width, int height,
    double buildTime, int cost, int costToUpgrade*/
    @Test
    public void test() {
        assertEquals("Close Combat Weapon", weapon.getName());
        assertEquals(0, weapon.getSpeed(), 0);
        assertEquals(50.0, weapon.getDamage(), 0);
        assertEquals(2.0, weapon.getRateOfFire(), 0);
        assertEquals(100.0, weapon.getHealth(), 0);
        assertEquals(1.0, weapon.getRange(), 0);
        assertEquals(7.0, weapon.getChanceToHit(), 0);
        assertEquals(0, weapon.getX(), 0);
        assertEquals(0, weapon.getY(), 0);
        assertEquals(60, weapon.getWidth(), 0);
        assertEquals(60, weapon.getHeight(), 0);
        assertEquals(5.0, weapon.getBuildTime(), 0);
        assertEquals(50.0, weapon.getCost(), 0);
        assertEquals(25.0, weapon.getCostToUpgrade(), 0);
    }

    @Test
    public void testDamageAndCheckHealth() {
        assertEquals(100, weapon.getHealth(), 0);
        for (int i = 100; i > 0; i--) {
            assertEquals(i, weapon.getHealth(), 0);
            assertEquals(false, weapon.checkZeroHealth());
            weapon.takeDamage(1);
        }
        assertEquals(0, weapon.getHealth(), 0);
        assertEquals(true, weapon.checkZeroHealth());
    }

}