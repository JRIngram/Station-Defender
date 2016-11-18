package com.aston.group.stationdefender.gamesetting.helpers;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Tile class
 *
 * @author Jonathon Fitch
 * @author Twba Al-shaghdari
 */
public class Tile {
    private final int x, y, width, height;
    private final SpriteBatch batch;
    private final Texture texture;
    private final ShapeRenderer shapeRenderer;
    private Actor actor;

    /**
     * Construct a new Tile with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the Tile
     * @param y The Y co-ordinate of the Tile
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        width = Constants.TILE_WIDTH;
        height = Constants.TILE_HEIGHT;

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("textures/tile.png"));
        shapeRenderer = new ShapeRenderer();
    }

    /**
     * Check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Tiles X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x      The X co-ordinate of the object to check
     * @param y      The Y co-ordinate of the object to check
     * @param width  The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    public boolean isColliding(int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width && y + height > this.y && y < this.y + this.height;
    }

    /**
     * Returns whether a Unit is colliding with a Tile
     *
     * @param unit The Unit to check the collision status
     * @return true if the Unit is colliding, false if the Unit is not colliding
     */
    public boolean isColliding(Unit unit) {
        return isColliding(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight());
    }

    /**
     * Return the actor at this tile.
     *
     * @return The actor at this tile.
     **/
    public Actor getActor() {
        return actor;
    }

    /**
     * Returns the X co-ordinate of the center of the Tile
     *
     * @return The X co-ordinate of the center of the Tile
     */
    public int getCenterX() {
        return x + (width / 2);
    }

    /**
     * Returns the Y co-ordinate of the center of the Tile
     *
     * @return The Y co-ordinate of the center of the Tile
     */
    public int getCenterY() {
        return y + (height / 2);
    }

    /**
     * Render the Tile.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        boolean hovered = isColliding(MouseInput.getX(), MouseInput.getY(), 1, 1);

        batch.begin();
        batch.draw(texture, x, y, width, height);
        batch.end();

        if (hovered) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(222, 222, 222, 0.35f);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
    }
}