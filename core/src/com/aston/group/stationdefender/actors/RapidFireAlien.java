package com.aston.group.stationdefender.actors;

import java.util.Random;

public class RapidFireAlien extends Alien {
	
	/**
	 * Fast Firing Alien with fast, but low damage.
	 *
	 * @author IngramJ
	 * 
	 */
	
	private boolean overloaded;
	public RapidFireAlien(int x, int y){
		super(x, y);
		overloaded = false;
	}
	
	public RapidFireAlien(int x, int y, int width, int height){
		//name, speed, damage, rateOfFire, health, range, chanceToHit, x, y, width, height
		super("Rapid Fire Alien", 2, 0.5, 10, 10, 2, 0.5, x, y, width, height);
		overloaded = false;
	}
	
	/**
	 * Similar to usual act method, but if the alien hits on all shots the alien "overloads" and cannot fire for the next call of act.
	 * No damage is dealt on this round,
	 */
    @Override
    public void act() {
        if (!checkZeroHealth()) {
        	if(!overloaded){
	            if (isAdjacent) {
	                try {
	                	double damageDealt = fire();
	                	if((damageDealt / getDamage()) == getRateOfFire()){
	                		overloaded = true;
	                	}else{
	                        adjacentActor.takeDamage(damageDealt);
	                	}
	
	                } catch (Exception e) {
	                    System.out.println("Null values are not allowed");
	                }
	            } else {
	                move(speed);
	            }
        	}
        	else
        		overloaded = false;
        } else {
            destroy();
        }
    }
    
    /**
     * Returns if the Alien is overloaded.
     * @return Overloaded state of the Alien.
     */
    public boolean getOverloaded(){
    	return overloaded;
    }
    
    /**
     * Sets if the Alien is overloaded or not.
     * @param overloaded state of the Alien.
     */
    public void setOverloaded(boolean overloaded){
    	this.overloaded = overloaded;
    }
}
