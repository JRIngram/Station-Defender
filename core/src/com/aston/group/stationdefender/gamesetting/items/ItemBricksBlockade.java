package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents a blockage formed of bricks that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemBricksBlockade extends Item {

    /**
     * Construct a new Bricks Blockade Item
     */
    public ItemBricksBlockade() {
        super("Bricks Blockade");
        id = 7;
        cost = 3;
        //TODO bricks blockade texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = true;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value);
    }

    @Override
    public Unit getPlaceableUnit() {
        //TODO create a Bricks blockade actor
        return null;
    }
}