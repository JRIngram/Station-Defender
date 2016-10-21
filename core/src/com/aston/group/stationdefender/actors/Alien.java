package com.aston.group.stationdefender.actors;


/**
 * Superclass for different Alien types.
 * @author IngramJ
 * @version 20/10/2016
 */
public abstract class Alien extends Unit implements Actor{
	
	public Alien(){
		super();
	}

	@Override
	public abstract void act();

	@Override
	public abstract void destroy();
}