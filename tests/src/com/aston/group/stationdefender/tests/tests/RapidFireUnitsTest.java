package com.aston.group.stationdefender.tests.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aston.group.stationdefender.actors.RapidFireAlien;
import com.aston.group.stationdefender.actors.RapidFireWeapon;

public class RapidFireUnitsTest{
	private RapidFireAlien alien;
	private RapidFireWeapon weapon;
	
	@Before
	public void setUp(){
		alien = new RapidFireAlien(2, 2, 10, 10);
		weapon = new RapidFireWeapon(4,4,20,20, 10, 50, 25);
	}
	
	@Test
	public void testAlienConstructor() {
		assertEquals("Rapid Fire Alien", alien.getName());
		assertEquals(2, alien.getSpeed(), 0);
		assertEquals(0.5, alien.getDamage(), 0);
		assertEquals(10, alien.getRateOfFire(),0);
		assertEquals(100, alien.getHealth(), 0);
		assertEquals(2, alien.getRange(), 0);
		assertEquals(0.5, alien.getChanceToHit(), 0);
		assertEquals(2, alien.getX());
		assertEquals(2, alien.getY());
		assertEquals(10, alien.getWidth());
		assertEquals(10, alien.getHeight());
	}
	
	@Test
	public void testWeaponConstructor(){
		assertEquals("Rapid Fire Weapon", weapon.getName());
		assertEquals(0, weapon.getSpeed(), 0);
		assertEquals(0.5, weapon.getDamage(), 0);
		assertEquals(15, weapon.getRateOfFire(), 0);
		assertEquals(10, weapon.getHealth(), 0);
		assertEquals(2, weapon.getRange(), 0);
		assertEquals(0.5, weapon.getChanceToHit(), 0);
		assertEquals(4.0, weapon.getX(), 0);
		assertEquals(4.0, weapon.getY(), 0);
		assertEquals(20, weapon.getWidth(), 0);
		assertEquals(20, weapon.getHeight(), 0);
	}
	
	@Test
	public void testAlienOverload(){
		alien.setAdjacentActor(weapon);
		assertEquals(false, alien.getOverloaded());
		for(int i = 1; i <= 10; i++){
			System.out.print("Alien Rapid Fire Overload Test Run: " + i);
			alien.setOverloaded(false);
			assertEquals(false, alien.getOverloaded());
			while(!alien.getOverloaded()){
				alien.act(0.1f);
				if(alien.getOverloaded() == true){
					double wepHealth = weapon.getHealth();
					alien.act(0.1f);
					assertEquals(wepHealth, weapon.getHealth(), 0);
					alien.setOverloaded(true);
					System.out.println(":" + " pass.");
				}
			}
		}
	}
	
	@Test
	public void testWeaponOverload(){
		weapon.setAdjacentActor(alien);
		assertEquals(false, weapon.getOverloaded());
		for(int i = 1; i <= 10; i++){
			System.out.print("Weapon Rapid Fire Overload Test Run: " + i);
			weapon.setOverloaded(false);
			assertEquals(false, weapon.getOverloaded());
			while(!weapon.getOverloaded()){
				weapon.act(0.1f);
				if(weapon.getOverloaded() == true){
					double alienHealth = alien.getHealth();
					weapon.act(0.1f);
					assertEquals(alienHealth, alien.getHealth(), 0);
					weapon.setOverloaded(true);
					System.out.println(":" + " pass.");
				}
			}
		}
	}
}