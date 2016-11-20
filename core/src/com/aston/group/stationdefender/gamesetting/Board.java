package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Tower;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.utils.Array;

/**
 * Board class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Board {
    private final Array<Lane> lanes = new Array<>();
    private final Tower tower;
    private boolean hasWon;
    private boolean hasLost;

    /**
     * Construct a new Board with a given number of Lanes each lane will have
     * the same number of Tiles.
     *
     * @param player        The current Player
     * @param tower         The current Tower on the Board
     */
    Board(Player player, Tower tower) {
        this.tower = tower;
        int laneY = 120;

        for (int i = 0; i < Constants.LANE_AMOUNT; i++) {
            lanes.add(new Lane(player, tower, 100, laneY, Constants.TILE_AMOUNT));
            laneY += (Constants.TILE_HEIGHT + (Constants.TILE_HEIGHT / 2));
        }
    }

    /**
     * Adds a Lane to the Board
     *
     * @param lane The Lane to add to the Board
     */
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    /**
     * Removes a Lane from the Board by lane number
     *
     * @param index The Lane number to remove from the Board
     */
    public void removeLaneByIndex(int index) {
        lanes.removeIndex(index - 1);
    }

    /**
     * Removes a Lane from the Board by Lane Object
     *
     * @param lane The Lane Object to be removed from the Board
     */
    public void removeLaneByObject(Lane lane) {
        lanes.removeValue(lane, true);
    }

    /**
     * Returns a Lane by the specific Lane number
     *
     * @param index The lane number of the lane to get
     * @return The lane of the specific lane number
     */
    public Lane getLane(int index) {
        return lanes.get(index - 1);
    }

    /**
     * Empty the board
     **/
    public void clear() {
        lanes.clear();
    }

    /**
     * Place an actor at the given lane and tile. if there is already an actor
     * at that tile placing should not happen.
     *
     * @param unit The Unit to be placed
     * @param x    The X co-ordinate the place the Unit
     * @param y    The Y co-ordinate of the Unit
     **/
    public void place(Unit unit, int x, int y) {
        for (Lane lane : lanes) {
            if (lane.isColliding(x, y, 1, 1)) {
                lane.place(unit, x, y);
            }
        }
    }

    /**
     * Place an entity at the given lane and tile. if there is already an entity
     * at that tile it will be lost.
     *
     * @param laneNo The index of the lane the entity is in
     * @param tileNo The index of the tile the entity is in
     * @return The Actor at the specific lane and tile
     **/
    public Actor getActorAt(int laneNo, int tileNo) {
        return lanes.get(laneNo).getTile(tileNo).getActor();
    }

    /**
     * Render the Board.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        for (Lane lane : lanes) {
            lane.render(delta);
            if (lane.isOverrun()) {
                if (!tower.getExists())
                    hasLost = true;
            }
        }
        if (isAllLanesCleared()) {
            hasWon = true;
        }
    }

    /**
     * Returns whether all the lanes are clear or not
     *
     * @return true if all the lanes are cleared, false if any of the lanes are not cleared
     */
    private boolean isAllLanesCleared() {
        boolean cleared = true;
        for (int i = 0; i < lanes.size; i++) {
            if (!lanes.get(i).isCleared()) {
                cleared = false;
            }
        }
        return cleared;
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (Lane lane : lanes) {
            lane.dispose();
        }
    }

    /**
     * Returns whether the Player has won the game or not
     *
     * @return true if the Player has won, false if the Player has not won
     */
    boolean isHasWon() {
        return hasWon;
    }

    /**
     * Returns whether the Player has lost the game or not
     *
     * @return true if the Player has lost, false if the Player has not lost
     */
    boolean isHasLost() {
        return hasLost;
    }
}