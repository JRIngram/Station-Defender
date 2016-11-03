package com.aston.group.stationdefender.actors;

/**
 * Test class for Alien
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class TestAlien extends Alien{
	
	private ShapeRenderer shapeRenderer;
	public TestAlien(double speed, double damage, double rateOfFire, double health, double range, int x, int y, int height, int width){
		super("Test Alien", speed, damage, rateOfFire, health, range, x, y, height, width);
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filler);
		shapeRenderer.setColor(Colour.BLUE);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
	}
	
    /**
     * The main method which determines how the weapon acts.
     */
	@Override
	public void act(){
		if(!checkZeroHealth()){
			if(isAdjacent){
				try{
					adjacentActor.takeDamage(fire());
				}catch(Exception e){
					System.out.println("Null values are not allowed");
				}
			}else{
				move(speed);
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
		//TODO IMPLEMENT
		//Play explosion animation.
		//Play explosion sound.
		exists = false;
	}
}
