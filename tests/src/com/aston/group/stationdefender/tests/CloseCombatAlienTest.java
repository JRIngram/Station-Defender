package com.aston.group.stationdefender.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aston.group.stationdefender.actors.CloseCombatAlien;
import com.aston.group.stationdefender.actors.CloseCombatWeapon;
import com.badlogic.gdx.Gdx;

public class CloseCombatAlienTest extends GdxTestRunner {
	private CloseCombatWeapon adjacentWep;
	private CloseCombatAlien testAlien;
	
	@Before
	public void setUp(){
		Gdx.app.postRunnable(() -> {

		});
	}
	
	@Test
	public void testConstructor() {
		Gdx.app.postRunnable(() -> {
			assertEquals("Close Combat Alien", testAlien.getName());
			assertEquals(2, testAlien.getSpeed(), 0);
			assertEquals(3, testAlien.getDamage(), 0);
			assertEquals(6, testAlien.getDamage(), 0);
			assertEquals(10, testAlien.getHealth(), 0);
			assertEquals(7.0, testAlien.getChanceToHit(), 0);
		});
	}
	
    @Test
    public void testDamageAndCheckHealth() {
        Gdx.app.postRunnable(() -> {
			testAlien = new CloseCombatAlien(100,100,100,100);
			assertEquals(10, 1);
        	assertEquals(100, testAlien.getHealth());
            for (int i = 10; i > 0; i--) {
                assertEquals(i, testAlien.getHealth(), 0);
                assertEquals(false, testAlien.checkZeroHealth());
                testAlien.takeDamage(1);
            }
            assertEquals(0, testAlien.getHealth(), 0);
            assertEquals(true, testAlien.checkZeroHealth());
        });
    }

}
