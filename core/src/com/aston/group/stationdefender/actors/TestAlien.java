package com.aston.group.stationdefender.actors;

/**
 * Test class for Alien
 * @author Jamie Ingram
 */
public class TestAlien extends Alien{
	
	public TestAlien(String name,  double speed, double damage, double rateOfFire, double health, double range){
		super(name, speed, damage, rateOfFire, health, range);
	}

	@Override
	public void act() {
	}

	@Override
	public void destroy() {	
	}
}
