package com.aston.group.stationdefender.actors;

/**
 * Fast Alien with slow, but high damage.
 * @author IngramJ
 *
 */
public class CloseCombatAlien extends Alien{
	
	public CloseCombatAlien(int x, int y){
		super(x, y);
	}
	
	public CloseCombatAlien(int x, int y, int width, int height){
		super("Close Combat Alien", 2, 6, 2, 10, 1, 7.0, x, y, width, height);
	}
}
