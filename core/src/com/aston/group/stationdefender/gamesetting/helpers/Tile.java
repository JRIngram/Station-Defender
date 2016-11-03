package com.aston.group.stationdefender.gamesetting.helpers;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Tile class
 *
 * @author Jonathon Fitch
 * @author Twba Al-shaghdari
 */
public class Tile {
    private int x, y, width, height;
    private Actor actor;

    //DEBUG - Remove if not needed
    private ShapeRenderer shapeRenderer;

    /**
     * Construct a new Tile
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        width = Constants.TILE_WIDTH;
        height = Constants.TILE_HEIGHT;

        shapeRenderer = new ShapeRenderer();
    }

    /**
     * Place an actor at the tile.
     * If there is already an actor at that tile, placing should not happen.
     *
     * @param actor The actor to be placed
     * @return true if the actor has been placed, false if the actor hasn't been placed in the tile
     **/
    @Deprecated
    /*
      This method should be removed as Tiles are being used as helpers, not to store Actors
     */
    public boolean placeActor(Actor actor) {
        if (getActor() == null) {
            this.actor = actor;
            return true;
        }
        return false;
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
     * Render the Tile.
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {

        //Remove if not needed
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
    }
}