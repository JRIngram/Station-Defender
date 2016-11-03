package com.aston.group.stationdefender.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aston.group.stationdefender.actors.TestAlien;
import com.aston.group.stationdefender.actors.TestWeapon;

public class AlienTest {
	TestWeapon adjacentWep;
	TestAlien testAlien;
	
	@Before
	public void setUp(){
		adjacentWep = new TestWeapon(2, 2, 10, 1, 10, 100, 25, 0.0d, 0.0d, 100, 100);
		testAlien = new TestAlien(1, 3, 3, 15, 1, 100, 100, 100, 100);
	}
	
	@Test
	public void testConstructor(){
		assertEquals("Test Alien", testAlien.getName());
		assertEquals(1, testAlien.getSpeed(), 0);
		assertEquals(3, testAlien.getDamage(), 0);
		assertEquals(3, testAlien.getRateOfFire(), 0);
		assertEquals(15, testAlien.getHealth(), 0);
		assertEquals(1, testAlien.getRange(), 0);

	}
	
	@Test
	public void testDamageAndCheckHealth(){
		for(int i = 15; i > 0; i--){
			assertEquals(i, testAlien.getHealth(), 0);
			assertEquals(false, testAlien.checkZeroHealth());
			testAlien.takeDamage(1);
		}
		assertEquals(0, testAlien.getHealth(), 0);
		assertEquals(true, testAlien.checkZeroHealth());
	}
	
	@Test
	public void testFiring(){
		double damageDealt = testAlien.fire();
		assertTrue((damageDealt >= 0) && (damageDealt <= 9));
	}
	
	@Test
	public void testAdjacent(){
		TestAlien adjacentAlien = new TestAlien(1, 3, 3, 15, 1, 10, 100, 100, 100);
		assertEquals(false, testAlien.isAdjacent());
		assertEquals(null, testAlien.getAdjacentActor());
		testAlien.setAdjacentActor(adjacentWep);
		assertEquals(true, testAlien.isAdjacent());
		assertEquals(adjacentWep, testAlien.getAdjacentActor());
		testAlien.setAdjacentActor(adjacentAlien);
		assertEquals(true, testAlien.isAdjacent());
		assertEquals(adjacentAlien, testAlien.getAdjacentActor());
		testAlien.setAdjacentActor(null);
		assertEquals(false, testAlien.isAdjacent());
		assertEquals(null, testAlien.getAdjacentActor());
	}
	
	@Test
	public void testDamageDealing(){
		assertEquals(10, adjacentWep.getHealth(), 0);
		testAlien.setAdjacentActor(adjacentWep);
		assertEquals(adjacentWep, testAlien.getAdjacentActor());
		testAlien.act();
		assertTrue((adjacentWep.getHealth() <= 10));
	}

}
