package com.aston.group.stationdefender.actors;

/**
 * Test class for Weapon
 * @author Jamie Ingram
 */
public class TestWeapon extends Weapon {
	
	public TestWeapon(double speed, double damage, double rateOfFire, double health,
			double range, double buildTime, int cost, int costToUpgrade
			){
		super("Test Weapon", speed, damage, rateOfFire, health, range, buildTime, cost, costToUpgrade);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
