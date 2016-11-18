package com.aston.group.stationdefender.actors;

public class CloseCombatWeapon extends Weapon {
	
	public CloseCombatWeapon(int x, int y){
		
	}
	
	public CloseCombatWeapon(int x, int y, int width, int height){
		super("Close Comabat Weapon", 0, 5.0, 2.0, 10.0, 1.0, 7.0, x, y, width, height, 5.0, 50, 25);
	}
}
