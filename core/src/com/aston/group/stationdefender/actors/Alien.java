package com.aston.group.stationdefender.actors;

/**
 * Superclass for different Alien types.
 *
 * @author IngramJ
 * @version 01/11/2016
 */
public abstract class Alien extends Unit {

    public Alien(String name, double speed, double damage, double rateOfFire, double health, double range) {
        super(name, speed, damage, rateOfFire, health, range, x, y, height, width);
    }

    @Override
    public abstract void render(float delta);

    /**
     * Move to the left.
     * Checks if adjacent space to the left is occupied by a human weapon, and if so, attack.
     *
     * @see com.aston.group.stationdefender.actors.Unit#act()
     */
	@Override
	public abstract void act();
	
    /**
     * Abstract method for each Unit, the main method which determines how the unit acts.
     */
	@Override
	public abstract void destroy();
	
	/**
	 * Moves the Alien from the left side of the lane to the right
     *
	 * @param speed Distance the Alien moves.
	 */
	public void move(double speed){
		//TODO IMPLEMENT
	}
}