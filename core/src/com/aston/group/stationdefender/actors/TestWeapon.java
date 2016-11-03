package com.aston.group.stationdefender.actors;

/**
 * Test class for Weapon
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class TestWeapon extends Weapon {
	
	private ShapeRenderer shapeRenderer;
	public TestWeapon(double damage, double rateOfFire, double health, int x, int y, int height, int width
			double range, double buildTime, int cost, int costToUpgrade
			){
		super("Test Weapon", 0, damage, rateOfFire, health, range, x, y, height, width buildTime, cost, costToUpgrade);
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filler);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
	}
	
    /**
     * The main method which determines how the weapon acts.
     */
	@Override
	public void act(){
		if(!checkZeroHealth() && built){
			if(isAdjacent){
				try{
					adjacentActor.takeDamage(fire());
				}catch(Exception e){
					System.out.println("Null values are not allowed");
				}
			}
		}else{
			destroy();
		}
	}
	
	/**
	 * Plays an explosion sound and animation.
	 */
	@Override
	public void destroy() {
		// TODO IMPLEMENT
		//Play explosion animation.
		//Play explosion sound.
		System.out.println("UNIT DESTROYED - FILLER BEFORE IMPLEMENTATION.");
		exists = false;
	}
}
