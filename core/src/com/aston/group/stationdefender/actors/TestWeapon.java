package com.aston.group.stationdefender.actors;

public class TestWeapon extends Weapon {
	
	public TestWeapon(String name, double speed, double damage, double rateOfFire, double health,
			double range, double buildTime, int cost, int costToUpgrade
			){
		super(name, speed, damage, rateOfFire, health, 
	    		range,	buildTime, cost, costToUpgrade);
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
